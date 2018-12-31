import java.io.IOException;

/**
 * Creates and manages an array of Craigslist listings (For sale), this is the only recommended class for public use.
 * @author Brett Dispoto 
 */
public class Query 
{
	CraigslistParser p;
	Listing[] theListings;
	
	/**
	 * Creates a new query.
	 * @param location the desiered cl location code, (ex: Los Angeles -> LA).
	 * @param search the product to search for.
	 */
	public Query(String location, String search)
	{
		CraigslistParser p = new CraigslistParser(location, search);
		try 
		{
			theListings = p.getInfo(CraigslistParser.MAX_LISTINGS);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
		
	/**
	 * Gets the listings found for the queried term.
	 * @return the listings found in array from.
	 */
	public Listing[] getListings()
	{
		return theListings;
	}
}
