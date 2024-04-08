package task5;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import task4.BabyInfo;

import java.io.File;
public class XMLReader {
    public static void main(String[] args) {
        try {
            File inputFile = new File("Popular_Baby_Names2.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nameList = doc.getElementsByTagName("names");
            for (int temp = 0; temp < nameList.getLength(); temp++) {
                Node nNode = nameList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    String gender = eElement.getElementsByTagName("gender").item(0).getTextContent();
                    int count = Integer.parseInt(eElement.getElementsByTagName("count").item(0).getTextContent());
                    int rating = Integer.parseInt(eElement.getElementsByTagName("rating").item(0).getTextContent());

                    BabyInfo baby = new BabyInfo(name, gender, count, rating);
                    System.out.println(baby);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
