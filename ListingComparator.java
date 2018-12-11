import java.util.Comparator;

public class ListingComparator implements Comparator<Listing>
{
	public int compare(Listing l1,Listing l2)
	{
			return l1.compareTo(l2);
	}
}