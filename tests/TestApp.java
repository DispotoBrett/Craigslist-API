package tests;

import api.Listing;
import api.Query;

import java.util.*;
//include Craigslist API files in src

/**
 * Application to test Brett Dispoto's Craigslist API (Using Twitter)
 * @author BrettDispoto
 *
 */
public class TestApp
{	
	public static void main(String[] args) throws NoSuchFieldException
	{	
		//-----------------------------------Declarations-------------------------------
		Set<String> hasBeenTweeted = new HashSet<String>();
		String tweetMe;
		Query q = new Query("NYC", "Basketball");
		Listing[] l = q.getListings();
		q.getListingsInJSONFile();
		q.getListingsInXMLFile();
		q.getListingsInJSONString();
		//------------------------------------------------------------------------------

	}
}            
