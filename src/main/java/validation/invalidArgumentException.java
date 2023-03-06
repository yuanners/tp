package validation;

public class invalidArgumentException extends Exception{
    public invalidArgumentException (String message, Throwable cause) {
        super (message, cause);
    }
}
