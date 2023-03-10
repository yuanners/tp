package order;

import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import utility.Store;
import utility.Ui;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class OrderList {
    private ArrayList<Order> orders;
    private Store store;

    public OrderList() {
        this.store = new Store("orders.json");
        Type type = new TypeToken<ArrayList<Order>>() {}.getType();

        try {
            this.orders = store.load(type);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            this.orders = new ArrayList<>();
        } catch (JsonParseException e) {
            System.out.println(e.getMessage());
            this.orders = new ArrayList<>();
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            this.orders = new ArrayList<>();
        }
    }

    public void appendOrder(Order order) {
        this.orders.add(order);
        save();
    }

    // Print stuff
    public void displayList() {
        Ui ui = new Ui();
        ui.printOrderList(this.orders);
    }

    public void save() {
        try {
            store.save(orders);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
