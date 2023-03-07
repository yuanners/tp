package validation;


import app.Command;

public class validation {
    public validation () {

    }

    //handles the common input validators
    public void validateArgument (Command arg) throws invalidArgumentException {
        if (arg.getUserInput () == null) {
            throw new invalidArgumentException (UI.NULL_ERROR);
        } else if (arg.getUserInput ().contains (":") || arg.getUserInput ().contains (";")) {
            throw new invalidArgumentException (UI.ERROR_MESSAGE);
        }
    }

    public void validateInteger (String input) throws invalidArgumentException {
        try {
            Integer.parseInt (input);
        } catch (NumberFormatException n) {
            throw new invalidArgumentException (UI.INTEGER_ERROR);
        }
    }
}
