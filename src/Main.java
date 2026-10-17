import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String name;
        String lastName;
        String username;
        String password;
        String cellPhoneNumber;

        System.out.println("Enter your name:");
        name = scanner.nextLine();

        System.out.println("Enter your last name:");
        lastName = scanner.nextLine();

// Username registration
        while (true) {

            System.out.println("Create a username (must contain an underscore and be no more than 5 characters):");
            username = scanner.nextLine();

            Login login = new Login(username, "", "");

            if (login.checkUserName()) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters.");
            }
        }

// Password creation
        while (true) {

            System.out.println("Create a password (minimum 8 characters, including a capital letter, a number, and a special character):");
            password = scanner.nextLine();

            Login login = new Login(username, password, "");

            if (login.checkPasswordComplexity()) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password is not correctly formatted. Please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.");
            }
        }

// Cellphone number registration
        while (true) {

            System.out.println("Enter your South African cellphone number (+27):");
            cellPhoneNumber = scanner.nextLine();

            Login login = new Login(username, password, cellPhoneNumber);

            if (login.checkCellPhoneNumber()) {
                System.out.println("Cell phone number successfully added.");
                break;
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain national code.(+27)");
            }
        }


        Login login = new Login(username, password, cellPhoneNumber);

        System.out.println("Registration successful!");

// LOGIN SECTION
        while (true) {

            System.out.println("Enter your username:");
            String enteredUsername = scanner.nextLine();

            System.out.println("Enter your password:");
            String enteredPassword = scanner.nextLine();

            boolean loginSuccessful =
                    login.loginUser(enteredUsername, enteredPassword);

            System.out.println(login.returnLoginStatus(loginSuccessful));

            if (loginSuccessful) {
                break;
            }
        }

        scanner.close();
    }
}
