public class Login {
    private String username;
    private String password;
    private String cellPhoneNumber;


    public Login(String username, String password, String cellPhoneNumber) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }
//password requirement met using boolean variable hasCapital hasNumber and it hasCharacter
    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        if (password == null || password.length() < 8) {
            return false;
        }

            boolean hasCapital = false;
            boolean hasNumber = false;
            boolean hasCharacter = false;

            for (int i = 0; i < password.length(); i++) {
                char character = password.charAt(i);
                if (Character.isUpperCase(character)) {
                    hasCapital = true;
                } else if (Character.isDigit(character)) {
                    hasNumber = true;
                } else if (Character.isUpperCase(character)) {
                    hasCapital = true;
                } else if (!Character.isLetterOrDigit(character)) {
                    hasCharacter = true;
                }
            }
            return hasCapital && hasNumber && hasCharacter;
        }

        public boolean checkCellPhoneNumber () {
            if (cellPhoneNumber == null || cellPhoneNumber.length() != 12
                    || !cellPhoneNumber.startsWith("+27")) {
                return false;
            }
            for (int i = 3; i < cellPhoneNumber.length(); i++) {
                if (!Character.isDigit(cellPhoneNumber.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        public String registerUser () {
            if (!checkUserName()) {
                return "Username is not formatted correctly";
            }
            if (!checkPasswordComplexity()) {
                return "Password is not formatted correctly";
            }
            if (!checkCellPhoneNumber()) {
                return "Invalid Cell Phone Number";
            }
            return "User successfully registered";
        }

        public boolean loginUser (String enteredUsername, String enteredPassword){
            return enteredUsername.equals(username)
                    && enteredPassword.equals(password);
        }
// change
        public String returnLoginStatus ( boolean loginSuccessful){
            if (loginSuccessful) {
                return "Welcome " + username + " it is great to see you again.";
            }
            return "Username or password incorrect, please try again";
        }
    }