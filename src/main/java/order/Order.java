package order;

import app.Command;
import exception.OrderException;
import item.Menu;
import utility.Ui;
import validation.order.AddMultipleAddOrderValidation;
import validation.order.AddOrderValidation;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Order implements OrderInterface {

    private String orderId;
    private LocalDateTime dateTime;
    private ArrayList<OrderEntry> orderEntries;
    private String status;


    /**
     * Constructs an Order object with a unique ID, default transaction status as COMPLETED
     * the current date and time and an empty ArrayList of
     * OrderEntry objects.
     */
    public Order() {
        this.orderId = UUID.randomUUID().toString();
        this.status = "COMPLETED";
        this.dateTime = LocalDateTime.now();
        this.orderEntries = new ArrayList<>();
    }

    /**
     * Constructs an Order object with a unique ID,
     * the current date and time and an ArrayList of
     * OrderEntry objects.
     *
     * @param orderEntries ArrayList of OrderEntry objects
     */
    public Order(ArrayList<OrderEntry> orderEntries) {
        this.orderId = UUID.randomUUID().toString();
        this.dateTime = LocalDateTime.now();
        this.orderEntries = orderEntries;
    }

    /**
     * Gets the unique ID of the Order.
     *
     * @return String orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the unique ID of the Order.
     *
     * @param orderId unique ID of the Order
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets the date and time of the Order in the format
     * yyyy-MM-dd HH:mm:ss.
     *
     * @return String dateTime
     */
    public String getDateTime() {
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ;
        return dateTime.format(FORMATTER);
    }

    public Date getDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        Date date = Date.from(localDateTime.atZone(zoneId).toInstant());

        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the ArrayList of OrderEntry objects of the Order.
     *
     * @return ArrayList of OrderEntry objects
     */
    public ArrayList<OrderEntry> getOrderEntries() {
        return orderEntries;
    }

    /**
     * Sets the ArrayList of OrderEntry objects of the Order.
     *
     * @param orderEntries ArrayList of OrderEntry objects
     */
    public void setOrderEntries(ArrayList<OrderEntry> orderEntries) {
        this.orderEntries = orderEntries;
    }

    /**
     * Calculates the subtotal of the Order by summing up the
     * price of each item multiplied by its quantity in the order.
     *
     * @return double subtotal
     */
    @Override
    public double getSubTotal() {

        double subtotal = 0;

        for (OrderEntry i : this.orderEntries) {
            subtotal += (i.getQuantity() * i.getItem().getPrice());
        }

        return subtotal;

    }

    /**
     * Adds one or multiple items to the Order.
     *
     * @param command     Command object representing the user input
     * @param listOfItems ItemList object containing the available items
     */
    public void addOrder(Command command, Menu listOfItems)
            throws OrderException {

        try {
            AddOrderValidation addOrderValidation = new AddOrderValidation();
            AddMultipleAddOrderValidation addMultipleOrderValidation = new AddMultipleAddOrderValidation();
            command.mapArgumentAlias("item", "i");
            command.mapArgumentAlias("items", "I");

            if (command.getArgumentMap().get("item") != null) {
                command = addOrderValidation.validateCommand(command);
                addSingleOrder(command, listOfItems);
            } else if (command.getArgumentMap().get("items") != null) {
                command = addMultipleOrderValidation.validateAddMultipleOrder(command);
                handleMultipleAddOrders(command, listOfItems);
            } else {
                addOrderValidation.checkValidFlag(command);
            }
        } catch (OrderException o) {
            throw new OrderException(o.getMessage());
        }

    }

    /**
     * Adds a single item order to the current order instance
     *
     * @param command     the command object containing the user input
     * @param listOfItems the list of items from which the item is selected
     */
    public void addSingleOrder(Command command, Menu listOfItems) throws OrderException {

        command.mapArgumentAlias("item", "i");
        command.mapArgumentAlias("quantity", "q");

        int itemIndex = handleOrderIndex(command);
        int quantity = handleQuantity(command);

        OrderEntry orderEntry = new OrderEntry(listOfItems.getItems().get(itemIndex), quantity);
        this.orderEntries.add(orderEntry);
    }

    /**
     * Get the item index from user input
     *
     * @param command user input command
     * @return item index
     */
    public int handleOrderIndex(Command command) {
        int itemIndex = Integer.parseInt(command.getArgumentMap().get("item").trim());

        return itemIndex;
    }

    /**
     * Check if quantity of orderEntry is specified
     * If not specified, it defaults to 1
     *
     * @param command User input command
     * @return quantity
     */
    public int handleQuantity(Command command) throws OrderException {

        Ui ui = new Ui();

        int quantity;
        boolean isQuantityANumber = isInteger(command.getArgumentMap().get("quantity"));

        if (command.getArgumentMap().get("quantity") != null) {
            if (!isQuantityANumber) {
                throw new OrderException(ui.getInvalidOrderInteger());
            }
            quantity = Integer.parseInt(command.getArgumentMap().get("quantity").trim());
        } else {
            quantity = 1;
        }

        return quantity;
    }

    /**
     * Adds multiple item orders to the current order instance
     *
     * @param command     the command object containing the user input
     * @param listOfItems the list of items from which the items are selected
     */
    public void handleMultipleAddOrders(Command command, Menu listOfItems) {

        command.mapArgumentAlias("items", "I");

        String[] ordersArguments = command.getArgumentMap().get("items").split(",");

        for (String orderString : ordersArguments) {

            if (orderString.charAt(0) == '[') {
                orderString = orderString.substring(1);
            }

            if (orderString.substring(orderString.length() - 1).equals("]")) {
                orderString = orderString.substring(0, orderString.length() - 1);
            }

            orderString = orderString.trim();
            int itemIndex = Integer.parseInt(orderString.split(":")[0]);
            int quantity = Integer.parseInt(orderString.split(":")[1]);

            OrderEntry orderEntry = new OrderEntry(listOfItems.getItems().get(itemIndex), quantity);
            this.orderEntries.add(orderEntry);
        }

    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException n) {
            return false;
        }

        return true;
    }
}
