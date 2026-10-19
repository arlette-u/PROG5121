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

    assertEquals("Cell Phone number successfully captured", message.checkRecipientCell());
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

        assertEquals("message diregarded", message.SentMessage("Disregard"));
        assertEquals(1, message.returnTotalMessages());
        }



    }

