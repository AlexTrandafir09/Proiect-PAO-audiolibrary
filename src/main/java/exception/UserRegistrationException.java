package exception;

public class UserRegistrationException extends Exception {
    public UserRegistrationException() {
        super("User with given username already exists! Please try again!");
    }
}
