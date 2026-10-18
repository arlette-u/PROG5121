public class Message {

    private String messageID;
    private int numberOfMessages;
    private String recipientCell;
    private String message;
    private String messageHash;


    public Message (String messageID, int numberOfMessages, String recipientCell, String message, String messageHash){
        this.messageID=messageID;
        this.numberOfMessages=numberOfMessages;
        this.recipientCell=recipientCell;
        this.message=message;
        this.messageHash=messageHash;

    }

    public boolean checkMessageID(){
        return messageID.length() <=10;
    }
    public String checkRecipientCell(){
        if(recipientCell.startsWith("+27")&& recipientCell.length()==12){
            return "Cellphone number successsfully captured";
        }
        else {
            return "cell phone number incorrectly formatted";
        }
    }
    public boolean checkNumberOfMessages(){
        return numberOfMessages>0;
    }
    public boolean checkMessageLength(){
        return message.length() >=250;
    }
    public String generateMessageID(){
        long number = (long)(Math.random() * 9000000000l) +1000000000L;
        messageID = String.valueOf(number);
        return messageID;

    }
    public String createMessageHash(){

        String firstTwo = messageID.substring(0,2);
        String hash= firstTwo + ":" + numberOfMessages + ":" + message;
        messageHash=hash;
        return messageHash;
    }
    public String SentMessage(String messageOption){
        if (messageOption.equals("send")){
            return "message sent";
        }
        else if(messageOption.equals("store")){
            return "Message stored";
        }
        else if (messageOption.equals("disregard option")){
            return "Message Disregarded";
        }
        else{
            return"Invalid Option";
        }
    }
    public String printMessage(){
        return "Message ID" + messageID
                +"Message number:" + numberOfMessages
                + "Recipient:" + recipientCell
                + "Message:" + message
                + "Message Hash:" + messageHash;
    }
}
