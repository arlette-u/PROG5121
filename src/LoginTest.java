import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginTest {

    @Test
    void testUsernameCorrectlyFormatted() {
        Login login = new Login("kyl_1", "Ch&2&sec@ke99!", "+27838968976");
        assertTrue(login.checkUserName());
    }

    @Test
    void testUsernameIncorrectlyFormatted() {
        Login login = new Login("kyle!!!!!!!", "Ch&2&sec@ke99!", "+27838968976");
        assertFalse(login.checkUserName());
    }

    @Test
    void testPasswordMeetsComplexity() {
        Login login = new Login("kyl_1", "Ch&2&sec@ke99!", "+27838968976");
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    void testPasswordDoesNotMeetComplexity() {
        Login login = new Login("kyl_1", "password", "+27838968976");
        assertFalse(login.checkPasswordComplexity());
    }

    @Test
    void testCellPhoneCorrectlyFormatted() {
        Login login = new Login("kyl_1", "Ch&2&sec@ke99!", "+27838968976");
        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    void testCellPhoneIncorrectlyFormatted() {
        Login login = new Login("kyl_1", "Ch&2&sec@ke99!", "08966553");
        assertFalse(login.checkCellPhoneNumber());
    }

    @Test
    void testLoginSuccessful() {
        Login login = new Login("kyl_1", "Ch&2&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&2&sec@ke99!"));
    }

    @Test
    void testLoginFailed() {
        Login login = new Login("kyl_1", "Ch&2&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrong password"));
    }
}