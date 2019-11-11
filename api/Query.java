package api;

import com.google.gson.Gson;

import java.io.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Creates and manages an array of Craigslist listings (For sale), this is the only recommended class for public use.
 * @author Brett Dispoto 
 */
public class Query 
{
	CraigslistParser p;
	Listing[] theListings;
	
	/**
	 * Creates a new query.
	 * @param location the desiered cl location code, (ex: Los Angeles -> LA).
	 * @param search the product to search for.
	 */
	public Query(String location, String search)
	{
		CraigslistParser p = new CraigslistParser(location, search);
		try 
		{
			theListings = p.getInfo(CraigslistParser.MAX_LISTINGS);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
		
	/**
	 * Gets the listings found for the queried term.
	 * @return the listings found in array from.
	 */
	public Listing[] getListings()
	{
		return theListings;
	}

	/**
	 * Gets the listings in JSON String format found for the queried term.
	 * @return the listings found in String (JSON) from.
	 */
	public String getListingsInJSONString()
	{
		return (new Gson().toJson(theListings));
	}

	/**
	 * Gets the listings in a .JSON file for the queried term.
	 * @return the listings found in JSON from.
	 */
	public File getListingsInJSONFile()
	{
		File f = new File("tests/listings.json");
		try {
			FileWriter fw = new FileWriter(f.getName());
			fw.write(getListingsInJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return f;
	}

	/**
	 * Gets the listings found in an XML file for the queried term.
	 * @return the listings found in array XML.
	 */
	public File getListingsInXMLFile()
	{
		File f = new File("tests/listings.xml");
		Listings listings = new Listings(theListings);
		try {
			FileWriter fw = new FileWriter(f.getName());
			fw.write("");
			JAXBContext jaxbContext = JAXBContext.newInstance(Listings.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(listings, sw);
			fw.write(sw.toString());
			fw.close();
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}

		return f;
	}
}
