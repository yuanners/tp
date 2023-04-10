package order;

import app.Command;

import exception.DuplicateArgumentFoundException;
import exception.item.NoSuchItemException;
import exception.order.MissingQuantityArgumentException;
import exception.order.InvalidIndexNumberFormatException;
import exception.order.MissingOrderFlagException;
import exception.order.InvalidQuantityNumberFormatException;
import exception.order.InvalidIndexNegativeException;
import exception.order.InvalidQuantityNegativeException;
import exception.order.MissingOrderArgumentException;
import exception.order.InvalidIndexOutOfBoundsException;
import exception.order.MissingMultipleOrderArgumentException;
import exception.order.MissingMultpleOrderFlagException;
import exception.order.InvalidMultipleOrderFormatException;
import exception.order.MultipleSimilarItemsFoundException;

import item.Menu;
import payment.Payment;
import ui.Flags;
import ui.TransactionUi;
import validation.order.AddMultipleAddOrderValidation;
import validation.order.AddOrderValidation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

public class Order implements ComputeOrder {

    private String orderId;
    private LocalDateTime dateTime;
    private ArrayList<OrderEntry> orderEntries;
    private String status;
    private String paymentType;


    /**
     * Constructs an Order object with a unique ID
     * default transaction status as COMPLETED
     * payment type as null
     * the current date and time and an empty ArrayList of
     * OrderEntry objects.
     */
    public Order() {
        this.orderId = UUID.randomUUID().toString();
        this.status = "IN PROGRESS";
        this.dateTime = LocalDateTime.now();
        this.orderEntries = new ArrayList<>();
        this.paymentType = "";
    }

    /**
     * Constructs a new Order object.
     *
     * @param command      The command given by the customer to place the order.
     * @param menu         The menu from which the customer chooses items for the order.
     * @param transactions The transaction object to which the order will be appended.
     */
    public Order(Command command, Menu menu, Transaction transactions,
                 TransactionUi transactionUi, Payment payment) throws DuplicateArgumentFoundException {
        this.orderId = UUID.randomUUID().toString();
        this.status = "IN PROGRESS";
        this.dateTime = LocalDateTime.now();
        this.orderEntries = new ArrayList<>();
        this.paymentType = "";
        if (this.addOrder(command, menu, transactionUi)) {
            transactionUi.printOrderAdded(this.getSubTotal());
            payment.makePayment(this);
            transactions.appendOrder(this);
        }
    }

    /**
     * Constructs an Order object with a unique ID
     * default transaction status as COMPLETED
     * payment type as null
     * the current date and time and an ArrayList of
     * OrderEntry objects.
     *
     * @param orderEntries ArrayList of OrderEntry objects
     */
    public Order(ArrayList<OrderEntry> orderEntries) {
        this.orderId = UUID.randomUUID().toString();
        this.status = "COMPLETED";
        this.dateTime = LocalDateTime.now();
        this.orderEntries = orderEntries;
        this.paymentType = "";
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
     * Set the payment type of the order
     *
     * @param paymentType user input payment type
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Gets the date and time of the Order in the format
     * yyyy-MM-dd HH:mm:ss.
     *
     * @return String dateTime
     */
    public String getFormatDateTime() {
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(FORMATTER);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<OrderEntry> getOrderEntries() {
        return orderEntries;
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
    public boolean addOrder(Command command, Menu listOfItems, TransactionUi transactionUi)
            throws DuplicateArgumentFoundException {
        boolean isAdded = false;
        try {
            AddOrderValidation addOrderValidation = new AddOrderValidation(listOfItems);
            AddMultipleAddOrderValidation addMultipleOrderValidation = new AddMultipleAddOrderValidation(listOfItems);

            command.mapArgumentAlias("item", "i");
            command.mapArgumentAlias("items", "I");

            if (command.getArgumentMap().get("item") != null) {
                addOrderValidation.validateItemName(command);
                command = addOrderValidation.validateCommand(command, listOfItems);
                addOrderValidation.validateFlag(command);
                addOrderValidation.validateIndex(command, listOfItems);
                addOrderValidation.validateQuantity(command);
                addSingleOrder(command, listOfItems, transactionUi);
                isAdded = true;
            } else if (command.getArgumentMap().get("items") != null) {
                command = addMultipleOrderValidation.validateFormat(command);
                addMultipleOrderValidation.validateArguments(command, listOfItems);
                handleMultipleAddOrders(command, listOfItems, transactionUi);
                isAdded = true;
            } else {
                addOrderValidation.validateFlag(command);
            }

        } catch (MissingQuantityArgumentException e) {
            transactionUi.printError(Flags.Error.MISSING_QUANTITY_FLAG_ARGUMENT);
        } catch (InvalidIndexNumberFormatException e) {
            transactionUi.printError(Flags.Error.INVALID_ORDER_ITEM_INDEX_FORMAT);
        } catch (MissingOrderFlagException e) {
            transactionUi.printError(Flags.Error.MISSING_ORDER_FLAG);
        } catch (InvalidQuantityNumberFormatException e) {
            transactionUi.printError(Flags.Error.INVALID_QUANTITY_FORMAT);
        } catch (InvalidQuantityNegativeException e) {
            transactionUi.printError(Flags.Error.INVALID_NEGATIVE_QUANTITY);
        } catch (MissingOrderArgumentException e) {
            transactionUi.printError(Flags.Error.MISSING_ORDER_FLAG_ARGUMENT);
        } catch (InvalidIndexOutOfBoundsException e) {
            transactionUi.printError(Flags.Error.INVALID_ORDER_ITEM_INDEX_OUT_OF_BOUNDS);
        } catch (InvalidIndexNegativeException e) {
            transactionUi.printError(Flags.Error.NEGATIVE_ORDER_ITEM_INDEX);
        } catch (MissingMultipleOrderArgumentException e) {
            transactionUi.printError(Flags.Error.MISSING_MULTIPLE_ORDER_ARGUMENT_EXCEPTION);
        } catch (MissingMultpleOrderFlagException e) {
            transactionUi.printError(Flags.Error.MISSING_MULTIPLE_ORDER_FLAG_EXCEPTION);
        } catch (InvalidMultipleOrderFormatException e) {
            transactionUi.printError(Flags.Error.INVALID_MULTIPLE_ORDER_FORMAT_EXCEPTION);
        } catch (MultipleSimilarItemsFoundException e) {
            // Error message is already printed in a separate handler
        } catch (NoSuchItemException e) {
            transactionUi.printError(Flags.Error.NO_SUCH_ITEM);
        }
        return isAdded;
    }

    /**
     * Adds a single item order to the current order instance
     *
     * @param command     the command object containing the user input
     * @param listOfItems the list of items from which the item is selected
     */
    public void addSingleOrder(Command command, Menu listOfItems,
                               TransactionUi transactionUi) throws InvalidQuantityNumberFormatException,
            DuplicateArgumentFoundException {

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
    public int handleQuantity(Command command) throws InvalidQuantityNumberFormatException {

        int quantity;
        boolean isQuantityANumber = isInteger(command.getArgumentMap().get("quantity"));

        if (command.getArgumentMap().get("quantity") != null) {
            if (!isQuantityANumber) {
                throw new InvalidQuantityNumberFormatException();
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
    public void handleMultipleAddOrders(Command command, Menu listOfItems, TransactionUi transactionUi)
            throws DuplicateArgumentFoundException {

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
