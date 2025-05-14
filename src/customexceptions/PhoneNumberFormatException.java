package customexceptions;

public class PhoneNumberFormatException extends InvalidInputException {
    public PhoneNumberFormatException(String message) {
        super(message);
    }
}
