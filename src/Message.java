import java.util.Locale;
import java.io.FileWriter;
import java.io.IOException;

public class Message {

    private String messageID;
    private int numberOfMessages;
    private String recipientCell;
    private String message;
    private String messageHash;


    public Message(String messageID, int numberOfMessages, String recipientCell, String message, String messageHash) {
        this.messageID = messageID;
        this.numberOfMessages = numberOfMessages;
        this.recipientCell = recipientCell;
        this.message = message;
        this.messageHash = messageHash;

    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipientCell.startsWith("+27") && recipientCell.length() == 12) {
            return "Cellphone number successfully entered";
        } else {
            return "cell phone number incorrectly formatted";
        }
    }

    public boolean checkNumberOfMessages() {
        return numberOfMessages > 0;
    }

    public boolean checkMessageLength() {
        return message.length() <= 250;
    }

    public String generateMessageID() {
        long number = (long) (Math.random() * 9000000000l) + 1000000000L;
        messageID = String.valueOf(number);
        return messageID;

    }

    public String createMessageHash() {

        String[] words = message.split(" ");

        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();

        messageHash = messageID.substring(0, 2)
                + ":" + numberOfMessages + ":" + firstWord + lastWord;
        return messageHash;
    }

    public String SentMessage(String messageOption) {
        if (messageOption.equals("send")) {
            return "message sent";
        } else if (messageOption.equals("store")) {
            return "Message stored";
        } else if (messageOption.equals("disregard option")) {
            return "Message Disregarded";
        } else {
            return "Invalid Option";
        }
    }

    public String printMessage() {
        return "Message ID" + messageID
                + "Message number:" + numberOfMessages
                + "Recipient:" + recipientCell
                + "Message:" + message
                + "Message Hash:" + messageHash;
    }

    public int returnTotalMessages() {
        return numberOfMessages;
    }

    public String storeMessages() {
        try {

            FileWriter writer = new FileWriter("messages.json");

            writer.write("{\n");
            writer.write(" \"messageID\": \"" + messageID + "\",\n");
            writer.write(" \"messageNumber\": " + numberOfMessages + ",\n");
            writer.write(" \"recipient\": \"" + recipientCell + "\",\n");
            writer.write(" \"message\": \"" + message + "\",\n");
            writer.write(" \"messageHash\": \"" + messageHash + "\"\n");
            writer.write("}");

            writer.close();

            return "Message stored successfully.";

        } catch (IOException e) {

            return "Message could not be stored.";
        }

    }
}
