package task1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.LinkedHashSet;
public class DataHandler extends DefaultHandler {
    private LinkedHashSet<String> tagNames = new LinkedHashSet<>();

    public void startDocument() throws SAXException {
        System.out.println("Start Document Parsing Process ...");
    }
    public void endDocument() throws SAXException {
        System.out.println("End Document Parsing Process ...");
        System.out.println("List of tag names present in the document:");
        for (String tagName : tagNames) {
            System.out.println(tagName);
        }
    }
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tagNames.add(qName);
        System.out.println("Start Element: " + qName);
    }
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("End Element: " + qName);
    }
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length).trim();
        if (!data.isEmpty()) {
            System.out.println("Text: " + data);
        }
    }
}
