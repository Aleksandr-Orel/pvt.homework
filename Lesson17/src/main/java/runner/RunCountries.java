package runner;
import dom.DomParser;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
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
import model.Country;
import stax.StaxParser;
import sax.CountriesHandler;

public class RunCountries {
    private static final String COUNTRIES_XML = "countries.xml";

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException,
            XMLStreamException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        CountriesHandler countriesHandler = new CountriesHandler();
        saxParser.parse(new File(COUNTRIES_XML), countriesHandler);
        List<Country> countries = countriesHandler.getCountries();
        countries.forEach(country -> System.out.println(country));

        System.out.println("=====================================");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(COUNTRIES_XML);
        countries = new DomParser().parse(document);
        countries.forEach(employee -> System.out.println(employee));

        System.out.println("=====================================");

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(COUNTRIES_XML));
        countries = new StaxParser().parse(xmlEventReader);
        countries.forEach(employee -> System.out.println(employee));
    }
}
