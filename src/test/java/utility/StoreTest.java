package utility;

import app.Command;
import item.Item;
import item.ItemList;
import order.Order;
import order.OrderList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {
    @Test
    void save_order() {
        Parser parser = new Parser();

        Store storeOrders = new Store("test_order2.json");
        Store storeItems = new Store("test_item.json");

        OrderList ol = new OrderList(storeOrders);
        ItemList il = new ItemList(storeItems);

        Command command = new Command("addorder -i 0 -q 20");

        System.out.println("HEELLO");

        Order order = new Order();
        order.addOrder(command, parser, il);
        ol.save();
    }
}