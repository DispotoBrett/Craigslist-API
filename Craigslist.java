import java.util.Scanner;
/**
 * Application to test Craigslist "Paser"
 * @author BrettDispoto
 *
 */
public class Craigslist 
{	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter Location");
		String loc = scan.nextLine();
		System.out.println("Enter Search");
		String qry = scan.nextLine();
		CraigslistParser test = new CraigslistParser(loc, qry);
		
		System.out.println(test.getInfo());
	}
}
