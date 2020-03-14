package runner;
import dom.DomParser2;
import model.Message;
import sax.MessagesHandler;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import stax.StaxParser2;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class RunMessages {
    private static final String MESSAGES_XML = "messages.xml";

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException,
            XMLStreamException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        MessagesHandler messagesHandler = new MessagesHandler();
        saxParser.parse(new File(MESSAGES_XML), messagesHandler);
        List<Message> messages = messagesHandler.getMessages();
        messages.forEach(country -> System.out.println(country));

        System.out.println("=====================================");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(MESSAGES_XML);
        messages = new DomParser2().parse(document);
        messages.forEach(employee -> System.out.println(employee));

        System.out.println("=====================================");

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(MESSAGES_XML));
        messages = new StaxParser2().parse(xmlEventReader);
        messages.forEach(employee -> System.out.println(employee));
    }
}
