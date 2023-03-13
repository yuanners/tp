package validation.item;

import app.Command;
import exception.InvalidArgumentException;
import utility.Ui;
import validation.Validation;

public class ItemValidation extends Validation {
    Ui ui = new Ui();

    /**
     * Checks if the given input is a double
     * @param input Given input
     * @return Validation result (true/false)
     */
    public boolean isDouble(String input) {
        try {
            Double.valueOf(input);
        } catch (NumberFormatException e) {
            ui.printRequiresNumber();
            return false;
        }
        return true;
    }

    /*
    public int getNumOfDecimalPoints(String input) {
        int indexOfDecimalPoint = input.indexOf(".");
        int numOfDecimalPoint = input.length() - indexOfDecimalPoint - 1;
        return numOfDecimalPoint;
    }
    */
}
