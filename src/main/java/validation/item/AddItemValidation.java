package validation.item;

import app.Command;
import exception.InvalidArgumentException;
import exception.ItemException;
import item.Menu;
import utility.Ui;

public class AddItemValidation extends ItemValidation {
    public final String SHORT_NAME_FLAG = "n";
    public final String LONG_NAME_FLAG = "name";
    public final String SHORT_PRICE_FLAG = "p";
    public final String LONG_PRICE_FLAG = "price";
    private Ui ui = new Ui();

    /**
     * Checks if the required flag is given
     *
     * @param c Given command
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

    public void validateCommand(Command c, Menu items) throws ItemException {
        try {
            validateArgument(c);
            validateName(c, items);
            validatePrice(c);
        } catch (ItemException e) {
            throw new ItemException(e.getMessage());
        } catch (InvalidArgumentException e) {
            throw new ItemException(e.getMessage());
        }
    }

    public void validateName(Command c, Menu items) throws ItemException {
        if (c.getArgumentMap().get(LONG_NAME_FLAG).length() > 25) {
            throw new ItemException(ui.ITEM_NAME_MAX_LENGTH_ERROR);
        }

        if (c.getArgumentMap().get(LONG_NAME_FLAG).length() < 1) {
            throw new ItemException(ui.ITEM_NAME_MIN_LENGTH_ERROR);
        }

        String newItemName = c.getArgumentMap().get(LONG_NAME_FLAG);
        int menuSize = items.getItems().size();
        for(int i = 0; i<menuSize; i++) {
            if(newItemName.toLowerCase().equals(items.getItem(i).getName().toLowerCase())) {
               throw new ItemException(ui.ITEM_DUPLICATE_NAME_ERROR);
            }
        }

    }

    /**
     * Checks if the given input for price is valid
     *
     * @param c Given command
     */
    public void validatePrice(Command c) throws ItemException {
        String price = c.getArgumentMap().get(LONG_PRICE_FLAG);
        price = price.trim();

        if (price.length() < 1) {
            throw new ItemException(ui.ITEM_PRICE_MIN_LENGTH_ERROR);
        }

        if (!super.isDouble(price)) {
            throw new ItemException(ui.INVALID_PRICE_ERROR);
        }

        double tempPrice = Double.parseDouble(price);

        if (tempPrice > Double.MAX_VALUE) {
            throw new ItemException(ui.DOUBLE_OVERFLOW);
        }

        if (tempPrice < 0.00) {
            throw new ItemException(ui.ITEM_PRICE_NEGATIVE_ERROR);
        }

        int numOfDecimalPoint = price.length() - price.indexOf('.') - 1;

        if (numOfDecimalPoint > 2) {
            throw new ItemException(ui.PRICE_DECIMAL_ERROR);
        }

    }

}
