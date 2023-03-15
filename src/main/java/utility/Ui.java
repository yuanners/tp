package utility;

import item.Item;
import order.Order;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * All print command will be done through here.
 */
public class Ui {

    private String NULL_MESSAGE = "Input is empty. Please enter something.";
    private String DOUBLE_OVERFLOW = "Double overflow! Please enter a double within the valid range.";
    private String ERROR_MESSAGE = "Please do not use special characters such as ';' and ':'.";
    private String INTEGER_ERROR = "Argument needs to be an integer";
    private String MISSING_ORDER_ARGUMENT = "Please use -i or --item and -d or --done to add order.";
    private String ITEM_DUPLICATE_NAME_ERROR = "Item name already exists.";
    private String ITEM_NAME_MIN_LENGTH_ERROR = "Name cannot be empty.";
    private String ITEM_NAME_MAX_LENGTH_ERROR = "Name exceeds the 25 character limit.";
    private String ITEM_PRICE_MIN_LENGTH_ERROR = "Price cannot be empty.";
    private String ITEM_PRICE_NEGATIVE_ERROR = "Price cannot be negative.";
    private String INTEGER_OVERFLOW = "Integer overflow! Please enter an integer within the valid range.";
    private String INVALID_ADDITEM_FORMAT = "additem command format is invalid.";
    private String INVALID_DELETEITEM_FORMAT = "deleteitem command format is invalid.";
    private String INVALID_INDEX = "Please enter a valid index!";
    private String PRICE_DECIMAL_ERROR = "Price must have at most 2 decimal points.";
    private String INVALID_PRICE_ERROR = "Price must be a number.";
    private String SUCCESSFUL_COMMAND = "Successfully executed your command!";
    private String PROMPT_MESSAGE = "Please enter again:";
    private String REQUIRE_INTEGER = "This input requires a whole number!";
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
     * Prints error message informing user to input a whole number
     */
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
    public String printInvalidFlags(String command) {
        return "The usage of " + command + " is invalid.";
    }

    /*** Getter Methods to retrieve error messages ***/
    public String getNULL_MESSAGE() {
        return NULL_MESSAGE;
    }

    public String getDOUBLE_OVERFLOW() {
        return DOUBLE_OVERFLOW;
    }

    public String getERROR_MESSAGE() {
        return ERROR_MESSAGE;
    }

    public String getINTEGER_ERROR() {
        return INTEGER_ERROR;
    }

    public String getMISSING_ORDER_ARGUMENT() {
        return MISSING_ORDER_ARGUMENT;
    }

    public String getITEM_DUPLICATE_NAME_ERROR() {
        return ITEM_DUPLICATE_NAME_ERROR;
    }

    public String getITEM_NAME_MIN_LENGTH_ERROR() {
        return ITEM_NAME_MIN_LENGTH_ERROR;
    }

    public String getITEM_NAME_MAX_LENGTH_ERROR() {
        return ITEM_NAME_MAX_LENGTH_ERROR;
    }

    public String getITEM_PRICE_MIN_LENGTH_ERROR() {
        return ITEM_PRICE_MIN_LENGTH_ERROR;
    }

    public String getITEM_PRICE_NEGATIVE_ERROR() {
        return ITEM_PRICE_NEGATIVE_ERROR;
    }

    public String getINTEGER_OVERFLOW() {
        return INTEGER_OVERFLOW;
    }

    public String getINVALID_ADDITEM_FORMAT() {
        return INVALID_ADDITEM_FORMAT;
    }

    public String getINVALID_DELETEITEM_FORMAT() {
        return INVALID_DELETEITEM_FORMAT;
    }

    public String getINVALID_INDEX() {
        return INVALID_INDEX;
    }

    public String getPRICE_DECIMAL_ERROR() {
        return PRICE_DECIMAL_ERROR;
    }

    public String getINVALID_PRICE_ERROR() {
        return INVALID_PRICE_ERROR;
    }

    public String getSUCCESSFUL_COMMAND() {
        return SUCCESSFUL_COMMAND;
    }

    public String getPROMPT_MESSAGE() {
        return PROMPT_MESSAGE;
    }

    public String getREQUIRE_INTEGER() {
        return REQUIRE_INTEGER;
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

    /**
     * Prints the list of orders.
     * This includes the subtotal cost of each order.
     * @param orders
     */
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
