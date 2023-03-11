package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import app.Command;
import app.MoneyGoWhere;
import org.junit.jupiter.api.Test;
import utility.Ui;
import exception.InvalidArgumentException;

class OrderTest {

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

        MoneyGoWhere moneyGoWhere = new MoneyGoWhere();
        runTest("addorder -i 0 -q 169", moneyGoWhere);

//        moneyGoWhere.orderList.getOrderList()
//                .get(moneyGoWhere.orderList.getOrderList().size() - 1);

        assertEquals("Chicken Rice ", moneyGoWhere.orderList.getOrderList()
                .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.orderList.getOrderList()
                        .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                        .getOrderEntries().size() - 1).getItem().getName());

        assertEquals(169, moneyGoWhere.orderList.getOrderList()
                .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.orderList.getOrderList()
                        .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                        .getOrderEntries().size() - 1).getQuantity());

    }

    @Test
    public void orderTest2() {

        MoneyGoWhere moneyGoWhere = new MoneyGoWhere();
        runTest("addorder -I [0 69, 1 169]", moneyGoWhere);

        assertEquals("Chicken Rice ", moneyGoWhere.orderList.getOrderList()
                .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.orderList.getOrderList()
                        .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                        .getOrderEntries().size() - 2).getItem().getName());

        assertEquals(69, moneyGoWhere.orderList.getOrderList()
                .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.orderList.getOrderList()
                        .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                        .getOrderEntries().size() - 2).getQuantity());

        assertEquals("chicken rice", moneyGoWhere.orderList.getOrderList()
                .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.orderList.getOrderList()
                        .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                        .getOrderEntries().size() - 1).getItem().getName());

        assertEquals(169, moneyGoWhere.orderList.getOrderList()
                .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.orderList.getOrderList()
                        .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                        .getOrderEntries().size() - 1).getQuantity());

    }

    @Test
    public void orderTest3() {

        MoneyGoWhere moneyGoWhere = new MoneyGoWhere();
        runTest("addorder -i 0 -q 169", moneyGoWhere);

        assertEquals("Chicken Rice ", moneyGoWhere.orderList.getOrderList()
                .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.orderList.getOrderList()
                        .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                        .getOrderEntries().size() - 1).getItem().getName());

        assertEquals(169, moneyGoWhere.orderList.getOrderList()
                .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.orderList.getOrderList()
                        .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                        .getOrderEntries().size() - 1).getQuantity());

    }

    @Test
    public void orderTest4() {

        MoneyGoWhere moneyGoWhere = new MoneyGoWhere();
        runTest("addorder --items [0 69, 1 169]", moneyGoWhere);

        assertEquals("Chicken Rice ", moneyGoWhere.orderList.getOrderList()
                .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.orderList.getOrderList()
                        .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                        .getOrderEntries().size() - 2).getItem().getName());

        assertEquals(69, moneyGoWhere.orderList.getOrderList()
                .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.orderList.getOrderList()
                        .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                        .getOrderEntries().size() - 2).getQuantity());

        assertEquals("chicken rice", moneyGoWhere.orderList.getOrderList()
                .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.orderList.getOrderList()
                        .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                        .getOrderEntries().size() - 1).getItem().getName());

        assertEquals(169, moneyGoWhere.orderList.getOrderList()
                .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.orderList.getOrderList()
                        .get(moneyGoWhere.orderList.getOrderList().size() - 1)
                        .getOrderEntries().size() - 1).getQuantity());

    }


}

