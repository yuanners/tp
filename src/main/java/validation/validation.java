package validation;

import utility.Ui;
import app.Command;

public class validation {
    public validation () {

    }

    //handles the common input validators
    public void validateArgument (Command arg) throws invalidArgumentException {
        if (arg.getUserInput () == null) {
            throw new invalidArgumentException (Ui.NULL_MESSAGE);
        } else if (arg.getUserInput ().contains (":") || arg.getUserInput ().contains (";")) {
            throw new invalidArgumentException (Ui.ERROR_MESSAGE);
        }
    }

    public void validateInteger (String input) throws invalidArgumentException {
        try {
            Integer.parseInt (input);
        } catch (NumberFormatException n) {
            throw new invalidArgumentException (Ui.INTEGER_ERROR);
        }
    }
}
