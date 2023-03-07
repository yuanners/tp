package validation;

import app.Command;
import utility.Ui;

public class ItemValidation extends validation {

    public boolean isValidFormat(Command c) {

        Ui ui = new Ui();

        String args = c.getArgumentString();

        if(!(args.contains("n") || args.contains("name")) || !(args.contains("p") || args.contains("price"))) {
            ui.println(Ui.INVALID_ADDITEM_FORMAT);
            return false;
        }

        return true;
    }

    public boolean isValid(Command c) {
        Ui ui = new Ui();

        try {
            validateArgument(c);
        } catch (invalidArgumentException e) {
            ui.println(e.getMessage());
            return false;
        }

        boolean nameIsValid = validateName(c, ui);

        boolean priceIsValid = validatePrice(c, ui);

        return nameIsValid && priceIsValid;
    }

    @Override
    public void validateArgument (Command arg) throws invalidArgumentException {

        try {
            super.validateArgument (arg);
        } catch (invalidArgumentException e) {
            throw new invalidArgumentException(Ui.ERROR_MESSAGE);
        }

    }

    public boolean validateName(Command c, Ui ui) {
        if(c.getArgumentMap().get("n").length() > 25) {
            ui.println(Ui.ITEM_NAME_MAX_LENGTH_ERROR);
            return false;
        }

        if(c.getArgumentMap().get("n").length() <1) {
            ui.println(Ui.ITEM_NAME_MIN_LENGTH_ERROR);
            return false;
        }

        return true;
    }

    public boolean validatePrice (Command c, Ui ui) {
        String price = c.getArgumentMap().get("p");
        price = price.trim();

        if(price.length() < 1) {
            ui.println(Ui.ITEM_PRICE_MIN_LENGTH_ERROR);
            return false;
        }

        Double tempPrice;

        try {
            tempPrice = Double.valueOf(price);
        } catch (NumberFormatException e) {
            ui.println(Ui.INVALID_PRICE_ERROR);
            return false;
        }

        if(tempPrice < 0.00) {
            ui.println(Ui.ITEM_PRICE_NEGATIVE_ERROR);
            return false;
        }

        if(!price.contains(".")) {
            ui.println(Ui.PRICE_DECIMAL_ERROR);
            return false;
        }

        int indexOfDecimalPoint = price.indexOf(".");
        int numOfDecimalPoint = price.length() - indexOfDecimalPoint - 1;

        if(numOfDecimalPoint != 2) {
            ui.println(Ui.PRICE_DECIMAL_ERROR);
            return false;
        }

        return true;
    }

}
