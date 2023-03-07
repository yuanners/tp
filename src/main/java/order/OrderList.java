package order;

import app.Command;
import item.Item;
import item.ItemList;
import utility.Parser;
import validation.orderValidation;

import java.util.ArrayList;
import java.util.Arrays;

public class OrderList {
    private ArrayList<Order> orders;

    public OrderList() {
        orders = new ArrayList<>();
    }

    public void appendOrder(Order order) {
        this.orders.add(order);
    }



    // print stuff
    public void displayList() {
        System.out.println("display something");
//        System.out.printf("| %-5s | %-25s | %-5s |\n","Index","Name","Price");
//        System.out.println("| " + "-".repeat(5) + " | " + "-".repeat(25) + " | " + "-".repeat(5) + " |");
//        for (int i = 0; i < orders.size(); i++) {
//            System.out.printf("| %-5d | %-25s | %-5.2f |\n", i, orders.get(i).getName(), items.get(i).getPrice());
//        }
    }

}
