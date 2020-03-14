package dom;
import model.Country;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DomParser {
    public List<Country> parse(Document document) throws FileNotFoundException, XMLStreamException {
        NodeList countryElements = document.getElementsByTagName("Country");
        List<Country> countries = new ArrayList<>();
        for (int i = 0; i < countryElements.getLength(); i++) {
            countries.add(getCountry(countryElements.item(i)));
        }
        return  countries;
    }

    private static Country getCountry(Node node) {
        Country country = new Country();
        Element element = (Element) node;
        country.setId(Integer.parseInt(getAttributeValue("Id", element)));
        country.setCode(getTagValue("Code", element));
        country.setName(getTagValue("Name", element));
        country.setDescription(getTagValue("Description", element));
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
