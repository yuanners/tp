package validation.item;

import app.Command;
import exception.ItemException;

public class FindItemValidation extends ItemValidation {
    public final String SHORT_NAME_FLAG = "d";
    public final String LONG_NAME_FLAG = "description";

    public void validateFlags(Command c) throws ItemException {
        String args = c.getArgumentString();

        if (!(args.contains(SHORT_NAME_FLAG) || args.contains(LONG_NAME_FLAG))) {
            throw new ItemException(ui.printInvalidFlags(c.getCommand()));
        }
    }

    public void validateName(Command c) throws ItemException {
        if(c.getArgumentMap().get(LONG_NAME_FLAG) == null) {
            throw new ItemException(ui.getItemNameMinLengthError());
        }

        if (c.getArgumentMap().get(LONG_NAME_FLAG).length() < 1) {
            throw new ItemException(ui.getItemNameMinLengthError());
        }
    }

}
