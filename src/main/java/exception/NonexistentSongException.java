package exception;

public class NonexistentSongException extends Exception{
    public NonexistentSongException(int id){
        super("Song with identifier "+ id  +" does not exist.");
    }
}
