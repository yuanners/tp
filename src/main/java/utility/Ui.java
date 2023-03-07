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

    public void printUserInput () {
        System.out.println ("Please enter something: ");
    }

    public void printInvalidIndex() {System.out.println("Please enter a valid index!");}

    public void printRequiresInteger() {System.out.println("This input requires a whole number!");}
    
    /**
     * Prints string to user and moves the cursor to a new line.
     */
    public void println(Object string) {
        System.out.println(string);
    }

    /**
     * Prints string to user without moving the cursor to a new line.
     */
    public void print(Object string) {
        System.out.print(string);
    }
}
