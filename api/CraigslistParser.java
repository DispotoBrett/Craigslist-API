package api;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
/**
 * Finds information from Craigslist classified ads.
 * @author Brett Dispoto
 *
 */
public class CraigslistParser
{
	private String search;
	private String location;
	private String theURL[];
	public static final int MAX_PAGES = 24;
	public static final int MAX_LISTINGS = 3000;
	
	
	/**
	 * Instantiates a new api.CraigslistParser.
	 * @param theLocation the location to be searched in *MUST BE IN CL FORMAT*.
	 * @param searchMe the term to be searched for.
	 */
	public CraigslistParser(String theLocation, String searchMe)
	{
		location = theLocation;
		search = searchMe;
		search = search.replaceAll(" ", "%20");
		theURL = generateURL();
	}
	
	/**
	 * Gets the pertinent information- price and title, in string format.
	 * @return an array of the requested number of results; unless less were available than requested. 
	 * @throws IOException 
	 */
	 public Listing[] getInfo(int numberOfResults) throws IOException
	 {
		try 
		{
			String title = "", line = "";
			double price = 0;
    		boolean  foundCloser = false, foundOpener = false; 
    		int lim = 0, objectsFound = 0;
    		Listing[] results = new Listing[numberOfResults]; //Trim down later if we can't find all of them
		    Scanner scan =  null;		
    		for(String u: theURL)
    		{
        		URL url = new URL(u);
        		InputStream in = url.openStream();

        		scan = new Scanner(in);
		    
			    while(scan.hasNextLine() && objectsFound < numberOfResults)
			    {
			    	if( (price != 0) && !title.equals("")) //Not all listings have price change URL to force that
			    	{
			    		results[objectsFound] = new Listing(title, price);
			    		title = "";
			    		price = 0;
			    		objectsFound++;
			    	}
			    	
			    	line = scan.nextLine();
			    	
			    	//Get Price
			    	if(line.contains("<span class=\"result-price\">"))
			    	{
	
			    		for(int i = 0; i < line.length() && !foundOpener; i++)
			    		{
			    			if(line.charAt(i) ==  '>')
			    			{
			    				foundOpener = true;
			    				line = line.substring(i + 2);
			    			}
			    		}
			    		
	
			    		for(int i = 0; i < line.length() && !foundCloser; i++)
			    		{
			    			if(line.charAt(i) ==  '<')
			    			{
			    				foundCloser = true;
			    				line = line.substring(0 , i);
			    			}
			    		}
			    		price = Double.parseDouble(line);
			    		foundOpener = false;
			    		foundCloser = false;
	
			    	}	 
		    	
			    	//Get Title
			    	else if(line.contains("\" class=\"result-title hdrlnk\">"))
			    	{
			    		for(int i = 0; i < line.length() && !foundCloser; i++)
			    		{
			    			if(line.charAt(i) == '>')
			    			{
			    				lim = i;
			    				foundCloser = true;
			    			}
			    		}
			    		line = line.substring(lim + 1);
			    		
			    		for(int i = 0; i < line.length() && !foundOpener; i++)
			    		{
			    			if(line.charAt(i) == '<')
			    			{
			    				lim = i;
			    				foundOpener = true;
			    			}
			    		}
			    		title = line.substring(0, lim);
			    		foundOpener = false;
			    		foundCloser = false;
			    	}
			    	
		    	}
		    }
    		int trueLength = 0;
		    Listing[] trimmedResults;
		    
		    for(int i = 0; i < results.length; i++)
		    {
		    	if(results[i] == null)
		    	{
		    		trueLength = i;
		    		break;
		    	}
		    }
		    
		    trimmedResults = new Listing[trueLength];
		    
		    for(int i = 0; i < trueLength; i++)
		    {
		   		trimmedResults[i] = results[i];
		    }
		    
		    return trimmedResults;
		}
		catch(MalformedURLException e)
		{
			throw e;
		}
		catch(IOException e)
		{
			throw e;
		}

	 }
	 
	/**
	 * Internal class use only.
	 * @return URL needed for parser.
	 */
	private String[] generateURL()
	{
		String[] retVal = new String[MAX_PAGES];
		
		for(int i = 0; i < MAX_PAGES; i++)
		{
			retVal[i] = "https://" + location + ".craigslist.org/search/sss?s=" + (0 * i) +  "&query=" + search + "&sort=rel";
		}
		return retVal;
	}
	
}
