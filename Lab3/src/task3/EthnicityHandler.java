package task3;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;

public class EthnicityHandler extends DefaultHandler {
    private Set<String> Ethnicity = new HashSet<>();
    private boolean inEthcty = false;
    public Set<String> getNationalGroups() {
        return Ethnicity;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("ethcty")) {
            inEthcty = true;
        }
    }
    public void startDocument() throws SAXException {
        System.out.println("Start Document Parsing Process ...");
    }
    public void endDocument() throws SAXException {
        System.out.println("End Document Parsing Process ...");
        System.out.println("Ethnicities:");
        for (String ethnicity : Ethnicity) {
            System.out.println(ethnicity);
        }
    }
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (inEthcty) {
            String  ethnicity = new String(ch, start, length);
            Ethnicity.add(ethnicity);
            inEthcty = false;
        }
    }

}
