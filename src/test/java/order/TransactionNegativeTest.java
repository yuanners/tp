package order;

import item.Item;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TransactionNegativeTest {

    @Test
    void transactionNegativeTest() {
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

        assertNotEquals(69.999, transaction.getOrderList()
                .get(transaction.getOrderList().size() - 1).getSubTotal());
        assertNotEquals(70.000001, transaction.getOrderList()
                .get(transaction.getOrderList().size() - 1).getSubTotal());

        assertNotEquals(9, transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(0).getQuantity());
        assertNotEquals(11, transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(0).getQuantity());
        assertNotEquals(9, transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(1).getQuantity());
        assertNotEquals(11, transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(1).getQuantity());

        assertNotEquals("chicken_rice", transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(0).getItem().getName());
        assertNotEquals("bubble_tea", transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(1).getItem().getName());

        assertNotEquals(2.0001, transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(0).getItem().getPrice());
        assertNotEquals(4.9999, transaction.getOrderList().get(transaction.getOrderList().size() - 1)
                .getOrderEntries().get(1).getItem().getPrice());
    }

}
