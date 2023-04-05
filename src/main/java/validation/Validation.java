package validation;

import exception.InvalidArgumentException;
import exception.UnrecognisedCommandException;
import item.Menu;
import app.Command;

public class Validation {


    /**
     * Handles the common input validators
     *
     * @param arg The given command
     * @throws InvalidArgumentException
     */
    public void validateArgument(Command arg) throws InvalidArgumentException {
        if (arg.getUserInput() == null) {
            throw new InvalidArgumentException();
        }
        assert arg.getUserInput() != null : "Null input should be handled";
    }

    /**
     * Check if index provided is valid
     *
     * @param index Given index
     * @param menu  List of Items
     * @return validation outcome (true/false)
     */
    public boolean isValidIndex(String index, Menu menu) {
        try {
            menu.getItem(Integer.parseInt(index));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    /**
     * Validates that there are no redundant arguments after assistant commands
     *
     * @param command The given command
     * @throws UnrecognisedCommandException
     */
    public void validateNoArgumentCommand(Command command) throws UnrecognisedCommandException {

        if(command.getArgumentString() == null) {
            return;
        }

        String argString = command.getArgumentString();

        if (!argString.trim().equals("")) {
            throw new UnrecognisedCommandException();
        }
        assert command.getArgumentString().trim().equals("") : "There should not be arguments after the commands";
    }

}
