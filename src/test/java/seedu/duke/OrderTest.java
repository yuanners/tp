package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import app.Command;
import app.MoneyGoWhere;
import exception.InvalidFlagException;
import org.junit.jupiter.api.Test;
import utility.Ui;
import exception.InvalidArgumentException;

class OrderTest {

    public void runTest(String input, MoneyGoWhere moneyGoWhere) {
        Ui ui = new Ui();
        ui.promptUserInput();
        Command command = new Command(input);
        try {
            moneyGoWhere.handleCommand(command);
        } catch (InvalidArgumentException e) {
            ui.println(e.getMessage());
        } catch(InvalidFlagException e) {
            ui.invalidOrderCommand();
        }
    }

    @Test
    public void orderTest() {

        MoneyGoWhere moneyGoWhere = new MoneyGoWhere();
        runTest("addorder -i 0 -q 169", moneyGoWhere);

        assertEquals("chicken rice", moneyGoWhere.transactions.getOrderList()
                .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.transactions.getOrderList()
                        .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                        .getOrderEntries().size() - 1).getItem().getName());

        assertEquals(169, moneyGoWhere.transactions.getOrderList()
                .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.transactions.getOrderList()
                        .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                        .getOrderEntries().size() - 1).getQuantity());

    }

    @Test
    public void orderTest2() {

        MoneyGoWhere moneyGoWhere = new MoneyGoWhere();
        runTest("addorder -I [0 69, 1 169]", moneyGoWhere);

        assertEquals("chicken rice ", moneyGoWhere.transactions.getOrderList()
                .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.transactions.getOrderList()
                        .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                        .getOrderEntries().size() - 2).getItem().getName());

        assertEquals(69, moneyGoWhere.transactions.getOrderList()
                .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.transactions.getOrderList()
                        .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                        .getOrderEntries().size() - 2).getQuantity());

        assertEquals("chicken rice ", moneyGoWhere.transactions.getOrderList()
                .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.transactions.getOrderList()
                        .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                        .getOrderEntries().size() - 1).getItem().getName());

        assertEquals(169, moneyGoWhere.transactions.getOrderList()
                .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.transactions.getOrderList()
                        .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                        .getOrderEntries().size() - 1).getQuantity());

    }

    @Test
    public void orderTest3() {

        MoneyGoWhere moneyGoWhere = new MoneyGoWhere();
        runTest("addorder -i 0 -q 169", moneyGoWhere);

        assertEquals("chicken rice", moneyGoWhere.transactions.getOrderList()
                .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.transactions.getOrderList()
                        .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                        .getOrderEntries().size() - 1).getItem().getName());

        assertEquals(169, moneyGoWhere.transactions.getOrderList()
                .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.transactions.getOrderList()
                        .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                        .getOrderEntries().size() - 1).getQuantity());

    }

    @Test
    public void orderTest4() {

        MoneyGoWhere moneyGoWhere = new MoneyGoWhere();
        runTest("addorder --items [0 69, 1 169]", moneyGoWhere);

        assertEquals("chicken rice ", moneyGoWhere.transactions.getOrderList()
                .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.transactions.getOrderList()
                        .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                        .getOrderEntries().size() - 2).getItem().getName());

        assertEquals(69, moneyGoWhere.transactions.getOrderList()
                .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.transactions.getOrderList()
                        .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                        .getOrderEntries().size() - 2).getQuantity());

        assertEquals("chicken rice ", moneyGoWhere.transactions.getOrderList()
                .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.transactions.getOrderList()
                        .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                        .getOrderEntries().size() - 1).getItem().getName());

        assertEquals(169, moneyGoWhere.transactions.getOrderList()
                .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                .getOrderEntries()
                .get(moneyGoWhere.transactions.getOrderList()
                        .get(moneyGoWhere.transactions.getOrderList().size() - 1)
                        .getOrderEntries().size() - 1).getQuantity());

    }


}

