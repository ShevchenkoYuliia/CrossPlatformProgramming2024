package task4;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XMLWriter {
    public static void writeXML(List<BabyInfo> nameInfoList, String filePath) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("List");
            doc.appendChild(rootElement);
            for (BabyInfo nameInfo : nameInfoList) {
                Element nameElement = doc.createElement("names");
                rootElement.appendChild(nameElement);
                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(nameInfo.getName()));
                nameElement.appendChild(name);
                Element gender = doc.createElement("gender");
                gender.appendChild(doc.createTextNode(nameInfo.getGender()));
                nameElement.appendChild(gender);
                Element count = doc.createElement("count");
                count.appendChild(doc.createTextNode(String.valueOf(nameInfo.getCount())));
                nameElement.appendChild(count);
                Element rating = doc.createElement("rating");
                rating.appendChild(doc.createTextNode(String.valueOf(nameInfo.getRating())));
                nameElement.appendChild(rating);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
            System.out.println("XML file saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
