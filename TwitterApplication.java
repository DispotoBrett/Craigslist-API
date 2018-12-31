import java.util.*;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
//include Craigslist API files in src

/**
 * Application to test Brett Dispoto's Craigslist API (Using Twitter)
 * @author BrettDispoto
 *
 */
public class TwitterApplication 
{	
	public static void main(String[] args) throws NoSuchFieldException, TwitterException
	{	
		//-----------------------------------Declarations-------------------------------
		Set<String> hasBeenTweeted = new HashSet<String>();
		String tweetMe;
		Query q = new Query("NYC", "Basketball");
		Listing[] l = q.getListings();
		//------------------------------------------------------------------------------
		
		
		//-----------------------------------Config-------------------------------------
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("KC728loPPZhr80pZPOlbcF89X") // Censored
		  .setOAuthConsumerSecret("B7VSZGCaNY7kjZS4m7RktU5a7iwM08TyCZnhydvh1CcXkHounK") // Censored
		  .setOAuthAccessToken("1079482993545142272-rLITjdAvD2rKPpU1XbmXle4bLhKhIq") // Censored
		  .setOAuthAccessTokenSecret("TaXwfvSqZiHT5BLKqrLzgUVaXHkZQa4OYFUt3ZtlY3U6u"); // Censored
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		//------------------------------------------------------------------------------

		
		
		//-------------------------------------Tweet------------------------------------
		
		twitter.updateStatus("Tweeting Basketballs in New York City");
		for(int i = 0; i < l.length; i++)
		{
			tweetMe = l[i].getTitle() + "........$" + l[i].getPrice() + '\n';
			System.out.println(tweetMe);
			if(tweetMe.length() < 280 && !hasBeenTweeted.contains(tweetMe))
			{
				twitter.updateStatus(tweetMe);
				hasBeenTweeted.add(tweetMe);
			}
		}
		//------------------------------------------------------------------------------

	}
}            
