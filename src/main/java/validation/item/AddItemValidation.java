package validation.item;

import app.Command;
import exception.InvalidArgumentException;
import utility.Ui;

public class AddItemValidation extends ItemValidation {

    public boolean isValidFormat(Command c) throws InvalidArgumentException {

        Ui ui = new Ui();

        String args = c.getArgumentString();

        if (!(args.contains("n") || args.contains("name")) || !(args.contains("p") || args.contains("price"))) {
            throw new InvalidArgumentException(ui.INVALID_ADDITEM_FORMAT);
        }

        return true;
    }

    public boolean isValid(Command c) {
        Ui ui = new Ui();

        try {
            super.validateArgument(c);
        } catch (InvalidArgumentException e) {
            ui.println(e.getMessage());
            return false;
        }

        boolean nameIsValid = isValidName(c, ui);

        boolean priceIsValid = isValidPrice(c, ui);

        return nameIsValid && priceIsValid;
    }

    public boolean isValidName(Command c, Ui ui) {
        if (c.getArgumentMap().get("n").length() > 25) {
            ui.println(ui.ITEM_NAME_MAX_LENGTH_ERROR);
            return false;
        }

        if (c.getArgumentMap().get("n").length() < 1) {
            ui.println(ui.ITEM_NAME_MIN_LENGTH_ERROR);
            return false;
        }

        return true;
    }

    public boolean isValidPrice(Command c, Ui ui) {
        String price = c.getArgumentMap().get("p");
        price = price.trim();

        if (price.length() < 1) {
            ui.println(ui.ITEM_PRICE_MIN_LENGTH_ERROR);
            return false;
        }

        double tempPrice;

        if (super.isDouble(price)) {
            tempPrice = Double.parseDouble(price);
        } else {
            return false;
        }

        if (tempPrice < 0.00) {
            ui.println(ui.ITEM_PRICE_NEGATIVE_ERROR);
            return false;
        }

        int numOfDecimalPoint = super.getNumOfDecimalPoints(price);

        if (numOfDecimalPoint > 2) {
            ui.println(ui.PRICE_DECIMAL_ERROR);
            return false;
        }

        return true;
    }

}
