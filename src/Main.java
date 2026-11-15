import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        String name;
        String lastName;
        String username;
        String password;
        String cellPhoneNumber;
        int selectOption;
        int numberOfMessages = 0;
        String messageHash = "";
        String messageOption = "";
        Message messageObject = new Message("", 0, "", "", "");

        //Array declaration (used in part 3)
        String[] sentMessageArray;
        String[] disregardedMessageArray;
        String[] storedMessageArray = new String[0];
        String[] messageHashArray = new String[0];
        String[] messageIDArray = new String[0];
        String[] recipientArray = new String[0];
        String[] senderArray = new String[0];
        String[] searchrecipient;


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
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
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

        System.out.println("Welcome to QuickChat");
        do {
            System.out.println("Please select an option");
            System.out.println("1. Send messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.println("4.Stored Messages");

            selectOption = scanner.nextInt();

            if (selectOption == 1) {
                System.out.println("you selected to send messages");
                System.out.println("How many messages would you like to send");
                numberOfMessages = scanner.nextInt();
                scanner.nextLine();

                sentMessageArray = new String[numberOfMessages];
                disregardedMessageArray = new String[numberOfMessages];
                storedMessageArray = new String[numberOfMessages];
                messageHashArray = new String[numberOfMessages];
                messageIDArray = new String[numberOfMessages];
                recipientArray = new String[numberOfMessages];
                senderArray = new String[numberOfMessages];
                searchrecipient = new String[numberOfMessages];

                messageObject.loadStoredMessages(storedMessageArray, messageIDArray, messageHashArray, recipientArray, senderArray);


                for (int i = 1; i <= numberOfMessages; i++) {
                    System.out.println("Enter recipient Cell phone number");
                    String recipientCell = scanner.nextLine();
                    recipientArray[i - 1] = recipientCell;
                    senderArray[i - 1] = username;

                    System.out.println("Enter your message");
                    String message = scanner.nextLine();

                    messageObject = new Message("", numberOfMessages, recipientCell, message, messageHash);

                    String messageID = messageObject.generateMessageID();
                    System.out.println("MessageID:" + messageID);
                    messageIDArray[i - 1] = messageID;


                    System.out.println(messageObject.checkMessageID());
                    System.out.println(messageObject.checkRecipientCell());
                    System.out.println(messageObject.checkNumberOfMessages());

                    if (messageObject.checkMessageLength()) {
                        messageHash = messageObject.createMessageHash();
                        System.out.println("Message Hash: " + messageHash);
                        messageHashArray[i - 1] = messageHash;

                        System.out.println("Send, Store or Disregard");
                        messageOption = scanner.nextLine().trim();
                        System.out.println(messageObject.SentMessage(messageOption));

                        if (messageOption.equals("Send")) {
                            sentMessageArray[i - 1] = message;
                            System.out.println(messageObject.SentMessage(messageOption));

                        } else if (messageOption.equals("Store")) {
                            storedMessageArray[i - 1] = message;
                            messageObject.setSender(username);
                            System.out.println(messageObject.storeMessages());


                        } else if (messageOption.equals("Disregard")) {
                            disregardedMessageArray[i - 1] = message;
                            System.out.println(messageObject.SentMessage(messageOption));

                        } else {
                            System.out.println("Invalid Option");
                        }

                    } else {
                        System.out.println("Please enter a message that is less than 250 characters");
                    }
                }

            } else if (selectOption == 2) {
                System.out.println("no messages sent yet");

            } else if (selectOption == 3) {
                System.out.println("Goodbye");

            } else if (selectOption == 4) {
                // idea
                System.out.println("1. Display senders and recipients");
                System.out.println("2. Display longest stored message");
                System.out.println("3. Search by messageID");
                System.out.println("4. Search messages by recipient cell");
                System.out.println("5. Delete Message using hash");
                System.out.println("6. Display full report");

                int storedChoice = scanner.nextInt();
                scanner.nextLine();

                if (storedChoice == 1) {
                    System.out.println(messageObject.displayStoredSenderAndRecipients(storedMessageArray, senderArray, recipientArray));
                } else if (storedChoice == 2) {
                    System.out.println("Longest stored message:" + " " + messageObject.getLongestStoredMessage(storedMessageArray));
                } else if (storedChoice == 3) {
                    System.out.println("Enter message ID");
                    String searchID = scanner.nextLine();
                    System.out.println(messageObject.searchMessageID(storedMessageArray, messageIDArray, searchID));
                } else if (storedChoice == 4) {
                    System.out.println("Enter recipient Number");
                    String searchRecipient = scanner.nextLine();
                    System.out.println(messageObject.searchMessagesRecipient(storedMessageArray, recipientArray, new String[]{searchRecipient}));
                } else if (storedChoice == 5) {
                    System.out.println("Enter the message hash to delete the message");
                    String deleteHash = scanner.nextLine();
                    System.out.println(messageObject.deleteMessageUsingHash(storedMessageArray, messageHashArray, deleteHash));
                } else if (storedChoice == 6) {
                    System.out.println(messageObject.displayFullReport(storedMessageArray, messageIDArray, messageHashArray, senderArray, recipientArray));
                } else {
                    System.out.println("Invalid option");
                }
            }
        }while (selectOption != 3) ;

            scanner.close();
        }
    }







