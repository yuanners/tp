package validation.item;

import app.Command;
import exception.item.*;
import item.Menu;
import utility.Ui;

public class AddItemValidation extends ItemValidation {

    private Ui ui = new Ui();

    /**
     * Checks if the required flag is given
     *
     * @param c Given command
     * @throws ItemException If any required flag is not given
     */
    public void validateFlags(Command c) throws
            MissingNameFlagException, MissingPriceFlagException, MissingNameAndPriceFlag {
        String args = c.getArgumentString();

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
     * Checks if the given input for name is valid
     *
     * @param c Given command
     * @throws ItemException If name is invalid
     */
    public void validateName(Command c) throws NameMinimumLengthException, NameMaximumLengthException {
        // TODO : Refactor callers
        if(c.getArgumentMap().get(LONG_NAME_FLAG) == null) {
            throw new NameMinimumLengthException();
        }

        if (c.getArgumentMap().get(LONG_NAME_FLAG).length() > 25) {
            throw new NameMaximumLengthException();
        }

        if (c.getArgumentMap().get(LONG_NAME_FLAG).length() < 1) {
            throw new NameMinimumLengthException();
        }

    }

    /**
     * Checks if the given input for name exists in the menu
     *
     * @param c Given command
     * @param menu The list of items on the menu
     * @throws ItemException If name is invalid
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
     * @throws ItemException If price is invalid
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
