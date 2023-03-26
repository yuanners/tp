package validation.order;

import app.Command;
import exception.order.MissingOrderFlagException;
import exception.order.MissingOrderArgumentException;
import exception.order.MissingQuantityArgumentException;
import exception.order.InvalidIndexNumberFormatException;
import exception.order.InvalidIndexNegativeException;
import exception.order.InvalidIndexOutOfBoundsException;
import exception.order.InvalidQuantityNumberFormatException;
import exception.order.InvalidQuantityNegativeException;
import item.Menu;
import ui.Flags;
import ui.TransactionUi;
import utility.Ui;
import validation.Validation;

/**
 * Handles order related input validation
 */
public class AddOrderValidation extends Validation {
    private Ui ui = new Ui();
    private Menu menu = new Menu();
    private TransactionUi transactionUi = new TransactionUi();


    //    public void checkValidItem(Command arg) throws ItemException {
    //
    //        String item;
    //
    //        if (arg.getArgumentMap().get("i") != null) {
    //            item = arg.getArgumentMap().get("i");
    //        } else {
    //            item = arg.getArgumentMap().get("item");
    //        }
    //
    //        if (!isInteger(item)) {
    //            if (menu.findMatchingItemNames(item).size() > 1) {
    //                throw new ItemException(ui.getMultipleSimilarItemsFound());
    //            } else if (menu.findMatchingItemNames(item).size() == 0) {
    //                throw new ItemException(ui.getNoSuchItem());
    //            }
    //        }
    //
    //    }

    public boolean checkValidItemName(String itemName) {

        if (!isInteger(itemName)) {
            if (menu.findMatchingItemNames(itemName).size() > 1) {
                transactionUi.printError(Flags.Error.MULTIPLE_SIMILAR_ITEMS);
            } else if (menu.findMatchingItemNames(itemName).size() == 0) {
                transactionUi.printError(Flags.Error.NO_SUCH_ITEM);
                return false;
            }
        }

        return true;
    }

    public boolean checkValidQuantity(String quantity) {
        if (!isInteger(quantity)) {
            transactionUi.printError(Flags.Error.INVALID_QUANTITY_FORMAT);
            return false;
        }

        if (Integer.parseInt(quantity) < 0) {
            transactionUi.printError(Flags.Error.INVALID_NEGATIVE_QUANTITY);
        }

        return true;
    }

    //    /**
    //     * Validate the item index and quantity
    //     *
    //     * @param arg  user command
    //     * @param menu itemlist
    //     * @throws OrderException custom exception for order validation
    //     */
    //    public Command validateAddOrder(Command arg, Menu menu) throws OrderException {
    //
    //        String item = "";
    //        String newItem = "";
    //
    //        if (arg.getArgumentString().contains("-i")) {
    //            item = arg.getArgumentMap().get("i").trim();
    //        } else if (arg.getArgumentString().contains("--item")) {
    //            item = arg.getArgumentMap().get("item").trim();
    //        }
    //
    //        if (!isInteger(item)) {
    //            newItem = Integer.toString(menu.findItemIndex(item));
    //            String newArgumentString = arg.getArgumentString().replace(item, newItem);
    //            Command newCommand = new Command("/addorder " + newArgumentString);
    //            return newCommand;
    //        }
    //
    //
    //        if (!(isValidQuantity(arg))) {
    //            throw new OrderException(ui.getInvalidOrderInteger());
    //        }
    //
    //
    //        return arg;
    //    }

    public void validateFlag(Command arg)
            throws MissingOrderFlagException, MissingOrderArgumentException, MissingQuantityArgumentException {
        if (arg.getArgumentString().contains("-i") || arg.getArgumentString().contains("--item")) {
            if (arg.getArgumentMap().get("i").length() < 1 && arg.getArgumentMap().get("item").length() < 1) {
                throw new MissingOrderArgumentException();
            }
        } else {
            throw new MissingOrderFlagException();
        }

        if (arg.getArgumentString().contains("-q") || arg.getArgumentString().contains("--quantity")) {
            if (arg.getArgumentMap().get("q").length() < 1 && arg.getArgumentMap().get("quantity").length() < 1) {
                throw new MissingQuantityArgumentException();
            }

        }
    }

    public void validateIndex(Command arg, Menu menu)
            throws InvalidIndexNumberFormatException, InvalidIndexNegativeException, InvalidIndexOutOfBoundsException {

        arg.mapArgumentAlias("i", "item");

        if (!isInteger(arg.getArgumentMap().get("i").trim())) {
            throw new InvalidIndexNumberFormatException();
        } else {
            int index = Integer.parseInt(arg.getArgumentMap().get("i").trim());
            if (index < 0) {
                throw new InvalidIndexNegativeException();
            } else if (!isValidIndex(Integer.toString(index), menu)) {
                throw new InvalidIndexOutOfBoundsException();
            }
        }
    }

    public void validateQuantity(Command arg)
            throws InvalidQuantityNumberFormatException, InvalidQuantityNegativeException {

        arg.mapArgumentAlias("q", "quantity");

        if (arg.getArgumentMap().get("q").length() > 0) {
            if (!isInteger(arg.getArgumentMap().get("q").trim())) {
                throw new InvalidQuantityNumberFormatException();
            } else {
                int quantity = Integer.parseInt(arg.getArgumentMap().get("q").trim());
                if (quantity <= 0) {
                    throw new InvalidQuantityNegativeException();
                }
            }
        }

    }

    /**
     * Check if the input is integer
     *
     * @param input the user input after flags
     * @return validation outcome (true/false)
     */
    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException n) {
            return false;
        }

        return true;
    }

    /*
    public boolean isValidQuantity(Command arg) throws OrderException {
        int quantity = 0;
        if (arg.getArgumentString().contains("-q")) {

            if (!(isInteger(arg.getArgumentMap().get("q").trim()))) {
                throw new OrderException(ui.getInvalidOrderInteger());
            } else {
                quantity = Integer.parseInt(arg.getArgumentMap().get("q").trim());
            }

        } else if (arg.getArgumentString().contains("--quantity")) {
            if (!(isInteger(arg.getArgumentMap().get("quantity").trim()))) {
                throw new OrderException(ui.getInvalidOrderInteger());
            } else {
                quantity = Integer.parseInt(arg.getArgumentMap().get("quantity").trim());
            }
        } else {
            return true;
        }

        if (quantity <= 0) {
            return false;
        }
        return true;
    }
    */

}



