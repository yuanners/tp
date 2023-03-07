package utility;

/**
 * All print command will be done through here.
 */
public class Ui {
    public Ui () {
    }

    public final static String NULL_MESSAGE = "Input is empty. Please enter something.";
    public final static String ERROR_MESSAGE = "Please do not use special characters such as ';' and ':'.";
    public final static String INTEGER_ERROR = "Argument needs to be an integer";
    public final static String MISSING_ARGUMENT = "The argument cannot be empty.";
    public final static String MISSING_ORDER_ARGUMENT = "Please use -i or --item and -d or --done to add order.";
    public final static String ITEM_NAME_MIN_LENGTH_ERROR = "Name cannot be empty.";
    public final static String ITEM_NAME_MAX_LENGTH_ERROR = "Name exceeds the 25 character limit.";
    public final static String ITEM_PRICE_MIN_LENGTH_ERROR = "Price cannot be empty.";
    public final static String ITEM_PRICE_NEGATIVE_ERROR = "Price cannot be negative.";
    public final static String INVALID_ADDITEM_FORMAT = "additem command format is invalid.";
    public final static String INVALID_DELETEITEM_FORMAT = "deleteitem command format is invalid.";
    public final static String PRICE_DECIMAL_ERROR = "Price must be in 2 decimal points.";
    public final static String INVALID_PRICE_ERROR = "Price must be a number.";

    public final static String SUCCESSFUL_COMMAND = "Successfully executed your command!";

    public void printUserInput () {
        System.out.println ("Please enter something: ");
    }

    public void printInvalidIndex () {
        System.out.println ("Please enter a valid index!");
    }

    public void printRequiresInteger () { System.out.println ("This input requires a whole number!"); }

    public void printTableHeader (String col1, String col2, String col3) {
        System.out.printf("| %-5s | %-25s | %-5s |\n", col1, col2, col3);
        System.out.println("| " + "-".repeat(5) + " | " + "-".repeat(25) + " | " + "-".repeat(5) + " |");
    }

    public void printItemMenu (int index, String name, double price) {
        System.out.printf("| %-5d | %-25s | %-5.2f |\n", index, name, price);
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
