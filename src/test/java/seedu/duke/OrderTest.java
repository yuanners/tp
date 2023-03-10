package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import app.Command;
import app.MoneyGoWhere;
import order.Order;
import order.OrderEntry;
import org.junit.jupiter.api.Test;
import utility.Ui;
import exception.InvalidArgumentException;

import java.util.ArrayList;

class OrderTest {

    public void sampleTest() {
        assertTrue(true);
    }

    public void runTest(String input, MoneyGoWhere moneyGoWhere) {
        Ui ui = new Ui();
        ui.printUserInput();
        Command command = new Command(input);
        try {
            moneyGoWhere.handleCommand(command);
        } catch (InvalidArgumentException e) {
            ui.println(e.getMessage());
        }
    }

    @Test
    public void orderTest() {
//        MoneyGoWhere moneyGoWhere = new MoneyGoWhere();
//        runTest("additem -p 2.50 -n chicken rice", moneyGoWhere);
//        runTest("addorder -i 0 -q 3", moneyGoWhere);
//        ArrayList<Order> testList = moneyGoWhere.orderList.getOrderList();
//        ArrayList<OrderEntry> testEntries = testList.get(testList.size() - 1).getOrderEntries();
//        assertEquals("chicken rice", testEntries.get(testEntries.size() - 1).getItem().getName());
//        assertEquals(3, testEntries.get(testEntries.size() - 1).getQuantity());
    }

}

