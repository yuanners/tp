package order;

import app.Command;
import item.ItemList;
import utility.Parser;
import validation.OrderValidation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class Order implements OrderInterface {

    private String orderId;
    private LocalDateTime dateTime;
    private ArrayList<OrderEntry> orderEntries;


    public Order() {
        this.orderId = UUID.randomUUID().toString();
        this.dateTime = LocalDateTime.now();
        this.orderEntries = new ArrayList<>();
    }

    public Order(ArrayList<OrderEntry> orderEntries) {
        this.orderId = UUID.randomUUID().toString();
        this.dateTime = LocalDateTime.now();
        this.orderEntries = orderEntries;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ArrayList<OrderEntry> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(ArrayList<OrderEntry> orderEntries) {
        this.orderEntries = orderEntries;
    }

    @Override
    public double getSubTotal() {

        double subtotal = 0;

        for (OrderEntry i : this.orderEntries) {
            subtotal += (i.getQuantity() * i.getItem().getPrice());
        }

        return subtotal;

    }

    public boolean addOrder(Command command, Parser parser, ItemList items) {

        OrderValidation orderValidation = new OrderValidation();

        if (!orderValidation.isValidFormat(command)) return false;

        // If command contains multiple OrderEntries, do this
        if (command.getUserInput().contains("{") && command.getUserInput().contains("}")) {
            String allOrderEntriesAsString;
            allOrderEntriesAsString = parser.extractStringWithinBraces(command.getUserInput());

            ArrayList<String> arrayListOfOrderEntries = new ArrayList<String>(Arrays
                    .asList(allOrderEntriesAsString.split(",")));

            for (String orderEntryCommand : arrayListOfOrderEntries) {
                handleMultipleAddOrder(orderEntryCommand.trim(), orderValidation, items);
            }
        }

        // If command only contains 1 OrderEntry, do this
        else {
            handleAddOrder(command, orderValidation, items);
        }


        return true;
    }

    public boolean handleMultipleAddOrder(String orderEntryCommand, OrderValidation orderValidation, ItemList items) {

        Command command = new Command("addOrder " + orderEntryCommand);

        command.mapArgumentAlias("item", "i");
        command.mapArgumentAlias("quantity", "q");

        if (!orderValidation.isValid(command)) {
            return false;
        }

        int itemIndex = Integer.parseInt(command.getArgumentMap().get("item").trim());
        int quantity;

        // Checks if quantity of orderEntry is specified
        // If not specified, it defaults to 1
        if (command.getArgumentMap().get("quantity") != null) {
            quantity = Integer.parseInt(command.getArgumentMap().get("quantity").trim());
        } else {
            quantity = 1;
        }

        OrderEntry orderEntry = new OrderEntry(items.getItems().get(itemIndex), quantity);
        this.orderEntries.add(orderEntry);

        return true;
    }

    public boolean handleAddOrder(Command command, OrderValidation orderValidation, ItemList items) {

        command.mapArgumentAlias("item", "i");
        command.mapArgumentAlias("quantity", "q");

        if (!orderValidation.isValid(command)) {
            return false;
        }

        int itemIndex = Integer.parseInt(command.getArgumentMap().get("item").trim());
        int quantity;

        // Checks if quantity of orderEntry is specified
        // If not specified, it defaults to 1
        if (command.getArgumentMap().get("quantity") != null) {
            quantity = Integer.parseInt(command.getArgumentMap().get("quantity").trim());
        } else {
            quantity = 1;
        }

        OrderEntry orderEntry = new OrderEntry(items.getItems().get(itemIndex), quantity);
        this.orderEntries.add(orderEntry);

        return true;
    }
}
