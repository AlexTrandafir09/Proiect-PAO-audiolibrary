package exception;

public class UserNotExistentException extends Exception{
    public UserNotExistentException() {
        super("User not existent");
    }
}
