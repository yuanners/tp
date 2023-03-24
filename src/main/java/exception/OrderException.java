package exception;

/**
 * Custom exception to handle order validation
 */
public class OrderException extends Exception {

    public OrderException(String message) {
        super(message);
    }
}
