package exception;

public class SongAlreadyCreatedException extends Exception {
    public SongAlreadyCreatedException() {
        super("Song already exists");
    }

}
