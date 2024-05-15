package exception;

public class InvalidCommandUsageException extends Exception{
    public InvalidCommandUsageException(String command){
        super("Invalid usage of command "+command+".");
    }
}
