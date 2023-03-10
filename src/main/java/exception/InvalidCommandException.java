package exception;

/**
 * Custom exception for command validation
 */
public class InvalidCommandException extends Exception {

    public InvalidCommandException(String message) {

        super(message);
    }
}
