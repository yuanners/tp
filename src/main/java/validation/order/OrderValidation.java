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
     * Catch all the thrown exception and display the error message accordingly
     *
     * @param arg user command
     * @throws InvalidArgumentException
     * @throws InvalidFlagException
     */
    public void checkValidFlagArgument(Command arg) throws InvalidArgumentException, InvalidFlagException {
        try {
            checkValidFlag(arg);
            checkArgumentPresent(arg);

            if(arg.getArgumentString().contains("-q") || arg.getArgumentString().contains("--quantity")) {
                if(!(isInteger(arg.getArgumentMap().get("q").trim()))) {
                    throw new InvalidArgumentException(ui.INVALID_QUANTITY_ARGUMENT);
                }
            }

            if(!(isInteger(arg.getArgumentMap().get("i").trim()))
                    || !(isInteger(arg.getArgumentMap().get("I").trim()))) {
                throw new InvalidArgumentException(ui.INVALID_INDEX_ARGUMENT);
            }
        } catch(InvalidArgumentException a) {
            throw new InvalidArgumentException(a.getMessage());
        } catch(InvalidFlagException f) {
            throw new InvalidFlagException(f.getMessage());
        }
    }

    /**
     * Check if the argument contains the required flags for addorder
     *
     * @param arg user input
     */
    public void checkValidFlag(Command arg) throws InvalidFlagException {

        if(arg.getArgumentString().contains("-i") || arg.getArgumentString().contains("--item")
                || arg.getArgumentString().contains("-I") || arg.getArgumentString().contains("--items")) {
        } else {
            throw new InvalidFlagException(ui.MISSING_ORDER_FLAG);
        }

    }

    /**
     * Check if there are argument present after the required flags
     *
     * @param arg user input
     * @throws InvalidArgumentException
     */
    public void checkArgumentPresent(Command arg) throws InvalidArgumentException {

        if(arg.getArgumentMap().get("i").trim() != null || arg.getArgumentMap().get("I").trim() != null) {
        } else {
            throw new InvalidArgumentException(ui.MISSING_ORDER_ARGUMENT);
        }

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
            ui.println(ui.INTEGER_ERROR);
        }

        return true;
    }


}
