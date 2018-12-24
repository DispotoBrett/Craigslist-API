import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
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
	public static final int GET_ALL_LISTINGS = -1;
	
	
	/**
	 * Instantiates a new CraigslistParser.
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
	 */
	 public Listing[] getInfo(int numberOfResults)
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
		    //scan.close();
		    
		    //Trim null values from array
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
		    
		    trimmedResults = new Listing[trueLength + 1];
		    
		    for(int i = 0; i < trueLength; i++)
		    {
		    	trimmedResults[i] = results[i];
		    }
		    
		    return results;
		}
		catch(Exception e)
		{
			System.out.println("URl is the error");
			System.exit(-2);
			 //return null;
			return null;
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
