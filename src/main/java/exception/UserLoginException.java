package exception;

public class UserLoginException extends Exception {
    public UserLoginException() {
        super("Username or password is invalid. Please try again!");
    }
}
