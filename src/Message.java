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
    private String sender;


    public Message(String messageID, int numberOfMessages, String recipientCell, String message, String messageHash) {
        this.messageID = messageID;
        this.numberOfMessages = numberOfMessages;
        this.recipientCell = recipientCell;
        this.message = message;
        this.messageHash = messageHash;

    }
    public void setSender(String sender){
        this.sender=sender;
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
    public void displaySentMessages(String[] sentMessageArray) {

        boolean messagesFound = false;

        for (int i = 0; i < sentMessageArray.length; i++) {

            if (sentMessageArray[i] != null) {
                System.out.println(sentMessageArray[i]);
                messagesFound = true;
            }
        }
        if (!messagesFound) {
            System.out.println("No messages sent yet");
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
            String newMessage = "messageID:" + escapeJson(messageID) + "\n"
                     + "messageNumber" + numberOfMessages + "\n"
                    +"recipient:"  + escapeJson(recipientCell) + "\n"
                    +"message:"  + escapeJson(message) + "\n"
                    + "messageHash:" + escapeJson(messageHash) + "\n"
                    +"sender:" + escapeJson(sender) + "\n" ;

            String updatedJson;
            if (existingJson.isBlank() || existingJson.trim().equals("[]")) {
                updatedJson = "[\n" + newMessage + "\n]";
            } else {
                existingJson = existingJson.trim();
                existingJson = existingJson.substring(0, existingJson.length() - 1);
                updatedJson = existingJson + ",\n" + newMessage + "\n]";
            }
            Files.writeString(
                    Path.of("message.json"), updatedJson, StandardCharsets.UTF_8);
            return "Mmessages stored successfully.";

        } catch (IOException e) {
            return "Message could not be stored.";
        }
    }

    private String escapeJson(String text) {
        return text.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");

    }

    public String getLongestStoredMessage(String[] storedMessageArray) {
        String longestMessage = "";
        for (int i = 0; i < storedMessageArray.length; i++) {
            if (storedMessageArray[i] != null && storedMessageArray[i].length() > longestMessage.length()) {
                longestMessage = storedMessageArray[i];
            }
        }
        if (longestMessage.isEmpty()) {
            return "No stored messages Found.";
        }
        return longestMessage;
    }

    public String searchMessageID(String[] storedMessageArray, String[] messageIDArray, String searchID) {
        for (int i = 0; i < storedMessageArray.length; i++) {
            if (storedMessageArray[i] != null && messageIDArray[i] != null && messageIDArray[i].equals(searchID)) {
                return storedMessageArray[i];
            }
        }
        return "Message ID not found";
    }

    public String searchMessagesRecipient(String[] storedMessageArray, String[] recipientArray, String[] searchRecipient) {
        String results = "";
        for (int i = 0; i < storedMessageArray.length; i++) {
            if (storedMessageArray[i] !=null
            && recipientArray[i]!=null && recipientArray.equals(searchRecipient)){
                results += storedMessageArray[i] +"\n";
            }
        }
        if (results.isEmpty()) {
            return "No message for this recipient";
        }
        return results;

    }
    public String deleteMessageUsingHash (String[]storedMessageArray, String [] messageHashArray, String deleteHash){
        for (int i=0; i<storedMessageArray.length; i++){
            if (storedMessageArray[i]!=null
            && messageHashArray[i]!=null && messageHashArray.equals(deleteHash)){
                storedMessageArray[i]=null;
                messageHashArray[i]=null;
                return "Message deleted successfully";

            }
        }
        return "Message not found.";
    }
    public String displayStoredSenderAndRecipients(String []storedMessageArray, String [] senderArray, String []recipientArray){
        String results="";
        for (int i=0; i< storedMessageArray.length; i++){
            if (storedMessageArray[i]!=null){
                results += "Sender:" + senderArray[i] + "\nRecpient:" +recipientArray[i];
            }
        }
        if (results.isEmpty()){
            return "No stored message found.";
        }
        return results;
    }
    public String displayFullReport(String []storedMessageArray, String []messageIDArray, String []messageHashArray, String[]senderArray, String []recipientArray){
        String report="";
        for (int i=0; i<storedMessageArray.length; i++){
            if (storedMessageArray[i]!=null) {
                report += "Message ID:" + messageIDArray[i]
                        + "\nMessage Hash:" + messageHashArray[i]
                        + "\nSender:" + senderArray[i]
                        + "\nRecipient:" + recipientArray[i];
            }
            }
        if (report.isEmpty()){
            return " No stored Messages found.";
        }
        return report;
    }
    public void loadStoredMessages(String []storedMessageArray, String[] messageIDArray, String [] messageHashArray, String [] recipientArray, String [] senderArray){
        try{
            String json =Files.readString(Path.of("message.json"), StandardCharsets.UTF_8);

            java.util.regex.Pattern objectPattern=java.util.regex.Pattern.compile("\\{(.*?)\\}", java.util.regex.Pattern.DOTALL);

            java.util.regex.Matcher objectMatcher= objectPattern.matcher(json);
            int index=0;

            while (objectMatcher.find()&& index <storedMessageArray.length){
                String object=objectMatcher.group();
                messageIDArray[index]=getJsonValue(object, "message ID");
                messageHashArray[index]=getJsonValue(object, "message hash");
                recipientArray[index]=getJsonValue(object, "recipient");
                storedMessageArray[index]=getJsonValue(object, "message");
                senderArray[index]=getJsonValue(object, "sender");
                index++;
            }
            System.out.println("Stored messages have been loaded successfully");
        } catch (IOException e){
            System.out.println("Could not read messages.json");
        }
    }

    private String getJsonValue(String json, String key){
        String search= "\"" + key +"\";";
        int start =json.indexOf(search);
        if (start==1){
            return "";
        }

        start+= search.length();

        int firstQuote=json.indexOf("\"", start);
        int lastQuote=json.indexOf("\"", firstQuote+1);

        if (firstQuote==-1 || lastQuote==-1){
            return "";
        }
        return json.substring(firstQuote +1,lastQuote);
    }
}




