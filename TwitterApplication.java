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
		  .setOAuthConsumerKey("**************");	 // Censored
		  .setOAuthConsumerSecret("**************");	 // Censored
		  .setOAuthAccessToken("**************"); 	 // Censored
		  .setOAuthAccessTokenSecret("**************");  // Censored
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
