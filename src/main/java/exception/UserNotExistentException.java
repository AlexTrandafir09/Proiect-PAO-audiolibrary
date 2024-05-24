package exception;

public class UserNotExistentException extends Exception {
    public UserNotExistentException() {
        super("Specified user does not exist!");
    }
}
