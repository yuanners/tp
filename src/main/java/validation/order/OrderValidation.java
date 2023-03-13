package validation.order;

import app.Command;
import exception.InvalidArgumentException;
import utility.Ui;
import validation.Validation;

/**
 * Handles order related input validation
 */
public class OrderValidation extends Validation {
    Ui ui = new Ui();

    /**
     * Call override validation method to check common validations
     *
     * @param arg user input
     * @return validation result (true/false)
     */
    public boolean isValid(Command arg) {
        try {
            validateArgument(arg);
        } catch (InvalidArgumentException e) {
            ui.println(e.getMessage());
            return false;
        }

        return isValidFlagArgument(arg);
    }

    /**
     * Check if the argument contains the required flags for addorder
     *
     * @param arg user input
     * @return validation outcome (true/false)
     */
    public boolean isValidFlagArgument(Command arg) {
        boolean isValidQuantity = true;
        boolean isValidItem;
        if (arg.getArgumentString().contains("-q") || arg.getArgumentString().contains("--quantity")) {
            isValidQuantity = isInteger(arg.getArgumentMap().get("q").trim());
        }
        isValidItem = isInteger(arg.getArgumentMap().get("i").trim());
        return isValidQuantity && isValidItem;
    }

    /**
     * Check if there is argument after the addorder command
     *
     * @param arg user input
     * @return validation outcome
     */
    public boolean isArgumentPresent(Command arg) {
        if (arg.getCommand().contains("addorder") && arg.getArgumentString() != null) {
            return true;
        }
        ui.println(ui.NULL_MESSAGE);
        return false;
    }
}
