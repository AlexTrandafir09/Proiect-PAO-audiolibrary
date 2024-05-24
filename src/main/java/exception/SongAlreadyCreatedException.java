package exception;

public class SongAlreadyCreatedException extends Exception {
    public SongAlreadyCreatedException() {
        super("This song is already part of the library!");
    }
}
