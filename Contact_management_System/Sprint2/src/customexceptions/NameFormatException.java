package customexceptions;

public class NameFormatException extends InvalidInputException {
    public NameFormatException(String message) {
        super(message);
    }
}