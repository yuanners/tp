package order;

import app.Command;
import exception.DuplicateArgumentFoundException;
import item.Item;
import org.junit.jupiter.api.Test;
import payment.Payment;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentTest {
    @Test
    void paymentNegativeTest() throws DuplicateArgumentFoundException {
        Transaction transaction = new Transaction("orders.json");
        Item item1 = new Item("chicken rice", 2.00);

        OrderEntry orderEntry1 = new OrderEntry(item1, 10);

        ArrayList<OrderEntry> orderEntries = new ArrayList<>();
        orderEntries.add(orderEntry1);
        Order order = new Order(orderEntries);
        Command command;
        Payment payment = new Payment();
        assertThrows(NoSuchElementException.class, ()-> payment.makePayment(order));
    }
}
