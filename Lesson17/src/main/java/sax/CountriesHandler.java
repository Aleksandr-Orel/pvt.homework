package sax;
import model.Country;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

public class CountriesHandler extends DefaultHandler {
    private List<Country> countries;
    private Country country;
    private boolean isCode = false;
    private boolean isName = false;
    private boolean isDescription = false;

    public List<Country> getCountries() {
        return countries;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        String currentTag = qName;
        switch (currentTag) {
            case "Country": {
                String id = attributes.getValue("id");
                country = new Country();
                country.setId(Integer.parseInt(id));
                if (countries == null) {
                    countries = new ArrayList<>();
                }
            } break;
            case "Code": {
                isCode = true;
            } break;
            case "Name": {
                isName = true;
            } break;
            case "Description": {
                isDescription = true;
            } break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("Country")) {
            countries.add(country);
        }
    }

    @Override
    public void characters(char characters[], int start, int length) {
        if (isCode) {
            country.setCode(new String(characters, start, length));
            isCode = false;
        } else if(isName) {
            country.setName(new String(characters, start, length));
            isName = false;
        } else if(isDescription) {
            country.setDescription(new String(characters, start, length));
            isDescription = false;
        }
    }
}
