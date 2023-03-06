package validation;

public class invalidCommandException extends Exception {
    public invalidCommandException (String message, Throwable cause) {

        super (message, cause);
    }
}
