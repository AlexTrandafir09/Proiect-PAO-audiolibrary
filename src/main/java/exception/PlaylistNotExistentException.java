package exception;

public class PlaylistNotExistentException extends Exception{
    public PlaylistNotExistentException() {
        super("The desired playlist does not exist.");
    }
}
