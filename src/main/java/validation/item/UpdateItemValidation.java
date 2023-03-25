package validation.item;

import app.Command;
import exception.item.MissingIndexFlagException;
import exception.item.MissingNameOrPriceFlagException;
import ui.MenuUi;

public class UpdateItemValidation extends ItemValidation {
    MenuUi menuUi;

    public UpdateItemValidation() {
        menuUi = new MenuUi();
    }

    /**
     * Checks if the required flag is given
     *
     * @param c Given command
     * @throws MissingIndexFlagException If Index is missing
     * @throws MissingNameOrPriceFlagException If Name or Price is missing
     */
    public void validateFlags(Command c) throws
            MissingIndexFlagException, MissingNameOrPriceFlagException {
        String args = c.getArgumentString();

        if (!(args.contains(SHORT_INDEX_FLAG) || args.contains(LONG_INDEX_FLAG))) {
            throw new MissingIndexFlagException();
        }

        if(!(args.contains(SHORT_NAME_FLAG) || args.contains(LONG_NAME_FLAG) ||
                args.contains(SHORT_PRICE_FLAG) || args.contains(LONG_PRICE_FLAG))) {
            throw new MissingNameOrPriceFlagException();
        }
    }

}
