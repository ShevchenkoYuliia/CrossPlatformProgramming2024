package myApplication;

import beans.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class DataHandler extends DefaultHandler {
    private DataSheet datasheet = null;
    private Data tmpData = null;
    private boolean isX, isY;
    public DataSheet getDataSheet() {
        return datasheet;
    }
    public void setDataSheet(DataSheet dsh) {
        this.datasheet = dsh;
    }
    public void startDocument() throws SAXException {
        System.out.println("Start Document Parsing Process ...");
        if (datasheet == null) {
            datasheet = new DataSheet();
            datasheet.setName("New DataSheet");
        }
    }
    public void endDocument() throws SAXException {
        System.out.println("\tData structure constructed");
        System.out.println("\t"+datasheet);
        System.out.println("End Document Parsing Process ...");
    }
    public void startElement(String uri, String name, String qName,
                             Attributes attrs) throws SAXException {
        if (qName.equals("data")) {
            tmpData = new Data();
            if (attrs.getLength() > 0) {
                tmpData.setDate(attrs.getValue(0));
            }
        } else if (qName.equals("x")) {
            isX = true;
        } else if (qName.equals("y")) {
            isY = true;
        }
    }
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (qName.equals("x")) {
            isX = false;
        } else if (qName.equals("y")) {
            isY = false;
        } else if (qName.equals("data")) {
            datasheet.addDataItem(tmpData);
            tmpData = null;
        }
    }
    public void characters(char[] ch, int start, int length)  throws SAXException {
        String str = new String(ch, start, length).trim();
        if (isX) {
            tmpData.setX(Double.parseDouble(str));
        } else if (isY) {
            tmpData.setY(Double.parseDouble(str));
        }
    }
    public void warning(SAXParseException e) throws SAXException {
        System.err.println("Warning " + e);
        System.err.println("line = " + e.getLineNumber() + ", col = " + e.getColumnNumber());
    }
    public void error(SAXParseException e) throws SAXException {
        System.err.println("Error " + e);
        System.err.println("line = " + e.getLineNumber() + ", col = " + e.getColumnNumber());
    }
    public void fatalError(SAXParseException e) throws SAXException {
        System.err.println("Fatal Error " + e);
        System.err.println("line = " + e.getLineNumber() + ", col = " + e.getColumnNumber());
    }

}