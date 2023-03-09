package order;

import utility.Ui;

import java.util.ArrayList;

public class OrderList {
    private ArrayList<Order> orders;

    public OrderList() {
        orders = new ArrayList<>();
    }

    public void appendOrder(Order order) {
        this.orders.add(order);
    }

    public ArrayList<Order> getOrderList() {
        return this.orders;
    }

    // Print stuff
    public void displayList() {
        Ui ui = new Ui();
        ui.printOrderList(this.orders);
    }

}
