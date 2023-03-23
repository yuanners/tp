package validation.item;

import app.Command;
import exception.InvalidArgumentException;
import exception.item.ItemException;
import item.Menu;

public class DeleteItemValidation extends ItemValidation {

    /**
     * Checks if the required flag is given
     *
     * @param c Given command
     * @throws ItemException If any required flag is not given
     */
    public void validateFlags(Command c) throws ItemException {
        String args = c.getArgumentString();

        if (!(args.contains(SHORT_INDEX_FLAG) || args.contains(LONG_INDEX_FLAG))) {
            throw new ItemException(ui.printInvalidFlags(c.getCommand()));
        }
    }

    /**
     * Calls all validation methods to check all parts of the given command
     *
     * @param c Given command
     * @param menu The list of items on the menu
     * @throws ItemException If any validation fails
     */
    public void validateCommand(Command c, Menu menu) throws ItemException {
        try {
            validateArgument(c);
            validateIndex(c, menu);
        } catch (ItemException e) {
            throw new ItemException(e.getMessage());
        } catch (InvalidArgumentException e) {
            throw new ItemException(e.getMessage());
        }
    }

    /**
     * Checks if the given input for index is valid
     *
     * @param c Given command
     * @param menu The list of items on the menu
     * @throws ItemException If index given is invalid
     */
    public void validateIndex(Command c, Menu menu) throws ItemException {
        int result = isInteger(c.getArgumentMap().get(LONG_INDEX_FLAG));
        if(result == 1) {
            throw new ItemException(ui.getRequireValidItemIndex());
        }
        if(result == 2) {
            throw new ItemException(ui.getIntegerOverflow());
        }
        if(!isValidIndex(c.getArgumentMap().get(LONG_INDEX_FLAG), menu)) {
            throw new ItemException(ui.getInvalidIndex());
        }
    }
}
