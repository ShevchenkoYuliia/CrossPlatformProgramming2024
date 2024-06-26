package task3;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.*;
public class EthnicitySAXParser {
    public static void parse(String fileName) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            DefaultHandler handler = new EthnicityHandler();
            InputStream xmlStream = new FileInputStream(fileName);
            parser.parse(xmlStream, handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        EthnicitySAXParser.parse("Popular_Baby_Names_NY.xml");
    }
}

