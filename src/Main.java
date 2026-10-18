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

            Login login = new Login( username, "", "");

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
        //Part 2 POE
        // QuickChat
        int selectOption;
        String messsage = "";
        int numberOfMessages= 1;
        String messageHash = "";
        Message messageObject = null;
        

            System.out.println("Welcome to QuickChat");
            System.out.println("Please select an option");
            System.out.println("1. Send messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");

            selectOption = scanner.nextInt();

            if (selectOption==1){
                System.out.println("you selected to send messages");
                System.out.println("How many messages would you like to send");
                numberOfMessages=scanner.nextInt();
                
                for (int i=1; i<= numberOfMessages; i++){
                    int messageNumber=i;

                    System.out.println("Enter Message ID");
                    String messageID = scanner.nextLine();
                    System.out.println("Enter recipient Cell phone number");
                    String recipientCell= scanner.nextLine();
                    System.out.println("Enter your message");
                    String message=scanner.nextLine();



                    Message meessage=new Message(messageID, numberOfMessages , recipientCell, messsage, messageHash);
                    
                    System.out.println(messageObject.checkMessageID());
                    System.out.println(messageObject.checkRecipientCell());
                    System.out.println(messageObject.checkNumberOfMessages());

                    if (messageObject.checkMessageLength()){
                        System.out.println("Message sent.");
                    }
                    else{
                        System.out.println("Please enter a message that is less than 250 words");
                    }

                }
            }
            else if (selectOption==2){
                System.out.println("Coming soon.");
            }
            else if (selectOption==3){
                System.out.println("Goodbye");
            }
            else{
                System.out.println("Invalid option");
            }

        scanner.close();
    }

}

