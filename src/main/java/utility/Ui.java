package utility;

/**
 * All print command will be done through here.
 */
public class Ui {

    public static final String NULL_MESSAGE = "Input is empty. Please enter something.";
    public static final String ERROR_MESSAGE = "Please do not use special characters such as ';' and ':'.";
    public static final String INTEGER_ERROR = "Argument needs to be an integer";
    public static final String MISSING_ARGUMENT = "The argument cannot be empty.";
    public static final String MISSING_ORDER_ARGUMENT = "Please use -i or --item and -d or --done to add order.";
    public static final String ITEM_NAME_MIN_LENGTH_ERROR = "Name cannot be empty.";
    public static final String ITEM_NAME_MAX_LENGTH_ERROR = "Name exceeds the 25 character limit.";
    public static final String ITEM_PRICE_MIN_LENGTH_ERROR = "Price cannot be empty.";
    public static final String ITEM_PRICE_NEGATIVE_ERROR = "Price cannot be negative.";
    public static final String INVALID_ADDITEM_FORMAT = "additem command format is invalid.";
    public static final String PRICE_DECIMAL_ERROR = "Price must be in 2 decimal points.";
    public static final String INVALID_PRICE_ERROR = "Price must be a number.";

    public Ui () {
    }

    public void printUserInput () {
        System.out.println ("Please enter something: ");
    }

    public void printInvalidIndex () {
        System.out.println ("Please enter a valid index!");
    }

    public void printRequiresInteger () {
        System.out.println ("This input requires a whole number!");
    }

    /**
     * Prints string to user and moves the cursor to a new line.
     */
    public void println (Object string) {
        System.out.println (string);
    }

    /**
     * Prints string to user without moving the cursor to a new line.
     */
    public void print (Object string) {
        System.out.print (string);
    }
}
