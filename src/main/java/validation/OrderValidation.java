package validation;

import app.Command;
import exception.InvalidArgumentException;
import utility.Ui;

/* for add order
-i or --item for item index
-d ot --done for end of input
-q or --quantity for quantity(optional)
example:
addorder -i 1 -q 2, -i 3, -i 4 iq 3 -d
*/

public class OrderValidation extends Validation {
    Ui ui = new Ui();

    @Override
    public void validateArgument(Command arg) throws InvalidArgumentException {

        try {
            super.validateArgument(arg);
        } catch (InvalidArgumentException e) {
            throw new InvalidArgumentException(Ui.ERROR_MESSAGE);
        }

    }

    public boolean isValidFormat(Command arg) {
        if (isArgumentPresent(arg)) {
            if (arg.getArgumentString().contains("-d") || arg.getArgumentString().contains("--done")
                    || arg.getArgumentString().contains("-i") || arg.getArgumentString().contains("--item")) {
                return true;
            } else {
                ui.println(ui.MISSING_ORDER_ARGUMENT);
                return false;
            }
        }
        return false;
    }


    public boolean isValid(Command arg) {
        try {
            validateArgument(arg);
        } catch (InvalidArgumentException e) {
            ui.println(e.getMessage());
            return false;
        }

        return isValidFlagArgument(arg);
    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException n) {
            ui.println(ui.INTEGER_ERROR);
            return false;
        }
        return true;
    }

    public boolean isValidFlagArgument(Command arg) {
        boolean isValidQuantity = true;
        boolean isValidItem;
        if (arg.getArgumentString().contains("-q") || arg.getArgumentString().contains("--quantity")) {
            isValidQuantity = isInteger(arg.getArgumentMap().get("q").trim());
        }
        isValidItem = isInteger(arg.getArgumentMap().get("i").trim());
        return isValidQuantity && isValidItem;
    }


    public boolean isArgumentPresent(Command arg) {
        if (arg.getCommand().contains("addorder") && arg.getArgumentString() != null) {
            return true;
        }
        return false;
    }
}
