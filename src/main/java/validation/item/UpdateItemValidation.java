package validation.item;

import app.Command;
import exception.InvalidArgumentException;
import exception.item.ItemException;
import item.Menu;

public class UpdateItemValidation extends ItemValidation {

    /**
     * Checks if the required flag is given
     *
     * @param c Given command
     * @throws ItemException If any required flag is not given
     */
    public void validateFlags(Command c) throws ItemException{
        String args = c.getArgumentString();

        if (!(args.contains(SHORT_INDEX_FLAG) || args.contains(LONG_INDEX_FLAG))) {
            throw new ItemException(ui.printInvalidFlags(c.getCommand()));
        }

        if(!(args.contains(SHORT_NAME_FLAG) || args.contains(LONG_NAME_FLAG) ||
                args.contains(SHORT_PRICE_FLAG) || args.contains(LONG_PRICE_FLAG))) {
            throw new ItemException(ui.printInvalidFlags(c.getCommand()));
        }
    }

    /**
     * Calls all validation methods to check all parts of the given command
     *
     * @param command Given command
     * @param menu The list of items on the menu
     * @throws ItemException If any validation fails
     */
    public void validateCommand(Command command, Menu menu) throws ItemException {
        AddItemValidation addItemValidation = new AddItemValidation();
        DeleteItemValidation deleteItemValidation = new DeleteItemValidation();
        try {
            validateArgument(command);
            deleteItemValidation.validateIndex(command, menu);

            int indexOfItem = Integer.parseInt(command.getArgumentMap().get(deleteItemValidation.LONG_INDEX_FLAG));
            if(command.getArgumentMap().containsKey(LONG_NAME_FLAG)) {
                addItemValidation.validateName(command);

                String newName = command.getArgumentMap().get(LONG_NAME_FLAG).toLowerCase();
                String currentName = menu.getItem(indexOfItem).getName().toLowerCase();
                if(!newName.equals(currentName)) {
                    addItemValidation.validateDuplicateName(command, menu);
                }
            }

            if(command.getArgumentMap().containsKey(LONG_PRICE_FLAG)) {
                addItemValidation.validatePrice(command);
            }
        } catch (ItemException e) {
            throw new ItemException(e.getMessage());
        } catch (InvalidArgumentException e) {
            throw new ItemException(e.getMessage());
        }
    }

}
