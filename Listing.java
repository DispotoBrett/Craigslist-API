import java.util.Objects;

/**
 * The basis of the craigslist API, an Item represents 1 (one) CL search result.
 * @author BrettDispoto
 */
public class Listing implements Comparable
{
	
	private boolean priceAvailable;
	private String title;
	private double price;
	//Coming Soon: Image
	
	//---------------Constructors------------
	/**
	 * Sets default values ~ empty Listing
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
	 * @Override
	 */
	public int compareTo(Object other)
	{
		if(other != null)
		{
			if(other.getClass().getName().equals(getClass().getName()))
			{
				Listing otherListing = (Listing) other;
				return ((priceAvailable == true) && (otherListing.priceAvailable == true)) ?
						Double.compare(this.price, otherListing.price) :
						title.compareTo(otherListing.title);
			}
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
	 * rows NoSuchFieldException
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
