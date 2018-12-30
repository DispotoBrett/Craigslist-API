import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Application to test Brett Dispoto's Craigslist API (Using Twitter)
 * @author BrettDispoto
 *
 */
public class TwitterApplication 
{	
	public static void main(String[] args) throws NoSuchFieldException, TwitterException
	{	
		//-----------------------------Config-------------------------------------
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("*************") // Censored
		  .setOAuthConsumerSecret("*************") // Censored
		  .setOAuthAccessToken("*************") // Censored
		  .setOAuthAccessTokenSecret("*************"); // Censored
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		//------------------------Get Info/Tweet-----------------------
		Query q = new Query("Bham", "computer");
		Listing[] l = q.getListings();
		String tweetMe = "", addMe;
		Status status =  twitter.updateStatus("Tweeting Computers In Birmingham");
		System.out.println(l.length);
		for(int i = 0; i < 5; i++)
		{
			addMe = l[i].getTitle() + "........$" + l[i].getPrice() + '\n';
			
			if(addMe.length() < 280 && !status.getText().equals(addMe))
				status = twitter.updateStatus(addMe);
		}
	}
}            
