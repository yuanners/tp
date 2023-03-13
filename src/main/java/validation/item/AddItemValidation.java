package validation.item;

import app.Command;
import exception.InvalidArgumentException;
import utility.Ui;

public class AddItemValidation extends ItemValidation {
    private Ui ui = new Ui();

    public boolean isValid(Command c) {
        try {
            super.validateArgument(c);
        } catch (InvalidArgumentException e) {
            ui.println(e.getMessage());
            return false;
        }

        boolean nameIsValid = isValidName(c);

        boolean priceIsValid = isValidPrice(c);

        return nameIsValid && priceIsValid;
    }

    public boolean isValidName(Command c) {
        if (c.getArgumentMap().get("n").length() > 25) {
            ui.printMaxLengthError();
            return false;
        }

        if (c.getArgumentMap().get("n").length() < 1) {
            ui.printMinLengthError("Name");
            return false;
        }

        return true;
    }

    /**
     * Checks if the given input for price is valid
     *
     * @param c Given command
     * @return Valdiation result (true/false)
     */
    public boolean isValidPrice(Command c) {
        String price = c.getArgumentMap().get("p");
        price = price.trim();

        if (price.length() < 1) {
            ui.printMinLengthError("Price");
            return false;
        }

        double tempPrice;

        if (super.isDouble(price)) {
            tempPrice = Double.parseDouble(price);
        } else {
            return false;
        }

        if (tempPrice < 0.00) {
            ui.printNegativeError();
            return false;
        }

        int numOfDecimalPoint = price.length() - price.indexOf('.') - 1;

        if (numOfDecimalPoint > 2) {
            ui.printInvalidDouble();
            return false;
        }

        return true;
    }

}
