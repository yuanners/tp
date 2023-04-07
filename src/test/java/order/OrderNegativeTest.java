package order;

import item.Item;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OrderNegativeTest {

    @Test
    void orderEntryNegativeTest() {

        Item item = new Item("chicken rice", 2.00);
        OrderEntry orderEntry = new OrderEntry(item, 11);

        assertNotEquals(9, orderEntry.getQuantity());
        assertNotEquals("chickenest rice", orderEntry.getItem().getName());
        assertNotEquals(2.10, orderEntry.getItem().getPrice());

    }

    @Test
    void orderNegativeTest() {

        Item item1 = new Item("chicken rice", 2.00);
        Item item2 = new Item("bubble tea", 5.00);

        OrderEntry orderEntry1 = new OrderEntry(item1, 10);
        OrderEntry orderEntry2 = new OrderEntry(item2, 10);

        ArrayList<OrderEntry> orderEntries = new ArrayList<>();
        orderEntries.add(orderEntry1);
        orderEntries.add(orderEntry2);

        Order order = new Order(orderEntries);

        assertNotEquals("70", order.getSubTotal());
        assertNotEquals(69.99999, order.getSubTotal());
        assertNotEquals(70.0000001, order.getSubTotal());

        assertNotEquals("chickenest rice", order.getOrderEntries().get(0).getItem().getName());
        assertNotEquals("bubblest tea", order.getOrderEntries().get(1).getItem().getName());

        assertNotEquals(12, order.getOrderEntries().get(0).getItem().getPrice());
        assertNotEquals(15, order.getOrderEntries().get(1).getItem().getPrice());

    }

    @Test
    void orderBoundaryLimitNegativeTest() {

        Item item1 = new Item("earl grey milk tea avocado pudding jelly more pearls less ice 50% sugar",
                29.0);
        Item item2 = new Item("teh", 0.5);

        OrderEntry orderEntry1 = new OrderEntry(item1, 1);
        OrderEntry orderEntry2 = new OrderEntry(item2, 69);

        ArrayList<OrderEntry> orderEntries = new ArrayList<>();
        orderEntries.add(orderEntry1);
        orderEntries.add(orderEntry2);

        Order order = new Order(orderEntries);

        assertNotEquals(64, order.getSubTotal());
        assertNotEquals(62, order.getSubTotal());
        assertNotEquals(63.555, order.getSubTotal());

        assertNotEquals("earl_grey milk tea avocado pudding jelly more pearls less ice 50% sugar",
                order.getOrderEntries().get(0).getItem().getName());
        assertNotEquals("_teh", order.getOrderEntries().get(1).getItem().getName());

        assertNotEquals(30, order.getOrderEntries().get(0).getItem().getPrice());
        assertNotEquals(0.51, order.getOrderEntries().get(1).getItem().getPrice());

    }

}
