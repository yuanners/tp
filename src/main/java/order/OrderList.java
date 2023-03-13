package order;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import utility.Store;
import utility.Ui;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * The OrderList class represents a list of orders.
 */
public class OrderList {
    /**
     * The list of orders.
     */
    private ArrayList<Order> orders;

    /**
     * The store used to load and save the order list.
     */
    private Store store;

    /**
     * Constructs an empty order list.
     */
    public OrderList() {
        this.store = new Store("orders.json");
        Type type = new TypeToken<ArrayList<Order>>() {
        }.getType();

        try {
            this.orders = store.load(type);
        } catch (IOException e) {
            System.out.println("File does not exist. Initializing new list");
            this.orders = new ArrayList<>();
        } catch (JsonParseException | NumberFormatException e) {
            System.out.println("Data file corrupted. Do you want to initialize new list? [Y]es or [N]o");
            this.orders = new ArrayList<>();
        }
    }

    public OrderList(Store store) {
        this.store = store;
        Type type = new TypeToken<ArrayList<Order>>() {
        }.getType();

        try {
            this.orders = store.load(type);
        } catch (IOException e) {
            System.out.println("File does not exist. Initializing new list");
            this.orders = new ArrayList<>();
        } catch (JsonParseException | NumberFormatException e) {
            System.out.println("Data file corrupted. Do you want to initialize new list? [Y]es or [N]o");
            this.orders = new ArrayList<>();
        }
    }

    /**
     * Appends the given order to the order list and saves the changes.
     *
     * @param order the order to append to the order list
     */
    public void appendOrder(Order order) {
        this.orders.add(order);
        save();
    }

    /**
     * Returns the list of orders.
     *
     * @return the list of orders
     */
    public ArrayList<Order> getOrderList() {
        return this.orders;
    }

    /**
     * Displays the order list using the UI class.
     */
    public void displayList() {
        Ui ui = new Ui();
        ui.printOrderList(this.orders);
    }

    /**
     * Saves the order list using the store.
     */
    public void save() {
        try {
            store.save(orders);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
