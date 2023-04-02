package validation.item;

import app.Command;
import exception.InvalidArgumentException;
import exception.item.MissingNameFlagException;
import exception.item.MissingNameAndPriceFlag;
import exception.item.MissingPriceFlagException;
import exception.item.NameMinimumLengthException;
import exception.item.NameMaximumLengthException;
import exception.item.DuplicateNameException;
import exception.item.PriceMinimumLengthException;
import exception.item.PriceInvalidNumberException;
import exception.item.PriceOverflowException;
import exception.item.PriceNegativeException;
import exception.item.PriceInvalidDecimalPlaceException;
import exception.item.NameIsIntegerException;
import item.Menu;
import ui.Flags;
import ui.MenuUi;

public class AddItemValidation extends ItemValidation {

    private MenuUi menuUi;

    public AddItemValidation() {
        menuUi = new MenuUi();
    }

    public boolean validateFlags(Command command) {
        boolean isValid = false;
        try {
            checkFlags(command);
            isValid = true;
        } catch (MissingNameAndPriceFlag e) {
            menuUi.printError(Flags.Error.MISSING_ITEM_NAME_AND_PRICE_FLAG);
        } catch (MissingNameFlagException e) {
            menuUi.printError(Flags.Error.MISSING_ITEM_NAME_FLAG);
        } catch (MissingPriceFlagException e) {
            menuUi.printError(Flags.Error.MISSING_ITEM_PRICE_FLAG);
        }

        return isValid;
    }

    /**
     * Checks if the required flag is given
     *
     * @param command Given command
     */
    private void checkFlags(Command command) throws
            MissingNameFlagException, MissingPriceFlagException, MissingNameAndPriceFlag {
        String args = command.getArgumentString();

        if(!args.contains(SHORT_NAME_FLAG) && !args.contains(LONG_NAME_FLAG)
                && !args.contains(SHORT_PRICE_FLAG) && !args.contains(LONG_PRICE_FLAG)) {
            throw new MissingNameAndPriceFlag();
        }

        if (!(args.contains(SHORT_NAME_FLAG) || args.contains(LONG_NAME_FLAG))) {
            throw new MissingNameFlagException();
        }

        if (!(args.contains(SHORT_PRICE_FLAG) || args.contains(LONG_PRICE_FLAG))) {
            throw new MissingPriceFlagException();
        }
    }

    /**
     * Calls all validation methods to check all parts of the given command
     *
     * @param c Given command
     * @param menu The list of items on the menu
     */
    public boolean validateCommand(Command c, Menu menu) {
        boolean isValid = false;

        try {
            validateArgument(c);
            validateName(c);
            validateDuplicateName(c, menu);
            validatePrice(c);
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
        } catch (NameIsIntegerException e) {
            menuUi.printError(Flags.Error.ITEM_NAME_IS_INTEGER_ERROR);
        }

        return isValid;
    }

    /**
     * Checks if the given input for name is valid
     *
     * @param c Given command
     */
    public void validateName(Command c) throws NameMinimumLengthException, NameMaximumLengthException,
            NameIsIntegerException {

        if(c.getArgumentMap().get(LONG_NAME_FLAG) == null) {
            throw new NameMinimumLengthException();
        }

        int result = isInteger(c.getArgumentMap().get(LONG_NAME_FLAG));

        if(result == 0) {
            throw new NameIsIntegerException();
        }

        if(isDouble(c.getArgumentMap().get(LONG_NAME_FLAG))) {
            throw new NameIsIntegerException();
        }

        if (c.getArgumentMap().get(LONG_NAME_FLAG).length() > 25) {
            throw new NameMaximumLengthException();
        }

        if (c.getArgumentMap().get(LONG_NAME_FLAG).trim().length() < 1) {
            throw new NameMinimumLengthException();
        }

    }

    /**
     * Checks if the given input for name exists in the menu
     *
     * @param c Given command
     * @param menu The list of items on the menu
     */
    public void validateDuplicateName(Command c, Menu menu) throws DuplicateNameException {
        String newItemName = c.getArgumentMap().get(LONG_NAME_FLAG);
        int menuSize = menu.getItems().size();
        for(int i = 0; i<menuSize; i++) {
            if(newItemName.toLowerCase().equals(menu.getItem(i).getName().toLowerCase())) {
                throw new DuplicateNameException();
            }
        }

    }

    /**
     * Checks if the given input for price is valid
     *
     * @param c Given command
     */
    public void validatePrice(Command c) throws
            PriceMinimumLengthException, PriceOverflowException, PriceInvalidNumberException,
            PriceNegativeException, PriceInvalidDecimalPlaceException {
        if(c.getArgumentMap().get(LONG_PRICE_FLAG) == null) {
            throw new PriceMinimumLengthException();
        }

        String price = c.getArgumentMap().get(LONG_PRICE_FLAG);
        price = price.trim();

        if (price.length() < 1) {
            throw new PriceMinimumLengthException();
        }

        if (!super.isDouble(price)) {
            throw new PriceInvalidNumberException();
        }

        double tempPrice = Double.parseDouble(price);

        if (tempPrice > Double.MAX_VALUE) {
            throw new PriceOverflowException();
        }

        if (tempPrice < 0.00) {
            throw new PriceNegativeException();
        }

        if(!price.contains(".")) { return; }

        int numOfDecimalPoint = price.length() - price.indexOf('.') - 1;

        if (numOfDecimalPoint > 2) {
            throw new PriceInvalidDecimalPlaceException();
        }

    }

}
