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
    Ui ui = new Ui ();

    public boolean isValidArgument (Command arg) {
        if(isArgumentPresent (arg)){
            if (!(arg.getArgumentString ().contains ("-d") || arg.getArgumentString ().contains ("--done"))
                    || !(arg.getArgumentString ().contains ("-i") || arg.getArgumentString ().contains ("--item"))) {
                ui.println (ui.MISSING_ORDER_ARGUMENT);
                return false;
            } else {
                if(isValidFlagArgument (arg)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isInteger (String input) {
        try {
            Integer.parseInt (input);
        } catch (NumberFormatException n) {
            ui.println (ui.INTEGER_ERROR);
            return false;
        }
        return true;
    }

    public boolean isValidFlagArgument (Command arg) {
        boolean isValidQuantity = true;
        boolean isValidItem;
        if (arg.getArgumentString ().contains ("-q") || arg.getArgumentString ().contains ("--quantity")) {
            isValidQuantity = isInteger (arg.getArgumentMap ().get ("q"));
        }
        isValidItem = isInteger (arg.getArgumentMap ().get ("i"));
        return isValidQuantity && isValidItem;
    }


    public boolean isArgumentPresent (Command arg) {
        if (arg.getCommand ().contains ("addorder") && arg.getArgumentString () != null) {
            return true;
        }
        return false;
    }
}
