package api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Models a single craigslist listing.
 * @author BrettDispoto
 */

@XmlRootElement
public class Listing implements Comparable<Listing>
{
	@XmlElement
	private boolean priceAvailable;
	@XmlElement
	private String title;
	@XmlElement
	private double price;
	
	//---------------Constructors------------
	/**
	 * Sets default values ~ empty api.Listing
	 */
	public Listing()
	{
		price = 0;
		title = "";
		priceAvailable = false;
	}
	
	/**
	 * Create new listing (without price).
	 * @param title the title of the listing.
	 */
	public Listing(String title)
	{
		this.title = title;
		price = 0;
		priceAvailable = false;
	}
	
	/**
	 * Create new listing (with price).
	 * @param title the title of the listing.
	 * @param price the price of the listing.
	 */
	public Listing(String title, double price)
	{
		this.title = title;
		this.price = price;
		priceAvailable = true;
	}
	
	//---------------Overrides------------
	
	/**
	 * Compares by price if available, otherwise uses title.
	 * @return the comparison
	 */
	public int compareTo(Listing other)
	{
		if(other != null)
		{
			return ((priceAvailable == true) && (other.priceAvailable == true)) ?
					Double.compare(this.price, other.price) :
					title.compareTo(other.title);
		}
		return -1; //Not of the same type.
	}
	
	@Override
	public String toString()
	{
		String retval = getClass().getName() + "[title=" + title + ",priceAvailable" + priceAvailable + "]";
		if(priceAvailable)
			retval = retval.replace("]" , ",price=" + Double.toString(price) + "]");
		
		return retval;			
	}

	
	@Override
	public boolean equals(Object other)
	{
		if(other != null)
		{
			if(other.getClass().getName().equals(getClass().getName()))
			{
				if(hashCode() == other.hashCode())
					return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(title, priceAvailable, price);
	}
	
	
	//---------------General Methods----------------------
	/**
	 * Gets the item's price (if author has listed it).
	 * @return price of the listing
	 * @throws NoSuchFieldException
	 */
	public double getPrice() throws NoSuchFieldException
	{
		if(priceAvailable = false)
			throw new NoSuchFieldException();
		
		return price;
	}
	
	/**
	 * Gets the title of the listing.
	 * @return the title of the listing
	 */
	public String getTitle()
	{
		return title;
	}
	
}
