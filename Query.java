public class Query 
{
	Listing[] theListings;
	
	public Query(String location, String search)
	{
		CraigslistParser p = new CraigslistParser(location, search);
		theListings = p.getInfo(CraigslistParser.GET_ALL_LISTINGS);
	}
	
	public Query(String location, String search, int numberOfResults)
	{
		CraigslistParser p = new CraigslistParser(location, search);
		theListings = p.getInfo(numberOfResults);	
	}
	
	public Listing[] getListings()
	{
		return theListings;
	}
}
