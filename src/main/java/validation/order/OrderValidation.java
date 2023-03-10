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

    @Override

    public void validateArgument(Command arg) throws InvalidArgumentException {
        try {
            super.validateArgument(arg);
        } catch (InvalidArgumentException e) {
            throw new InvalidArgumentException(ui.ERROR_MESSAGE);
        }
    }

    /**
     * If argument is present, check if the required flags are present
     *
     * @param arg User command
     * @return validation result (true/false)
     */
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
     * Check if the input after items and quantity flags are integer
     *
     * @param input the input after flags
     * @return validation outcome (true/false)
     */
    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException n) {
            ui.println(ui.INTEGER_ERROR);
            return false;
        }
        return true;
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
