package api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Listings {

    public  Listings(){}

    public Listings(Listing[] listings)
    {
        listing = listings;
    }

    @XmlElement
    Listing[] listing;
}
