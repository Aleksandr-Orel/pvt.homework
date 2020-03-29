package model;

public class Message {
    private int id;
    private String destinationAddress;
    private String messageHeading;
    private String messageText;

    public int getId() {
        return id;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public String getMessageHeading() {
        return messageHeading;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public void setMessageHeading(String messageHeading) {
        this.messageHeading = messageHeading;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    @Override
    public String toString() {
        return "Message:: id = " + this.id + "; destination address = " + this.destinationAddress
                + "; message heading = " + this.messageHeading + "; text of message = " + this.messageText;
    }
}
