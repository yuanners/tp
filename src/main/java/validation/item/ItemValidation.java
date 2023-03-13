package validation.item;

import app.Command;
import exception.InvalidArgumentException;
import utility.Ui;
import validation.Validation;

public class ItemValidation extends Validation {
    Ui ui = new Ui();

    @Override
    public void validateArgument(Command arg) throws InvalidArgumentException {
        try {
            super.validateArgument(arg);
        } catch (InvalidArgumentException e) {
            throw new InvalidArgumentException(ui.ERROR_MESSAGE);
        }
    }

    public boolean isDouble(String input) {
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
