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
