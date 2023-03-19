package validation.item;

import app.Command;
import exception.InvalidArgumentException;
import exception.ItemException;
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
    public void validateFlags(Command c) throws ItemException{
        String args = c.getArgumentString();

        if (!(args.contains(SHORT_NAME_FLAG) || args.contains(LONG_NAME_FLAG))) {
            throw new ItemException(ui.printInvalidFlags(c.getCommand()));
        }

        if (!(args.contains(SHORT_PRICE_FLAG) || args.contains(LONG_PRICE_FLAG))) {
            throw new ItemException(ui.printInvalidFlags(c.getCommand()));
        }
    }

    /**
     * Calls all validation methods to check all parts of the given command
     *
     * @param c Given command
     * @param menu The list of items on the menu
     * @throws ItemException If any validation fails
     */
    public void validateCommand(Command c, Menu menu) throws ItemException {
        try {
            validateArgument(c);
            validateName(c);
            validateDuplicateName(c, menu);
            validatePrice(c);
        } catch (ItemException e) {
            throw new ItemException(e.getMessage());
        } catch (InvalidArgumentException e) {
            throw new ItemException(e.getMessage());
        }
    }

    /**
     * Checks if the given input for name is valid
     *
     * @param c Given command
     * @throws ItemException If name is invalid
     */
    public void validateName(Command c) throws ItemException {
        if(c.getArgumentMap().get(LONG_NAME_FLAG) == null) {
            throw new ItemException(ui.getItemNameMinLengthError());
        }

        if (c.getArgumentMap().get(LONG_NAME_FLAG).length() > 25) {
            throw new ItemException(ui.getItemNameMaxLengthError());
        }

        if (c.getArgumentMap().get(LONG_NAME_FLAG).length() < 1) {
            throw new ItemException(ui.getItemNameMinLengthError());
        }

    }

    /**
     * Checks if the given input for name exists in the menu
     *
     * @param c Given command
     * @param menu The list of items on the menu
     * @throws ItemException If name is invalid
     */
    public void validateDuplicateName(Command c, Menu menu) throws ItemException {
        String newItemName = c.getArgumentMap().get(LONG_NAME_FLAG);
        int menuSize = menu.getItems().size();
        for(int i = 0; i<menuSize; i++) {
            if(newItemName.toLowerCase().equals(menu.getItem(i).getName().toLowerCase())) {
                throw new ItemException(ui.getItemDuplicateNameError());
            }
        }

    }

    /**
     * Checks if the given input for price is valid
     *
     * @param c Given command
     * @throws ItemException If price is invalid
     */
    public void validatePrice(Command c) throws ItemException {
        if(c.getArgumentMap().get(LONG_PRICE_FLAG) == null) {
            throw new ItemException(ui.getItemPriceMinLengthError());
        }

        String price = c.getArgumentMap().get(LONG_PRICE_FLAG);
        price = price.trim();

        if (price.length() < 1) {
            throw new ItemException(ui.getItemPriceMinLengthError());
        }

        if (!super.isDouble(price)) {
            throw new ItemException(ui.getInvalidPriceError());
        }

        double tempPrice = Double.parseDouble(price);

        if (tempPrice > Double.MAX_VALUE) {
            throw new ItemException(ui.getDoubleOverflow());
        }

        if (tempPrice < 0.00) {
            throw new ItemException(ui.getItemPriceNegativeError());
        }

        if(!price.contains(".")) { return; }

        int numOfDecimalPoint = price.length() - price.indexOf('.') - 1;

        if (numOfDecimalPoint > 2) {
            throw new ItemException(ui.getPriceDecimalError());
        }

    }

}
