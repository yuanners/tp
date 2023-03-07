package validation;

import app.Command;
import utility.Ui;

public class ItemValidation extends validation {

    public boolean isValidFormat(Command c) {

        Ui ui = new Ui();

        String args = c.getArgumentString();

        if(!(args.contains("n") || args.contains("name")) || !(args.contains("p") || args.contains("price"))) {
            ui.println("Invalid format.");
            return false;
        }

        return true;
    }

    public boolean isValid(Command c) {
        Ui ui = new Ui();

        try {
            validateArgument(c);
        } catch (invalidArgumentException e) {
            ui.println(e.getMessage());
            return false;
        }

        if(c.getArgumentMap().get("n").length() > 25) {
            ui.println("Name exceeds the 25 character limit.");
            return false;
        }

        String price = c.getArgumentMap().get("p");
        int indexOfDecimalPoint = price.indexOf(".");
        int numOfDecimalPoint = price.length() - indexOfDecimalPoint - 1;

        if(numOfDecimalPoint != 2) {
            return false;
        }

        return true;
    }

    @Override
    public void validateArgument (Command arg) throws invalidArgumentException {

        try {
            super.validateArgument (arg);
        } catch (invalidArgumentException e) {
            throw new invalidArgumentException("ERROR_MESSAGE");
        }

    }

    public void validateIntArgument (Command arg) throws invalidArgumentException {
        try {
            Integer.parseInt (arg.getArgumentString ());
        } catch (NumberFormatException n) {
            //a constant error message to print eg Please enter item index to add order
            throw new invalidArgumentException ("INT_ERROR_MESSAGE");
        }
    }

//    public void validateCommand (Command arg) throws invalidCommandException {
//        if (!(arg.getCommand ().contains ("additem"))) {
//            //a constant error message to print eg Please use addorder to add order
//            throw new invalidArgumentException ("ERROR_MESSAGE");
//        }
//    }
}
