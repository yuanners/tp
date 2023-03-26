package seedu.moneygowhere;

import item.Item;
import order.OrderEntry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OrderNegativeTest {

    @Test
    void orderEntryNegativeTest() {

        Item item = new Item("chicken rice", 2.00);
        OrderEntry orderEntry = new OrderEntry(item, 10);

        assertNotEquals(9, orderEntry.getQuantity());
        assertNotEquals("chickenest rice", orderEntry.getItem().getName());
        assertNotEquals(2.10, orderEntry.getItem().getPrice());

    }

}
