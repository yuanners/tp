package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import app.Command;
import app.MoneyGoWhere;
import org.junit.jupiter.api.Test;
import utility.Ui;
import exception.InvalidArgumentException;

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
    }

}

