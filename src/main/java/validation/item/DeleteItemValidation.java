package validation.item;

import app.Command;
import exception.InvalidArgumentException;
import exception.item.MissingIndexFlagException;
import exception.item.IndexInvalidNumberFormatException;
import exception.item.IndexOverflowException;
import exception.item.IndexOutOfBoundException;
import item.Menu;
import ui.Flags;
import ui.MenuUi;

public class DeleteItemValidation extends ItemValidation {
    private MenuUi menuUi;

    public DeleteItemValidation() {
        menuUi = new MenuUi();
    }

    public boolean validateFlags(Command command) {
        boolean isValid = false;
        try {
            checkFlags(command);
            isValid = true;
        } catch (MissingIndexFlagException e) {
            menuUi.printError(Flags.Error.MISSING_ITEM_INDEX_FLAG);
        }

        return isValid;
    }

    /**
     * Checks if the required flag is given
     *
     * @param c Given command
     * @throws MissingIndexFlagException If any required flag is not given
     */
    private void checkFlags(Command c) throws MissingIndexFlagException {
        String args = c.getArgumentString();

        if (!(args.contains(SHORT_INDEX_FLAG) || args.contains(LONG_INDEX_FLAG))) {
            throw new MissingIndexFlagException();
        }
    }

    /**
     * Calls all validation methods to check all parts of the given command
     *
     * @param c Given command
     * @param menu The list of items on the menu
     * @throws ItemException If any validation fails
     */
    public boolean validateCommand(Command c, Menu menu) {
        boolean isValid = false;
        try {
            validateArgument(c);
            validateIndex(c, menu);
            isValid = true;
        } catch (IndexInvalidNumberFormatException e) {
            menuUi.printError(Flags.Error.ITEM_INDEX_INVALID_FORMAT_ERROR);
        } catch (IndexOverflowException e) {
            menuUi.printError(Flags.Error.ITEM_INDEX_OVERFLOW_ERROR);
        } catch (IndexOutOfBoundException e) {
            menuUi.printError(Flags.Error.ITEM_INDEX_OUT_OF_BOUND_ERROR);
        } catch (InvalidArgumentException e) {
            menuUi.printError(Flags.Error.EMPTY_INPUT);
        }

        return isValid;
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
