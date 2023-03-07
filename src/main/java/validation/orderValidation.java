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
        if (!(arg.getArgumentString ().contains ("-d") || arg.getArgumentString ().contains ("--done"))
                || !(arg.getArgumentString ().contains ("-i") || arg.getArgumentString ().contains ("--item"))) {
            throw new invalidArgumentException (Ui.MISSING_ORDER_ARGUMENT);
        }
    }

    public void validateFlagArgument (Command arg) throws invalidFlagException, invalidArgumentException {
        validateInteger (arg.getArgumentMap ().get ("i"));
        if (arg.getArgumentString ().contains ("-q") || arg.getArgumentString ().contains ("--quantity")) {
            validateInteger (arg.getArgumentMap ().get ("-q"));
        }

    }

    public void validateCommand (Command arg) throws invalidCommandException {
        if (arg.getCommand ().contains ("addorder") && arg.getArgumentString () == null) {
            throw new invalidCommandException (Ui.MISSING_ARGUMENT);
        }
    }
}
