package validation;

import exception.InvalidArgumentException;
import item.Menu;
import utility.Ui;
import app.Command;

public class Validation {
    private Ui ui = new Ui();

    public Validation() {
    }

    /**
     * Handles the common input validators
     */
    public void validateArgument(Command arg) throws InvalidArgumentException {
        if (arg.getUserInput() == null) {
            throw new InvalidArgumentException(ui.NULL_MESSAGE);
        } else if (arg.getUserInput().contains(":") || arg.getUserInput().contains(";")) {
            throw new InvalidArgumentException(ui.ERROR_MESSAGE);
        }
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
            ui.printRequiresInteger();
            return false;
        }
        return true;
    }

    /**
     * Check if index provided is valid
     *
     * @param index Given index
     * @param menu  List of Items
     * @return validation outcome (true/false)
     */
    public boolean isValidIndex(String index, Menu menu) {
        try {
            menu.getItem(Integer.parseInt(index));
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidIndex();
            return false;
        }
        return true;
    }
}
