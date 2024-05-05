package myApplication;

import beans.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
public class XMLRead {

    public static DataSheet XMLReadData(String fileName) {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        DataSheet dsh = null;

        try {
            SAXParser saxParser = factory.newSAXParser();
            DataHandler handler = new DataHandler();
            InputStream xmlInput = new FileInputStream(fileName);
            saxParser.parse(xmlInput, handler);
            dsh = handler.getDataSheet();
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }

        return dsh;
    }
}