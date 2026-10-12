import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginTest {

    @Test
    @DisplayName("Username correctly formatted")
    void testUsernameCorrectlyFormatted() {
        Login login = new Login("kyl_1", "Ch&2&sec@ke99!", "+27838968976");
        assertTrue(login.checkUserName());
    }

    @Test
    @DisplayName("Username incorrectly formatted")
    void testUsernameIncorrectlyFormatted() {
        Login login = new Login("kyle!!!!!!!", "Ch&2&sec@ke99!", "+27838968976");
        assertFalse(login.checkUserName());
    }

    @Test
    @DisplayName("Password meets complexity requirements")
    void testPasswordMeetsComplexity() {
        Login login = new Login("kyl_1", "Ch&2&sec@ke99!", "+27838968976");
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    @DisplayName("Password doe not meet complexity requirements")
    void testPasswordDoesNotMeetComplexity() {
        Login login = new Login("kyl_1", "password", "+27838968976");
        assertFalse(login.checkPasswordComplexity());
    }

    @Test
    @DisplayName("Cellphone number correctly formatted")
    void testCellPhoneCorrectlyFormatted() {
        Login login = new Login("kyl_1", "Ch&2&sec@ke99!", "+27838968976");
        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    @DisplayName("Cellphone number incorrectly formatted")
    void testCellPhoneIncorrectlyFormatted() {
        Login login = new Login("kyl_1", "Ch&2&sec@ke99!", "08966553");
        assertFalse(login.checkCellPhoneNumber());
    }

    @Test
    @DisplayName("Login successful")
    void testLoginSuccessful() {
        Login login = new Login("kyl_1", "Ch&2&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&2&sec@ke99!"));
    }

    @Test
    @DisplayName("Login failed")
    void testLoginFailed() {
        Login login = new Login("kyl_1", "Ch&2&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrong password"));
    }
}