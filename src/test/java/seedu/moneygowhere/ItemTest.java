package seedu.moneygowhere;

import static org.junit.jupiter.api.Assertions.assertEquals;

import app.Command;
import app.MoneyGoWhere;
import org.junit.jupiter.api.Test;
import utility.Ui;

import java.util.Scanner;

class ItemTest {
    MoneyGoWhere moneyGoWhere;

    public ItemTest() {
        moneyGoWhere = new MoneyGoWhere();
    }

    public void runTest(String input, MoneyGoWhere moneyGoWhere) {

        Ui ui = new Ui();


        ui.promptUserInput();
        String userInput = input;

        Command command = new Command(userInput);

        moneyGoWhere.handleCommand(command);

    }

    @Test
    public void itemTest() {

        runTest("additem -p 20.1 -n \"chicken rice100\"", moneyGoWhere);
        assertEquals("chicken rice100", moneyGoWhere.menu.getItems().
                get(moneyGoWhere.menu.getItems().size() - 1).getName());

        assert moneyGoWhere.menu.getItems().
                get(moneyGoWhere.menu.getItems().size() - 1)
                .getName().equals("chicken rice100"): "Item name should be chicken rice100";

        assertEquals(20.10, moneyGoWhere.menu.getItems().
                get(moneyGoWhere.menu.getItems().size() - 1).getPrice());

    }

    @Test
    public void itemTest2() {
        runTest("additem -p 2kuku0.01 -n \"chicken rice3\"", moneyGoWhere);
        assert moneyGoWhere.menu.getItems().
                get(moneyGoWhere.menu.getItems().size() - 1)
                .getName().equals("chicken rice100"): "Item name should be chicken rice100";

    }

    @Test
    public void itemTest3() {
        // max 2dp error
        runTest("additem -p 20.0001 -n \"chicken rice4\"", moneyGoWhere);
        assert moneyGoWhere.menu.getItems().
                get(moneyGoWhere.menu.getItems().size() - 1)
                .getName().equals("chicken rice100"): "Item name should be chicken rice100";

    }

    @Test
    public void itemTest4() {

        runTest("listitem", moneyGoWhere);

        runTest("deleteitem -i " + (moneyGoWhere.menu.getItems().size()-1), moneyGoWhere);

        runTest("listitem", moneyGoWhere);
    }

}
