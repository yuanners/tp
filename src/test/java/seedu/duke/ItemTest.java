package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import app.Command;
import app.MoneyGoWhere;
import org.junit.jupiter.api.Test;
import utility.Ui;
import exception.InvalidArgumentException;

class ItemTest {

    public void sampleTest() {
        assertTrue(true);
    }

    public void runTest(String input, MoneyGoWhere moneyGoWhere) {

        Ui ui = new Ui();


        ui.printUserInput();
        String userInput = input;



        Command command = new Command(userInput);

        try {
            moneyGoWhere.handleCommand(command);
        } catch (InvalidArgumentException e) {
            ui.println(e.getMessage());
        }

    }

    @Test
    public void itemTest() {
        MoneyGoWhere moneyGoWhere = new MoneyGoWhere();
        runTest("additem -p 2.50 -n chicken rice", moneyGoWhere);

        assertEquals("chicken rice" , moneyGoWhere.items.getItems().get(moneyGoWhere.items.getItems().size() - 1).getName() );
        assertEquals(2.5 , moneyGoWhere.items.getItems().get(moneyGoWhere.items.getItems().size() - 1).getPrice() );

        runTest("listitem", moneyGoWhere);

        runTest("deleteitem -i 0", moneyGoWhere);
    }

}
