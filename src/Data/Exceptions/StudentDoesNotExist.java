package Data.Exceptions;

public class StudentDoesNotExist extends Exception {
    public StudentDoesNotExist(String message) {
        super(message);
    }
}
