package exception;

public class UnknownCommandException extends Exception{
    public UnknownCommandException(){
        super("Command unknown!");
    }
}
