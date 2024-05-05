package myApplication;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import beans.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DataSheetToXML {

    public static void saveXMLDoc(Document doc, String fileName) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "Windows-1251");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(fileName.trim()));

        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        System.out.println("File " + fileName.trim() + " saved!");

    }

    public static Document createDataSheetDOM(DataSheet dataSheet) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Element rootElement = doc.createElement("jb_data");
        doc.appendChild(rootElement);
        Element root = doc.createElement("dataTable");
        rootElement.appendChild(root);

        for (int i = 0; i < dataSheet.size(); i++) {
            Element data = doc.createElement("dataPoint");
            root.appendChild(data);
            Data d = dataSheet.getDataItem(i);
            Attr attr = doc.createAttribute("date");
            attr.setValue(d.getDate());
            data.setAttributeNode(attr);
            Element x = doc.createElement("x");
            data.appendChild(x);
            x.appendChild(doc.createTextNode(d.getX() + ""));
            Element y = doc.createElement("y");
            data.appendChild(y);
            y.appendChild(doc.createTextNode(d.getY() + ""));
        }
        return doc;
    }

}