import java.util.Comparator;

/**
 * Implementation of Comparator interface (JAVA SE8).
 * @author Brett Dispoto
 *
 */
public class ListingComparator implements Comparator<Listing>
{
	public int compare(Listing l1,Listing l2)
	{
			return l1.compareTo(l2);
	}
}
