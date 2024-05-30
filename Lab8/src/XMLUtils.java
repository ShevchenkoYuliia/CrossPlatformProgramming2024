import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.lang.reflect.Method;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class XMLUtils {
    public static <T> void exportToXML(List<T> objects, String rootElementName, String filePath) throws Exception {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element root = document.createElement(rootElementName);
        document.appendChild(root);

        for (T obj : objects) {
            Element objectElement = document.createElement(obj.getClass().getSimpleName().toLowerCase());
            root.appendChild(objectElement);

            for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(obj.getClass(), Object.class).getPropertyDescriptors()) {
                Method getter = propertyDescriptor.getReadMethod();
                if (getter != null) {
                    Object value = getter.invoke(obj);
                    Element propertyElement = document.createElement(propertyDescriptor.getName());
                    propertyElement.appendChild(document.createTextNode(value.toString()));
                    objectElement.appendChild(propertyElement);
                }
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(filePath));

        transformer.transform(domSource, streamResult);
    }

    public static void main(String[] args) {
        try {
            List<Participant> participants = Arrays.asList(
                    new Participant("Alice", "alice@example.com", "University A"),
                    new Participant("Bob", "bob@example.com", "University B")
            );
            exportToXML(participants, "participants", "participants.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}