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
     *
     * @param arg The given command
     * @throws InvalidArgumentException
     */
    public void validateArgument(Command arg) throws InvalidArgumentException {
        if (arg.getUserInput() == null) {
            throw new InvalidArgumentException(ui.getNullMessage());
        }
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
            return false;
        }
        return true;
    }

}
