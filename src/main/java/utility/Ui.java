package utility;

import item.Item;
import order.Order;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * All print command will be done through here.
 */
public class Ui {

    public final String NULL_MESSAGE = "Input is empty. Please enter something.";
    /**
     * General print statements
     * Prompts user for input
     */
    public void promptUserInput() {
        System.out.println("Please enter a command: ");
    }

    public void promptUserInputError() {
        System.out.println("Please enter again:");
    }

    /**
     * Prints an error message with the wrong command.
     *
     * @param command The extracted command word
     */
    public void printInvalidCommand(String command) {
        System.out.println("The command: " + command + " is not a valid command.");
    }

    /**
     * Prints invalid index
     */
    public void printInvalidIndex() {
        System.out.println("Please enter a valid index!");
    }

    public void printIntegerOverflowError() {
        System.out.println("Integer overflow! Please enter an integer within the valid range.");
    }

    /**
     * Prints error message informing user to input a whole number
     */
    public void printRequiresInteger() {
        System.out.println("This input requires a whole number!");
    }

    public void printNegativeError() {
        System.out.print("Value cannot be negative.");
    }

    public void printMinLengthError(String option) {
        System.out.println(option + " cannot be empty.");
    }

    public void printCommandSuccess(String command) {
        System.out.println("The command: " + command + " was successfully executed!");
    }

    public void printExitMessage() {
        System.out.println("Thank you for using MoneyGoWhere. Goodbye!");
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


    /*** ITEM AND MENU PRINT STATEMENTS ***/
    public void printInvalidDouble() {
        System.out.println("Price must have at most 2 decimal points.");
    }

    public void printInvalidFormat(String command) {
        System.out.println("The usage of " + command + " is invalid.");
    }

    public void printMaxLengthError() {
        System.out.println("Name exceeds the 25 character limit.");
    }

    /**
     * Prints all items in a table format
     *
     * @param menu List of items
     */
    public void printMenu(ArrayList<Item> menu) {
        System.out.printf("| %-5s | %-25s | %-5s |\n", "Index", "Name", "Price");
        System.out.println("| " + "-".repeat(5) + " | " + "-".repeat(25) + " | " + "-".repeat(5) + " |");

        for(int i = 0; i < menu.size(); ++i) {
            System.out.printf("| %-5d | %-25s | %-5.2f |\n", i, menu.get(i).getName(), menu.get(i).getPrice());
        }
    }


    /**
     * ORDER AND TRANSACTION PRINT STATEMENTS
     * Prints the list of orders.
     * This includes the subtotal cost of each order.
     *
     * @param orders Arraylist of orders stored
     */
    public void invalidOrderCommand(){
        System.out.println("Please use -i or -I flags for addorder command.");
    }
    public void invalidOrderSyntax(){
        System.out.println("Please enter the item's index number after the flag.");
    }
    public void invalidIndex(){
        System.out.println("Please enter an integer for item number or quantity.");
    }
    public void printOrderList(ArrayList<Order> orders) {

        DecimalFormat df = new DecimalFormat("#.00");

        System.out.println("================================================");

        for(int i = 0; i < orders.size(); i++) {

            System.out.println("Order " + (i + 1));
            System.out.println("Order ID: " + orders.get(i).getOrderId());
            System.out.println("Order time: " + orders.get(i).getDateTime());

            for(int j = 0; j < orders.get(i).getOrderEntries().size(); j++) {
                System.out.println((j + 1) + ". "
                        + orders.get(i).getOrderEntries().get(j).getItem().getName()
                        + " x" + orders.get(i).getOrderEntries().get(j).getQuantity());
            }

            String subtotal = df.format(orders.get(i).getSubTotal());
            System.out.println("\nSubtotal: $" + subtotal);
            System.out.println("================================================");

        }
    }
}
