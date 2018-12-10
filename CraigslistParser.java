import java.io.InputStream;
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
	private String theURL;
	
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
	 public String getInfo()
	 {
		try 
		{
			//To be modified: soon to return Array of Listings.
			String retval = "";
			String line = "";
    		boolean  foundCloser = false;
    		boolean foundOpener = false; 
    		int lim = 0;
    		
		    URL url = new URL(theURL);
		    InputStream in = url.openStream();
		    Scanner scan = new Scanner(in);
		    
		    while(scan.hasNextLine() && retval == "")
		    {
		    	line = scan.nextLine();
		    	if(line.contains("\" class=\"result-title hdrlnk\">"))
		    	{
		    		for(int i = 0; i < line.length() && !foundCloser; i++)
		    		{
		    			if(line.charAt(i) == '>')
		    			{
		    				lim = i;
		    				foundCloser = true;
		    			}
		    		}
		    		
		    		retval = line.substring(lim + 1);
		    		
		    		for(int i = 0; i < retval.length() && !foundOpener; i++)
		    		{
		    			if(retval.charAt(i) == '<')
		    			{
		    				lim = i;
		    				foundOpener = true;
		    			}
		    		}
		    		
		    		retval = retval.substring(0, lim);
		    		
		    	}
		    }
		    scan.close();
		    return retval;		
		}
		catch(Exception e)
		{
			 return "Improper Input";
		}
	 }
	 
	/**
	 * Internal class use only.
	 * @return URL needed for parser.
	 */
	private String generateURL()
	{
		return "https://" + location + ".craigslist.org/search/sss?query=" + search + "&sort=rel";
	}
	
}
