package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import app.Command;
import app.MoneyGoWhere;
import org.junit.jupiter.api.Test;
import utility.Ui;
import validation.invalidArgumentException;

import java.util.Scanner;

class OrderTest {

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
        } catch (invalidArgumentException e) {
            ui.println(e.getMessage());
        }

    }

    @Test
    public void OrderTest() {
        MoneyGoWhere moneyGoWhere = new MoneyGoWhere();
        runTest("additem -p 2.50 -n chicken rice", moneyGoWhere);
        runTest("addorder -i 0 -q 3 -d", moneyGoWhere);

        assertEquals("chicken rice" , moneyGoWhere.OrderEntry.getItem().getName());
        assertEquals(3 , moneyGoWhere.OrderEntry.getQuantity () );
    }


}

