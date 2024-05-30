import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class ConferenceServerGUI implements ConferenceRegistration {
    private List<Participant> participants;

    public ConferenceServerGUI() {
        participants = new ArrayList<>();
    }

    public void registerParticipant(Participant participant) throws RemoteException {
        participants.add(participant);
        System.out.println("Registered participant: " + participant.getName());
    }

    public void exportParticipantsToXML(String filePath) throws RemoteException {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("participants");
            document.appendChild(root);

            for (Participant participant : participants) {
                Element participantElement = document.createElement("participant");

                participantElement.appendChild(createElement(document, "name", participant.getName()));
                participantElement.appendChild(createElement(document, "email", participant.getEmail()));
                participantElement.appendChild(createElement(document, "organization", participant.getOrganization()));

                root.appendChild(participantElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(filePath));

            transformer.transform(domSource, streamResult);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Error exporting to XML", e);
        }
    }

    public void importParticipantsFromXML(String filePath) throws RemoteException {
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            Element root = (Element) doc.getElementsByTagName("participants").item(0);

            participants.clear();

            for (int i = 0; i < root.getElementsByTagName("participant").getLength(); i++) {
                Element participantElement = (Element) root.getElementsByTagName("participant").item(i);

                String name = participantElement.getElementsByTagName("name").item(0).getTextContent();
                String email = participantElement.getElementsByTagName("email").item(0).getTextContent();
                String organization = participantElement.getElementsByTagName("organization").item(0).getTextContent();

                participants.add(new Participant(name, email, organization));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Error importing from XML", e);
        }
    }

    private Element createElement(Document doc, String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(textContent));
        return element;
    }

    public static void main(String[] args) {
        try {
            ConferenceServerGUI server = new ConferenceServerGUI();
            ConferenceRegistration stub = (ConferenceRegistration) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ConferenceRegistration", stub);

            System.out.println("Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
