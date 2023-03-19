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
    private String MISSING_ORDER_FLAG = "Please use correctly formatted flags to add order.";
    private String MISSING_ORDER_ARGUMENT = "Please enter item index or quantity after flags.";
    private String INVALID_ORDER_INTEGER = "Quantity must be more than 0.";
    private String INVALID_MULTIPLE_ORDER_FORMAT = "Wrong format to add multiple orders.";
    private String INVALID_MULTIPLE_ORDER_INTEGER = "Please enter positive numbers only.";
    private String MISSING_ORDER_ID = "Please enter the order ID to refund.";
    private String INVALID_ORDER_ID = "Please enter a valid order ID to refund.";
    private String INVALID_REFUND_STATUS = "Order is already refunded.";
    private String INVALID_PAYMENT = "Invalid payment command.";
    private String MISSING_PAYMENT_FLAG = "Please use correctly formatted flags to add payment.";
    private String MISSING_PAYMENT_ARGUMENT = "Please enter both the payment type and amount received.";
    private String INVALID_PAYMENT_AMOUNT = "Please enter the correct amount up to 2 decimal points only.";
    private String INSUFFICIENT_AMOUNT = "Insufficient amount. Payment amount must be more than or equals to subtotal.";
    private String INVALID_PAYMENT_TYPE = "Please enter a valid payment type (Card/Cash/Others).";
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
    private String REQUIRE_INTEGER = "This input requires a whole number!";
    private String REQUIRE_VALID_ITEM_INDEX = "This input requires a valid item index!";
    private String EXIT_MESSAGE = "Thank you for using MoneyGoWhere. Goodbye!";
    private String NO_SUCH_ITEM = "No such item exists.";
    private String MULTIPLE_SIMILAR_ITEMS = "Your input referenced multiple similar items. " +
            "Please try again with a more specific item name.";
    private String EMPTY_MENU = "There are no items on the menu.";

    /**
     * General print statements
     * Prompts user for input
     */

    public void promptUserInput() {
        System.out.println("Please enter a command: ");
    }

    /**
     * Prompts user for item name when in assistance mode
     */
    public void promptItemName() {
        System.out.println("Please enter the item's name: ");
    }

    /**
     * Prompts user if they would like to update item name
     */
    public void promptItemNameChange() {
        System.out.println("Would you like to update item name? (yes/no)");
    }

    /**
     * Prompts user if they answer is not recognised
     */
    public void promptUpdateItemUnrecognisedAnswer() {
        System.out.println("Sorry your input was not recognised."
                + " Please answer with \"yes\" or \"no\".");
    }


    /**
     * Prompts user for the new item name when in assistance mode
     */
    public void promptNewItemName() {
        System.out.println("Please enter the new name: ");
    }

    /**
     * Prompts user for item price when in assistance mode
     */
    public void promptItemPrice() {
        System.out.println("Please enter the item's price: ");
    }

    /**
     * Prompts user if they would like to update item price
     */
    public void promptItemPriceChange() {
        System.out.println("Would you like to update item price? (yes/no)");
    }

    /**
     * Prompts user for the new item price when in assistance mode
     */
    public void promptNewItemPrice() {
        System.out.println("Please enter the new price: ");
    }

    /**
     * Prompts user for item index when in assistance mode
     */
    public void promptItemIndex() {
        System.out.println("Please enter the item's index: ");
    }

    /**
     * Prompts user for item keyword when in assistance mode
     */
    public void promptItemKeyword() {
        System.out.println("Please enter the keyword to search for: ");
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
     * Prints error message informing user that command has executed successfully
     */
    public void printCommandSuccess(String command) {
        System.out.println("The command: " + command + " was successfully executed!");
    }

    public void printOrderAdded(Double total) {
        System.out.println("Order has been added successfully. Total amount: $" + total + ".");
    }

    /**
     * Prints error message informing user to input a whole number
     */
    public void printCommandCancelled(String command) {
        System.out.println("The command: " + command + " has been cancelled.");
    }

    /**
     * Prompt user to use /pay to add payment
     */
    public void promptPayment() {
        System.out.println("Please use /pay command to add payment for the order.");
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
    public String getEmptyMenu() {
        return EMPTY_MENU;
    }

    public String getNullMessage() {
        return NULL_MESSAGE;
    }

    public String getDoubleOverflow() {
        return DOUBLE_OVERFLOW;
    }

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }

    public String getMissingOrderArgument() {
        return MISSING_ORDER_ARGUMENT;
    }

    public String getMissingOrderFlag() {
        return MISSING_ORDER_FLAG;
    }

    public String getInvalidOrderInteger() {
        return INVALID_ORDER_INTEGER;
    }

    public String getInvalidMultipleOrderFormat() {
        return INVALID_MULTIPLE_ORDER_FORMAT;
    }

    public String getInvalidMultipleOrderInteger() {
        return INVALID_MULTIPLE_ORDER_INTEGER;
    }

    public String printMissingOrderID() {
        return MISSING_ORDER_ID;
    }

    public String printInvalidRefundStatus() {
        return INVALID_REFUND_STATUS;
    }

    public String printInvalidOrderID() {
        return INVALID_ORDER_ID;
    }

    public String printInvalidPayment() {
        return INVALID_PAYMENT;
    }

    public String printMissingPaymentFlag() {
        return MISSING_PAYMENT_FLAG;
    }

    public String printMissingPaymentArgument() {
        return MISSING_PAYMENT_ARGUMENT;
    }

    public String printInvalidPaymentAmount() {
        return INVALID_PAYMENT_AMOUNT;
    }
    public String printInsufficientAmount(){return INSUFFICIENT_AMOUNT;}

    public String printInvalidPaymentType() {
        return INVALID_PAYMENT_TYPE;
    }

    public String getItemDuplicateNameError() {
        return ITEM_DUPLICATE_NAME_ERROR;
    }

    public String getItemNameMinLengthError() {
        return ITEM_NAME_MIN_LENGTH_ERROR;
    }

    public String getItemNameMaxLengthError() {
        return ITEM_NAME_MAX_LENGTH_ERROR;
    }

    public String getItemPriceMinLengthError() {
        return ITEM_PRICE_MIN_LENGTH_ERROR;
    }

    public String getItemPriceNegativeError() {
        return ITEM_PRICE_NEGATIVE_ERROR;
    }

    public String getIntegerOverflow() {
        return INTEGER_OVERFLOW;
    }

    public String getInvalidAddItemFormat() {
        return INVALID_ADDITEM_FORMAT;
    }

    public String getInvalidDeleteitemFormat() {
        return INVALID_DELETEITEM_FORMAT;
    }

    public String getInvalidIndex() {
        return INVALID_INDEX;
    }

    public String getPriceDecimalError() {
        return PRICE_DECIMAL_ERROR;
    }

    public String getInvalidPriceError() {
        return INVALID_PRICE_ERROR;
    }

    public String getRequireInteger() {
        return REQUIRE_INTEGER;
    }

    public String getRequireValidItemIndex() {
        return REQUIRE_VALID_ITEM_INDEX;
    }

    public String getExitMessage() {
        return EXIT_MESSAGE;
    }

    public String getNoSuchItem() {
        return NO_SUCH_ITEM;
    }

    public String getMultipleSimilarItemsFound() {
        return MULTIPLE_SIMILAR_ITEMS;
    }

    /**
     * Prints table header for menu
     */
    public void printMenuHeader() {
        System.out.printf("| %-5s | %-25s | %-5s |\n", "Index", "Name", "Price");
        System.out.println("| " + "-".repeat(5) + " | " + "-".repeat(25) + " | " + "-".repeat(5) + " |");
    }

    /**
     * Prints all items in a table format
     *
     * @param menu List of items
     */
    public void printMenu(ArrayList<Item> menu) {
        printMenuHeader();
        for (int i = 0; i < menu.size(); ++i) {
            System.out.printf("| %-5d | %-25s | %-5.2f |\n", i, menu.get(i).getName(), menu.get(i).getPrice());
        }
    }

    /**
     * Prints specific index and name of item
     *
     * @param index Given index
     * @param menu  List of items
     */

    public void printFindItem(int index, ArrayList<Item> menu) {
        System.out.printf("| %-5d | %-25s | %-5.2f |\n", index, menu.get(index).getName(), menu.get(index).getPrice());
    }


    /**
     * Prints the list of orders.
     * This includes the subtotal cost of each order.
     *
     * @param orders list of orders
     */
    public void printOrderList(ArrayList<Order> orders) {

        DecimalFormat df = new DecimalFormat("#.00");

        System.out.println("================================================");

        for (int i = 0; i < orders.size(); i++) {

            System.out.println("Order " + (i + 1));
            System.out.println("Order ID: " + orders.get(i).getOrderId());
            System.out.println("Order time: " + orders.get(i).getDateTime());
            System.out.println("Order status: " + orders.get(i).getStatus());
            System.out.println("Payment type: " + orders.get(i).getPaymentType());

            for (int j = 0; j < orders.get(i).getOrderEntries().size(); j++) {
                System.out.println((j + 1) + ". "
                        + orders.get(i).getOrderEntries().get(j).getItem().getName()
                        + " x" + orders.get(i).getOrderEntries().get(j).getQuantity());
            }

            String subtotal = df.format(orders.get(i).getSubTotal());
            System.out.println("\nSubtotal: $" + subtotal);
            System.out.println("================================================");

        }
    }

    public void printChangeGiven(Double change) {
        System.out.println("The calculated change is $" + change + ".");
    }

    public void printItemNotFound() {
        System.out.println("The entered item cannot be found.");
    }
}
