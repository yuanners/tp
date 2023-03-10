package validation.item;

import app.Command;
import exception.InvalidArgumentException;
import utility.Ui;
import validation.Validation;

public class ItemValidation extends Validation {

    @Override
    public void validateArgument(Command arg) throws InvalidArgumentException {
        Ui ui = new Ui();
        try {
            super.validateArgument(arg);
        } catch (InvalidArgumentException e) {
            throw new InvalidArgumentException(ui.ERROR_MESSAGE);
        }

    }

    public boolean isInteger(String input) {
        Ui ui = new Ui();
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException n) {
            ui.println(ui.INTEGER_ERROR);
            return false;
        }
        return true;
    }

    public boolean isDouble(String input) {
        Ui ui = new Ui();
        try {
            Double.valueOf(input);
        } catch (NumberFormatException e) {
            ui.println(ui.INVALID_PRICE_ERROR);
            return false;
        }
        return true;
    }

    public int getNumOfDecimalPoints(String input) {
        int indexOfDecimalPoint = input.indexOf(".");
        int numOfDecimalPoint = input.length() - indexOfDecimalPoint - 1;
        return numOfDecimalPoint;
    }

}
