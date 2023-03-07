package validation;

import exception.InvalidArgumentException;
import utility.Ui;
import app.Command;

public class Validation {
    public Validation() {

    }

    //handles the common input validators
    public void validateArgument(Command arg) throws InvalidArgumentException {
        if (arg.getUserInput() == null) {
            throw new InvalidArgumentException(Ui.NULL_MESSAGE);
        } else if (arg.getUserInput().contains(":") || arg.getUserInput().contains(";")) {
            throw new InvalidArgumentException(Ui.ERROR_MESSAGE);
        }
    }
}
