package validation.item;

import app.Command;
import exception.InvalidArgumentException;
import exception.ItemException;
import item.Menu;

public class DeleteItemValidation extends ItemValidation {
    public final String SHORT_INDEX_FLAG = "i";
    public final String LONG_INDEX_FLAG = "index";

    /**
     * Checks if the required flag is given
     *
     * @param c Given command
     * @return Validation result (true/false)
     */
    public void validateFlags(Command c) throws ItemException {
        String args = c.getArgumentString();

        if (!(args.contains(SHORT_INDEX_FLAG) || args.contains(LONG_INDEX_FLAG))) {
            throw new ItemException(ui.printInvalidFlags(c.getCommand()));
        }
    }

    public void validateCommand(Command c, Menu items) throws ItemException {
        try {
            validateArgument(c);
            validateIndex(c, items);
        } catch (ItemException e) {
            throw new ItemException(e.getMessage());
        } catch (InvalidArgumentException e) {
            throw new ItemException(e.getMessage());
        }
    }

    public void validateIndex(Command c, Menu items) throws ItemException {
        int result = isInteger(c.getArgumentMap().get(LONG_INDEX_FLAG));
        if(result == 1) {
            throw new ItemException(ui.REQUIRE_INTEGER);
        }
        if(result == 2) {
            throw new ItemException(ui.INTEGER_OVERFLOW);
        }
        if(!isValidIndex(c.getArgumentMap().get(LONG_INDEX_FLAG), items)) {
            throw new ItemException(ui.INVALID_INDEX);
        }
    }
}
