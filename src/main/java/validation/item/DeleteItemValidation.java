package validation.item;

import app.Command;
import exception.item.*;
import item.Menu;
import ui.MenuUi;

public class DeleteItemValidation extends ItemValidation {
    MenuUi menuUi;

    public DeleteItemValidation() {
        menuUi = new MenuUi();
    }

    /**
     * Checks if the required flag is given
     *
     * @param c Given command
     * @throws ItemException If any required flag is not given
     */
    public void validateFlags(Command c) throws MissingIndexFlagException {
        String args = c.getArgumentString();

        if (!(args.contains(SHORT_INDEX_FLAG) || args.contains(LONG_INDEX_FLAG))) {
            throw new MissingIndexFlagException();
        }
    }

    /**
     * Checks if the given input for index is valid
     *
     * @param c Given command
     * @param menu The list of items on the menu
     * @throws ItemException If index given is invalid
     */
    public void validateIndex(Command c, Menu menu) throws
            IndexInvalidNumberFormatException, IndexOverflowException, IndexOutOfBoundException {
        int result = isInteger(c.getArgumentMap().get(LONG_INDEX_FLAG));
        if(result == 1) {
            throw new IndexInvalidNumberFormatException();
        }
        if(result == 2) {
            throw new IndexOverflowException();
        }
        if(!isValidIndex(c.getArgumentMap().get(LONG_INDEX_FLAG), menu)) {
            throw new IndexOutOfBoundException();
        }
    }
}
