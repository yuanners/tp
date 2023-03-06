package utility;

/**
 * All print command will be done through here.
 */
public class Ui {
    public Ui() {
    }

    public void printUserInput() {
        System.out.println("Please enter something: ");
    }

    public void printInvalidIndex() {System.out.println("Please enter a valid index!");}

    public void printRequiresInteger() {System.out.println("This input requires a whole number!");}
}
