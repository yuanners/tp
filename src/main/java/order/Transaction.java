package order;

import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import ui.TransactionUi;
import utility.Store;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * The OrderList class represents a list of orders.
 */
public class Transaction {
    /**
     * The list of orders.
     */
    private ArrayList<Order> transactions;

    /**
     * The store used to load and save the order list.
     */
    private Store store;

    private TransactionUi transactionUi;
    private final String ORDER_DATA_FILE = "orders.json";

    public Transaction() {
        this.store = new Store(ORDER_DATA_FILE);
        Type type = new TypeToken<ArrayList<Order>>() {
        }.getType();

        try {
            this.transactions = store.load(type);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            this.transactions = new ArrayList<>();
        } catch (JsonParseException e) {
            System.out.println(e.getMessage());
            this.transactions = new ArrayList<>();
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            this.transactions = new ArrayList<>();
        }
    }

    public Transaction(boolean isTest) {
        this.transactions = new ArrayList<>();
    }

    /**
     * Appends the given order to the order list and saves the changes.
     *
     * @param order the order to append to the order list
     */
    public void appendOrder(Order order) {
        this.transactions.add(order);
        save();
    }

    /**
     * Returns the list of orders.
     *
     * @return the list of orders
     */
    public ArrayList<Order> getOrderList() {
        return this.transactions;
    }

    /**
     * Displays the order list using the UI class.
     */
    public void displayList() {
        TransactionUi transactionUi = new TransactionUi();
        transactionUi.printOrderList(this.transactions);
        transactionUi.printSuccessfulListOrder();
    }

    /**
     * Saves the order list using the store.
     */
    public void save() {
        try {
            store.save(transactions);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
