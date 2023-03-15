package validation.order;

import app.Command;
import exception.InvalidArgumentException;
import exception.InvalidFlagException;
import utility.Ui;
import validation.Validation;

/**
 * Handles order related input validation
 */
public class OrderValidation extends Validation {
    Ui ui = new Ui();

    /**
     * Check if the argument contains the required flags for addorder
     *
     * @param arg user input
     * @return validation outcome (true/false)
     */
    public boolean isValidFlag(Command arg) throws InvalidFlagException {
        if(arg.getArgumentString().contains("-i") || arg.getArgumentString().contains("--item")
                || arg.getArgumentString().contains("-I") || arg.getArgumentString().contains("--items")) {
            return true;
        } else {
            ui.invalidOrderCommand();
            return false;
        }
    }

    /**
     * Check if there are argument present after the required flags
     *
     * @param arg user input
     * @return validation outcome (true/false)
     * @throws InvalidArgumentException
     */
    public boolean isArgumentPresent(Command arg) throws InvalidArgumentException {
        if(arg.getArgumentMap().get("i") != null || arg.getArgumentMap().get("I") != null) {
            return true;
        } else {
            ui.invalidOrderSyntax();
            return false;
        }
    }

    /**
     * Check if the argument after the required flags are integers
     *
     * @param arg user input
     * @return validation outcome (true/false)
     * @throws InvalidArgumentException
     * @throws InvalidFlagException
     */
    public boolean isValidFlagArgument(Command arg) throws InvalidFlagException, InvalidArgumentException {
        boolean isValidQuantity = true;
        boolean isValidItem = false;
        if(isValidFlag(arg) && isArgumentPresent(arg)) {
            if(arg.getArgumentString().contains("-q") || arg.getArgumentString().contains("--quantity")) {
                isValidQuantity = isInteger(arg.getArgumentMap().get("q").trim());
            }
            if(arg.getArgumentMap().get("i").trim() != null) {
                isValidItem = isInteger(arg.getArgumentMap().get("i").trim());
            } else {
                isValidItem = isInteger(arg.getArgumentMap().get("I").trim());
            }
        }
        return isValidQuantity && isValidItem;
    }

    /**
     * Check if the input is integer
     *
     * @param input the user input after flags
     * @return validation outcome (true/false)
     */
    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch(NumberFormatException n) {
            ui.invalidIndex();
            return false;
        }
        return true;
    }


}
