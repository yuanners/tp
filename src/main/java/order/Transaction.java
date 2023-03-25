package order;

import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import ui.TransactionUi;
import utility.Store;
import utility.Ui;

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

    public Transaction() {
        this.store = new Store("orders.json");
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
        Ui ui = new Ui();
        TransactionUi transactionUi1 = new TransactionUi();
        ui.printOrderList(this.transactions);
        transactionUi1.printSuccessfulListOrder();

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
