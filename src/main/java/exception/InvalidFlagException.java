package exception;

/**
 * Custom exception for flag(starts with -/--) validation
 */
public class InvalidFlagException extends Exception {

    public InvalidFlagException (String message) {

        super (message);
    }
}
