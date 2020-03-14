package dom;
import model.Message;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DomParser2 {
    public List<Message> parse(Document document) throws FileNotFoundException, XMLStreamException {
        NodeList countryElements = document.getElementsByTagName("Message");
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < countryElements.getLength(); i++) {
            messages.add(getMessage(countryElements.item(i)));
        }
        return  messages;
    }

    private static Message getMessage(Node node) {
        Message country = new Message();
        Element element = (Element) node;
        country.setId(Integer.parseInt(getAttributeValue("id", element)));
        country.setDestinationAddress(getTagValue("destinationAddress", element));
        country.setMessageHeading(getTagValue("messageHeading", element));
        country.setMessageText(getTagValue("messageText", element));
        return country;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    private static String getAttributeValue(String tag, Element element) {
        NamedNodeMap nodeMap = element.getAttributes();
        Node node = nodeMap.item(0);
        return node.getNodeValue();
    }
}
