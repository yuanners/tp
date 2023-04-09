package order;

import app.Command;
import exception.DuplicateArgumentFoundException;
import item.Item;
import org.junit.jupiter.api.Test;
import payment.Refund;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RefundTest {
    @Test
    void refundOrderTest() throws DuplicateArgumentFoundException {
        Transaction transaction = new Transaction("orders.json");
        Item item1 = new Item("chicken rice", 2.00);

        OrderEntry orderEntry1 = new OrderEntry(item1, 10);

        ArrayList<OrderEntry> orderEntries = new ArrayList<>();
        orderEntries.add(orderEntry1);
        Order order = new Order(orderEntries);
        transaction.appendOrder(order);
        String ID = order.getOrderId();
        String input = "/refundorder -i " + ID;
        Command command = new Command(input);
        Refund refund = new Refund();
        refund.getOrder(command, transaction);
        assertEquals("REFUNDED", order.getStatus());
    }

    @Test
    void negativeRefundOrderTest() throws DuplicateArgumentFoundException {
        Transaction transaction = new Transaction("orders.json");
        Item item1 = new Item("chicken rice", 2.00);

        OrderEntry orderEntry1 = new OrderEntry(item1, 10);

        ArrayList<OrderEntry> orderEntries = new ArrayList<>();
        orderEntries.add(orderEntry1);
        Order order = new Order(orderEntries);
        transaction.appendOrder(order);
        String ID = "invalidID";
        String input = "/refundorder -i " + ID;
        Command command = new Command(input);
        Refund refund = new Refund();
        refund.getOrder(command, transaction);
        assertEquals("COMPLETED", order.getStatus());
    }

    @Test
    void cancelRefundOrderTest() throws DuplicateArgumentFoundException {
        Transaction transaction = new Transaction("orders.json");
        Item item1 = new Item("chicken rice", 2.00);

        OrderEntry orderEntry1 = new OrderEntry(item1, 10);

        ArrayList<OrderEntry> orderEntries = new ArrayList<>();
        orderEntries.add(orderEntry1);
        Order order = new Order(orderEntries);
        transaction.appendOrder(order);
        String input = "/cancel";
        Command command = new Command(input);
        Refund refund = new Refund();
        refund.refundTransaction(command, transaction);
        assertEquals("COMPLETED", order.getStatus());
    }

    @Test
    void duplicateRefundOrderTest() throws DuplicateArgumentFoundException {
        Transaction transaction = new Transaction("orders.json");
        Item item1 = new Item("chicken rice", 2.00);

        OrderEntry orderEntry1 = new OrderEntry(item1, 10);

        ArrayList<OrderEntry> orderEntries = new ArrayList<>();
        orderEntries.add(orderEntry1);
        Order order = new Order(orderEntries);
        order.setStatus("REFUNDED");
        transaction.appendOrder(order);
        String ID = order.getOrderId();
        String input = "/refundorder -i " + ID;
        Command command = new Command(input);
        Refund refund = new Refund();
        refund.getOrder(command, transaction);
        assertEquals("REFUNDED", order.getStatus());
    }

    @Test
    void refundOrderWithMultipleItemsTest() throws DuplicateArgumentFoundException {
        Transaction transaction = new Transaction("orders.json");
        Item item1 = new Item("chicken rice", 2.00);
        Item item2 = new Item("beef noodles", 3.50);

        OrderEntry orderEntry1 = new OrderEntry(item1, 10);
        OrderEntry orderEntry2 = new OrderEntry(item2, 5);

        ArrayList<OrderEntry> orderEntries = new ArrayList<>();
        orderEntries.add(orderEntry1);
        orderEntries.add(orderEntry2);
        Order order = new Order(orderEntries);
        transaction.appendOrder(order);

        String ID = order.getOrderId();
        String input = "/refundorder -i " + ID;
        Command command = new Command(input);
        Refund refund = new Refund();
        refund.getOrder(command, transaction);
        assertEquals("REFUNDED", order.getStatus());
    }

    @Test
    void testRefundOnEmptyTransaction() throws DuplicateArgumentFoundException {
        Command arg = new Command("/refundorder");
        Transaction transaction = new Transaction("orders.json");
        Refund refund = new Refund();
        assertThrows(NullPointerException.class, () -> refund.getOrder(arg, transaction));
    }


}
