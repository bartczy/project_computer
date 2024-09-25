package computer.software.file.shared;

public class NotEnoughSpaceException extends Exception {
    public NotEnoughSpaceException(String message) {
        super(message);
    }
}
