package validation;

import app.Command;
import utility.Ui;
/* for add order
-i or --item for item index
-d ot --done for end of input
-q or --quantity for quantity(optional)
example:
addorder -i 1 -q 2, -i 3, -i 4 iq 3 -d
*/

public class orderValidation extends validation {
    @Override
    public void validateArgument (Command arg) throws invalidArgumentException {
        super.validateArgument (arg);
        if (!(arg.getArgumentString ().contains ("-d"))) {

        }
    }

    public void validateIntArgument (Command arg) throws invalidArgumentException {
        try {
            Integer.parseInt (arg.getArgumentString ());
        } catch (NumberFormatException n) {
            //a constant error message to print eg Please enter item index to add order
            throw new invalidArgumentException (Ui.INT_ERROR_MESSAGE);
        }
    }

    public void validateCommand (Command arg) throws invalidCommandException {
        if (!(arg.getCommand ().contains ("addorder"))) {
            //a constant error message to print eg Please use addorder to add order
            throw new invalidArgumentException(Ui.ERROR_MESSAGE);
        }
    }
}
