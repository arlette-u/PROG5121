import java.util.Scanner;
void main(String[]args ) {
    Scanner scanner = new Scanner(System.in);

    String name;
    String lastName;
    String username;
    String password;
    String cellPhoneNumber;


    System.out.println("Enter your first name.");
    name=scanner.nextLine();

    System.out.println("Enter your last name");
    lastName=scanner.nextLine();
//using a while loop only breaking when captured
    while (true) {
        System.out.println("Create a username(should contain an underscore and is no more than 5 characters long)");
        username = scanner.nextLine();
        if (username.length() <= 5 && username.contains("_")) {
            System.out.println("Username successfully captured");
            break;
        } else {
            System.out.println("Username is not correctly formatted; please ensure that your username contains and underscore and is not more than five characters in length");

        }
    }
    System.out.println("Create a password(should contain a minimum of 8 letters, a number and a special character");
    while (true) {
        password = scanner.nextLine();
        if (password.length() >= 8) {
            boolean hasNumber = false;
            boolean hasCapital = false;
            boolean hasCharacter = false;

            for (int i = 0; i < password.length(); i++) {
                char character = password.charAt(i);

                if (Character.isDigit(character)) {
                    hasNumber = true;
                }
                if (Character.isUpperCase(character)) {
                    hasCapital = true;
                }
                if (!Character.isLetterOrDigit(character)) {
                    hasCharacter = true;
                }
            }
            if (hasNumber && hasCapital && hasCharacter) {
                System.out.println("Password successful captured");
                break;

            } else {
                System.out.println("Password is not formatted correctly;please ensure that the password contains atleast eight chracters, a capital letter, a number and a special character.");
            }
        } else {
            System.out.println("Password is not formatted correctly;please ensure that the password contains atleast eight charcters, a capital letter, a number and a special character.");
        }
    }
    while (true) {
        System.out.println("Enter your South African Cellphone Number such contain (+27)");
        cellPhoneNumber = scanner.nextLine();
        if (cellPhoneNumber.length() == 12
                && cellPhoneNumber.charAt(0) == '+'
                && cellPhoneNumber.charAt(1) == '2'
                && cellPhoneNumber.charAt(2) == '7') {
            System.out.println("Cell phone number successfully added");
            break;
        } else {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
        }
    }
    //LOGIN SECTION
    Login login = new Login(name, lastName, username, password, cellPhoneNumber);
    System.out.println("registration successful");
    while (true) {
        System.out.println("Enter your username");
        String enteredUsername = scanner.nextLine();

        System.out.println("Enter your password");
        String enteredPassword = scanner.nextLine();

        boolean loginSuccessful = login.loginUser(enteredUsername, enteredPassword);
        System.out.println(login.returnLoginStatus(loginSuccessful));

        if (loginSuccessful) {
            break;
        }
    }
    scanner.close();
}





