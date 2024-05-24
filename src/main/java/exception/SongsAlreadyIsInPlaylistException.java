package exception;

public class SongsAlreadyIsInPlaylistException extends Exception{
    public SongsAlreadyIsInPlaylistException(String name,String author,String playlist_name){
        super("song "+name +" by "+ author+ " is already part of "+playlist_name);
    }
}
