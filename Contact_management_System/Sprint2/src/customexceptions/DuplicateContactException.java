package customexceptions;

public class DuplicateContactException extends InvalidInputException {
    public DuplicateContactException(String message) {
        super(message);
    }
}
