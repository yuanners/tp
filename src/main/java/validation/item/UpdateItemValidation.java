package validation.item;

import app.Command;
import exception.item.NameMinimumLengthException;
import exception.item.NameMaximumLengthException;
import exception.item.DuplicateNameException;
import exception.item.PriceMinimumLengthException;
import exception.item.PriceInvalidNumberException;
import exception.item.PriceOverflowException;
import exception.item.PriceNegativeException;
import exception.item.PriceInvalidDecimalPlaceException;
import exception.item.NameIsIntegerException;
import exception.item.IndexInvalidNumberFormatException;
import exception.item.IndexOverflowException;
import exception.item.IndexOutOfBoundException;
import exception.item.MissingIndexFlagException;
import exception.item.MissingNameOrPriceFlagException;
import exception.InvalidArgumentException;
import ui.Flags;
import ui.MenuUi;
import item.Menu;

public class UpdateItemValidation extends ItemValidation {
    private MenuUi menuUi;

    public UpdateItemValidation() {
        menuUi = new MenuUi();
    }

    public boolean validateFlags(Command command) {
        boolean isValid = false;
        try {
            checkFlags(command);
            isValid = true;
        } catch (MissingIndexFlagException e) {
            menuUi.printError(Flags.Error.MISSING_ITEM_INDEX_FLAG);
        } catch (MissingNameOrPriceFlagException e) {
            menuUi.printError(Flags.Error.MISSING_ITEM_NAME_OR_PRICE_FLAG);
        }

        return isValid;
    }

    /**
     * Checks if the required flag is given
     *
     * @param c Given command
     * @throws MissingIndexFlagException If Index is missing
     * @throws MissingNameOrPriceFlagException If Name or Price is missing
     */
    private void checkFlags(Command c) throws
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

    /**
     * Calls all validation methods to check all parts of the given command
     *
     * @param command Given command
     * @param menu The list of items on the menu
     */
    public boolean validateCommand(Command command, Menu menu) {
        AddItemValidation addItemValidation = new AddItemValidation();
        DeleteItemValidation deleteItemValidation = new DeleteItemValidation();
        boolean isValid = false;
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

            isValid = true;
        } catch (InvalidArgumentException e) {
            menuUi.printError(Flags.Error.EMPTY_INPUT);
        } catch (NameMinimumLengthException e) {
            menuUi.printError(Flags.Error.ITEM_NAME_MIN_LENGTH_ERROR);
        } catch (NameMaximumLengthException e) {
            menuUi.printError(Flags.Error.ITEM_NAME_MAX_LENGTH_ERROR);
        } catch (DuplicateNameException e) {
            menuUi.printError(Flags.Error.ITEM_DUPLICATE_NAME_ERROR);
        } catch (PriceMinimumLengthException e) {
            menuUi.printError(Flags.Error.ITEM_PRICE_MIN_LENGTH_ERROR);
        } catch (PriceInvalidNumberException e) {
            menuUi.printError(Flags.Error.ITEM_PRICE_INVALID_FORMAT_ERROR);
        } catch (PriceOverflowException e) {
            menuUi.printError(Flags.Error.ITEM_PRICE_OVERFLOW_ERROR);
        } catch (PriceNegativeException e) {
            menuUi.printError(Flags.Error.ITEM_PRICE_NEGATIVE_ERROR);
        } catch (PriceInvalidDecimalPlaceException e) {
            menuUi.printError(Flags.Error.ITEM_PRICE_INVALID_DECIMAL_PLACE_ERROR);
        } catch (IndexInvalidNumberFormatException e) {
            menuUi.printError(Flags.Error.ITEM_INDEX_INVALID_FORMAT_ERROR);
        } catch (IndexOverflowException e) {
            menuUi.printError(Flags.Error.ITEM_INDEX_OVERFLOW_ERROR);
        } catch (IndexOutOfBoundException e) {
            menuUi.printError(Flags.Error.ITEM_INDEX_OUT_OF_BOUND_ERROR);
        } catch (NameIsIntegerException e) {
            menuUi.printError(Flags.Error.ITEM_NAME_IS_INTEGER_ERROR);
        }

        return isValid;
    }

}
