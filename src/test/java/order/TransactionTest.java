package order;

import item.Item;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {

    @Test
    void transactionTest() {
        Item item1 = new Item("chicken rice", 2.00);
        Item item2 = new Item("bubble tea", 5.00);

        OrderEntry orderEntry1 = new OrderEntry(item1, 10);
        OrderEntry orderEntry2 = new OrderEntry(item2, 10);

        ArrayList<OrderEntry> orderEntries = new ArrayList<>();
        orderEntries.add(orderEntry1);
        orderEntries.add(orderEntry2);

        Order order = new Order(orderEntries);

        Transaction transaction = new Transaction("orders.json");
        transaction.appendOrder(order);

        assertEquals(70, transaction.getOrderList().get(transaction.getOrderList().size() - 1).getSubTotal());

        assertEquals(10, transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(0).getQuantity());
        assertEquals(10, transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(1).getQuantity());

        assertEquals("chicken rice", transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(0).getItem().getName());
        assertEquals("bubble tea", transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(1).getItem().getName());

        assertEquals(2, transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(0).getItem().getPrice());
        assertEquals(5, transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(1).getItem().getPrice());
    }

    @Test
    void transactionBoundaryLimitTest() {
        Item item1 = new Item("earl grey milk tea avocado pudding jelly more pearls less ice 50% sugar",
                99.0);
        Item item2 = new Item("teh", 0.5);

        OrderEntry orderEntry1 = new OrderEntry(item1, 6969);
        OrderEntry orderEntry2 = new OrderEntry(item2, 6969);

        ArrayList<OrderEntry> orderEntries = new ArrayList<>();
        orderEntries.add(orderEntry1);
        orderEntries.add(orderEntry2);

        Order order = new Order(orderEntries);

        Transaction transaction = new Transaction("orders.json");
        transaction.appendOrder(order);

        assertEquals(693415.5, transaction.getOrderList()
                .get(transaction.getOrderList().size() - 1).getSubTotal());

        assertEquals(6969, transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(0).getQuantity());
        assertEquals(6969, transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(1).getQuantity());

        assertEquals("earl grey milk tea avocado pudding jelly more pearls less ice 50% sugar",
                transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(0).getItem().getName());
        assertEquals("teh", transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(1).getItem().getName());

        assertEquals(99, transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(0).getItem().getPrice());
        assertEquals(0.5, transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(1).getItem().getPrice());
    }

}
