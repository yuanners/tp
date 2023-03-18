package statistic;

import app.Command;
import exception.ItemException;
import exception.OrderException;
import item.Menu;
import item.MenuAssistant;
import order.Order;
import order.Transaction;
import org.junit.jupiter.api.Test;
import utility.Parser;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StatisticTest {

    public Menu menu;
    public Transaction transactions;

    public void createItem(Command c) {
        try {
            menu.addItem(c, menu);
        } catch (ItemException e) {
            System.out.println(e);
        }

    }
    public void createOrder(Command c){
        try {
            Order order = new Order();
            order.addOrder(c, menu);
            transactions.appendOrder(order);
        } catch (OrderException e) {
            System.out.println(e);
        }
    }

    public void generateTestData(){
        Command c;

        c = new Command("additem -n \"Chicken rice\" -p 3.00");
        createItem(c);
        c = new Command("additem -n \"Fish ball noodle\" -p 2.50");
        createItem(c);
        c = new Command("additem -n \"Mee Rubus\" -p 3.50");
        createItem(c);
        c = new Command("additem -n \"Fish soup\" -p 5.00");
        createItem(c);
        c = new Command("additem -n \"Rojak\" -p 2.00");
        createItem(c);

        c = new Command("addorder -i 0 -q 2");
        System.out.println(c.getArgumentMap());
        createOrder(c);
        c = new Command("addorder -i 1 -q 3");
        createOrder(c);
        c = new Command("addorder -i 2 -q 1");
        createOrder(c);
        c = new Command("addorder -i 3 -q 4");
        createOrder(c);
        c = new Command("addorder -i 4 -q 10");
        createOrder(c);
    }

    @Test
    void totalRevenue() {
        this.menu = new Menu();
        this.transactions = new Transaction();

        generateTestData();

        menu.displayList();
        transactions.displayList();

    }
}