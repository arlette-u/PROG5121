import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
//test data 1
    @Test
    @DisplayName("Test task 1- Send message")
     void  testSentMessage(){
    Message message = new Message("", 1, "+2718693002", "Hi Mike, can you join us for dinner tonight?", "");

   String messageID=message.generateMessageID();
    String messageHash= message.generateMessageID();
    assertTrue(message.checkMessageID());
    assertNotNull(messageHash);
    assertNotNull(messageID);

    assertEquals("cell phone number incorrectly formatted", message.checkRecipientCell());
    assertEquals("message sent", message.SentMessage("Send"));
    assertEquals(1, message.returnTotalMessages());
    }

    @Test
    @DisplayName("Test task 2- message")
        void testSentMessage2(){
        Message message =new Message ("", 2, "0857595889", "Hi Keagan, did you recieve payment?", "");

        String messageID=message.generateMessageID();
        String messageHash= message.generateMessageID();

        assertTrue(message.checkMessageID());
        assertNotNull(messageHash);
        assertNotNull(messageID);

        assertEquals("Press 0 to delete message", message.SentMessage("Disregard"));
        assertEquals(2, message.returnTotalMessages());
        }

    }

    // Test 1 - Send message
    @Test
    @DisplayName("Test task 1 - Send message")
    void testSentMessage() {

        Message message = new Message("", 1, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?", "");

        String messageID = message.generateMessageID();
        String messageHash = message.createMessageHash();

        assertTrue(message.checkMessageID());
        assertNotNull(messageHash);
        assertNotNull(messageID);

        assertEquals("Cellphone number successfully entered",message.checkRecipientCell());

        assertEquals("message sent", message.SentMessage("Send"));
        assertEquals(1, message.returnTotalMessages());
    }

    // Test 3 - Store message
    @Test
    @DisplayName("Test task 3 - Store message")
    void testStoreMessage() {

        Message message = new Message("", 3, "0821234567",
                "Where are you? You are late! I have asked you to be on time.", "");

        String messageID = message.generateMessageID();
        String messageHash = message.createMessageHash();

        assertTrue(message.checkMessageID());
        assertNotNull(messageID);
        assertNotNull(messageHash);

        assertEquals("cell phone number incorrectly formatted", message.checkRecipientCell());
    }

    // Test 4 - Check message ID
    @Test
    @DisplayName("Test task 4 - Check message ID")
    void testMessageID() {

        Message message = new Message("", 4, "0821234567",
                "It is dinner time!", "");

        String messageID = message.generateMessageID();

        assertNotNull(messageID);
        assertTrue(message.checkMessageID());
    }

    // Test 5 - Check message hash
    @Test
    @DisplayName("Test task 5 - Check message hash")
    void testMessageHash() {

        Message message = new Message("", 5, "0821234567",
                "Ok, I am leaving without you.", "");

        String messageID=message.generateMessageID();
        String messageHash = message.createMessageHash();

        assertNotNull(messageID);
        assertNotNull(messageHash);
        assertFalse(messageHash.isEmpty());
    }

void main() {
}



