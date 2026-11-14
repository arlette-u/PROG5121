import java.util.Locale;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

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
        long number = (long) (Math.random() * 9000000000L) + 1000000000L;
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
        if (messageOption.equals("Send")) {
            return "message sent";
        } else if (messageOption.equals("Store")) {
            return "Message stored";
        } else if (messageOption.equals("Disregard")) {
            return "Press 0 to delete message";
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
            String existingJson = "";
            if (Files.exists(Path.of("message.json"))) {
                existingJson = Files.readString(
                        Path.of("message.json"), StandardCharsets.UTF_8);
            }
            String newMessage = "{\n" + "\"messageID\": \"" + escapeJson(messageID) + "\",\n"
           + "{\n" + "\"messageNumber\": \"" + numberOfMessages+",\n"
                    +"{\n" + "\"recipient\": \""+escapeJson(recipientCell)+"\",\n"
                    +"{\n" + "\"message\": \""+escapeJson(message)+"\",\n"
                    +"{\n" + "\"messageHash\": \""+escapeJson(messageHash)+"\"\n"+"}";

            String updatedJson;
            if(existingJson.isBlank() || existingJson.trim().equals("[]")){
                updatedJson ="[\n"+newMessage+"\n]";
            }else {
                existingJson=existingJson.trim();
                existingJson=existingJson.substring(0,existingJson.length()-1);
                updatedJson=existingJson + ",\n" +newMessage + "\n]";
            }
            Files.writeString(
                    Path.of("message.json"), updatedJson,StandardCharsets.UTF_8);
            return "Mmessages stored successfully.";

        }catch (IOException e){
            return "Message could not be stored.";
        }
        }
        private String escapeJson(String text){
            return text.replace("\\","\\\\")
                    .replace("\"","\\\"")
                            .replace("\n","\\n")
                    .replace("\r","\\r");

        }

    }


