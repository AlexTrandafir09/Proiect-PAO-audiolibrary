package exception;

public class AlreadyExistingPlaylistException extends Exception {
    public AlreadyExistingPlaylistException(String name) {
        super("You already have a playlist named " + name);
    }
}
