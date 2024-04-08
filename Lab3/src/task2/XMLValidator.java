package task2;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
public class XMLValidator {
    public static void main(String[] args) {
        String fileXSD ="Popular_Baby_Names_NY.xsd";
        String fileXML ="Popular_Baby_Names_NY.xml";
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(fileXSD));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(fileXML)));
            System.out.println(fileXSD + " corresponds to a "+ fileXML );
        } catch (Exception e) {
            System.out.println( fileXSD + " do NOT corresponds to a "+ fileXML + ":");
            System.out.println(e.getMessage());
        }
    }
}
