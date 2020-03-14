package sax;
import model.Message;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

public class MessagesHandler extends DefaultHandler {
    private List<Message> messages;
    private Message message;
    private boolean isDestinationAddress = false;
    private boolean isMessageHeading = false;
    private boolean isMessageText = false;

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        String currentTag = qName;
        switch (currentTag) {
            case "Message": {
                String id = attributes.getValue("id");
                message = new Message();
                message.setId(Integer.parseInt(id));
                if (messages == null) {
                    messages = new ArrayList<>();
                }
            } break;
            case "destinationAddress": {
                isDestinationAddress = true;
            } break;
            case "messageHeading": {
                isMessageHeading = true;
            } break;
            case "messageText": {
                isMessageText = true;
            } break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("Message")) {
            messages.add(message);
        }
    }

    @Override
    public void characters(char characters[], int start, int length) {
        if (isDestinationAddress) {
            message.setDestinationAddress(new String(characters, start, length));
            isDestinationAddress = false;
        } else if(isMessageHeading) {
            message.setMessageHeading(new String(characters, start, length));
            isMessageHeading = false;
        } else if(isMessageText) {
            message.setMessageText(new String(characters, start, length));
            isMessageText = false;
        }
    }
}
