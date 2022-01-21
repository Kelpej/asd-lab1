package Data.Exceptions;

public class InvalidPersonNameException extends Exception{
    public InvalidPersonNameException(String message) {
        super("Name of a person must consist of three parts.\n" +
                message + " is not a valid name.");
    }
}
