package order;

import app.Command;
import exception.DuplicateArgumentFoundException;
import item.Menu;
import payment.Payment;
import ui.TransactionUi;
import validation.order.AddOrderValidation;

import java.util.Arrays;

public class OrderAssistant {

    private TransactionUi transactionUi;

    private final String[] CANCELS = {"/cancel", "cancel"};
    private final String[] YESES = {"/yes", "yes", "/y", "y"};
    private final String[] NOS = {"/no", "no", "/n", "n"};

    public OrderAssistant() {
        transactionUi = new TransactionUi();
    }

    public boolean assistedAddOrder(Menu menu, Transaction transaction) throws DuplicateArgumentFoundException {

        Command command;
        String commandString = "";
        String itemName = "";
        String quantity = "";

        AddOrderValidation addOrderValidation = new AddOrderValidation(menu);

        boolean hasMoreOrderEntry = true;

        while (hasMoreOrderEntry) {

            do {
                itemName = getItem();
                if (Arrays.asList(CANCELS).contains(itemName)) {
                    return true;
                }

            } while (!addOrderValidation.checkValidItemName(itemName));

            do {
                quantity = getQuantity();
                if (Arrays.asList(CANCELS).contains(quantity)) {
                    return true;
                }
            } while (!addOrderValidation.checkValidQuantity(quantity));

            String hasMoreOrderEntryString = askIfGotMoreOrderEntries();
            if (Arrays.asList(YESES).contains(hasMoreOrderEntryString)) {
                hasMoreOrderEntry = true;
            } else if (Arrays.asList(NOS).contains(hasMoreOrderEntryString)) {
                hasMoreOrderEntry = false;
            } else if (Arrays.asList(CANCELS).contains(hasMoreOrderEntryString)) {
                return true;
            } else {
                transactionUi.printInvalidInputEntered();
                return true;
            }

            // Append to final command string
            commandString +=  itemName + ":" + quantity + ",";
        }

        commandString = formatCommandStringForOrders(commandString);
        command = new Command(commandString);

        Payment payment = new Payment();
        new Order(command, menu, transaction, transactionUi, payment);

        // Returns false when there are no more orderEntries to add
        return false;
    }

    private String getItem() {

        String item = "";

        do {
            transactionUi.promptItemName();
            item = transactionUi.inputHandler();
            item = item.toLowerCase();

            if (item.strip().equals("")) {
                transactionUi.printInvalidInputEntered();
            }
        } while (item.strip().equals(""));

        return item;

    }

    private String getQuantity() {

        String quantity = "";

        do {
            transactionUi.promptItemQuantity();
            quantity = transactionUi.inputHandler();
            quantity = quantity.toLowerCase();

            if (quantity.strip().equals("")) {
                transactionUi.printInvalidInputEntered();
            }
        } while (quantity.strip().equals(""));

        return quantity;
    }

    private String askIfGotMoreOrderEntries() {

        String response = "";

        transactionUi.promptMoreOrderEntries();
        response = transactionUi.inputHandler();
        response = response.toLowerCase();

        return response;
    }

    private String formatCommandStringForOrders(String ordersString) {

        // Remove trailing ,
        ordersString = ordersString.substring(0, ordersString.length() - 1);

        ordersString = "/addorder -I [" + ordersString + "]";
        return ordersString;

    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException n) {
            return false;
        }

        return true;
    }

}
