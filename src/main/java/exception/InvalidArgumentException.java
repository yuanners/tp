package exception;

/**
 * Custom exception for argument validation
 */
public class InvalidArgumentException extends Exception {

    public InvalidArgumentException(String message) {

        super(message);
    }
}
