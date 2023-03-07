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

    public void printUserInput () {
        System.out.println ("Please enter something: ");
    }
}
