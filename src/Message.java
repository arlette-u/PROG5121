public class Message {

    private String messageID;
    private int messageNumber;
    private String recipientCell;
    private String message;


    public Message (String messageID, int messageNumber, String recipientCell, String message){
        this.messageID=messageID;
        this.messageNumber=messageNumber;
        this.recipientCell=recipientCell;
        this.message=message;

    }

    public boolean checkMessageID(){
        return messageID.length() <=10;
    }
    public boolean checkRecipientCell(){
        return recipientCell.startsWith("+27")&& recipientCell.length()==12;
    }
    public boolean checkMessageNumber(){
        return messageNumber <=250;
    }

}
