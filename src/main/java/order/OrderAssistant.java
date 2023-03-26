package order;

import app.Command;
import item.Menu;
import utility.Ui;
import validation.order.AddOrderValidation;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderAssistant {

    Ui ui;
    Scanner sc;
    AddOrderValidation addOrderValidation = new AddOrderValidation();

    private final ArrayList<String> CANCELS = new ArrayList<>() {
        {
            add("/cancel");
            add("cancel");
        }
    };
    private final ArrayList<String> YESES = new ArrayList<>() {
        {
            add("yes");
            add("y");
            add("/yes");
            add("/y");
        }
    };
    private final ArrayList<String> NOS = new ArrayList<>() {
        {
            add("no");
            add("n");
            add("/no");
            add("/n");
        }
    };

    public OrderAssistant() {
        ui = new Ui();
        sc = new Scanner(System.in);
    }

    public boolean assistedAddOrder(Menu menu, Transaction transaction) {

        Order order;
        Command command;
        String commandString = "";
        String itemName = "";
        String quantity = "";

        boolean hasMoreOrderEntry = true;

        while (hasMoreOrderEntry) {

            do {
                itemName = getItem();
                if (CANCELS.contains(itemName)) {
                    return true;
                }
            } while (!addOrderValidation.checkValidItemName(itemName));

            do {
                quantity = getQuantity();
                if (CANCELS.contains(quantity)) {
                    return true;
                }
            } while (!addOrderValidation.checkValidQuantity(quantity));

            String hasMoreOrderEntryString = askIfGotMoreOrderEntries();
            if (YESES.contains(hasMoreOrderEntryString)) {
                hasMoreOrderEntry = true;
            } else if (NOS.contains(hasMoreOrderEntryString)) {
                hasMoreOrderEntry = false;
            } else if (CANCELS.contains(hasMoreOrderEntryString)) {
                return true;
            }

            // Append to final command string
            commandString += "\"" + itemName + "\":" + quantity + ",";
        }

        commandString = formatCommandStringForOrders(commandString);
        command = new Command(commandString);
        order = new Order(command, menu, transaction);

        // Returns false when there are no more orderEntries to add
        return false;
    }

    private String getItem() {

        String item = "";

        ui.promptItemName();
        item = sc.nextLine();
        item = item.toLowerCase();

        return item;

    }

    private String getQuantity() {

        String quantity = "";

        ui.promptItemQuantity();
        quantity = sc.nextLine();
        quantity = quantity.toLowerCase();

        return quantity;
    }

    private String askIfGotMoreOrderEntries() {

        String response = "";

        ui.promptMoreOrderEntries();
        response = sc.nextLine();
        response = response.toLowerCase();

        return response;
    }

    private String formatCommandStringForOrders(String ordersString) {

        // Remove trailing ,
        ordersString = ordersString.substring(0, ordersString.length() - 1);

        ordersString = "/addorder -I [" + ordersString + "]";
        return ordersString;

    }

}
