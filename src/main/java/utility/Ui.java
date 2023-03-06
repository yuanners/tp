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
