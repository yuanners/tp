package validation.item;

import app.Command;
import exception.ItemException;

public class FindItemValidation extends ItemValidation {

    public void validateFlags(Command c) throws ItemException {
        String args = c.getArgumentString();

        if (!(args.contains(SHORT_DESC_FLAG) || args.contains(LONG_DESC_FLAG))) {
            throw new ItemException(ui.printInvalidFlags(c.getCommand()));
        }
    }

    public void validateName(Command c) throws ItemException {
        if(c.getArgumentMap().get(LONG_DESC_FLAG) == null) {
            throw new ItemException(ui.getItemNameMinLengthError());
        }

        if (c.getArgumentMap().get(LONG_DESC_FLAG).length() < 1) {
            throw new ItemException(ui.getItemNameMinLengthError());
        }
    }

}
