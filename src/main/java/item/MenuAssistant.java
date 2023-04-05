package item;

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
import ui.Flags;
import ui.MenuAssistantUi;
import validation.item.AddItemValidation;
import validation.item.DeleteItemValidation;

public class MenuAssistant {
    private MenuAssistantUi menuAssistantUi;
    private final String CANCEL = "/cancel";
    private final String YES = "yes";
    private final String NO = "no";
    private AddItemValidation addItemValidation;
    private DeleteItemValidation deleteItemValidation;
    public MenuAssistant() {
        menuAssistantUi = new MenuAssistantUi();
        addItemValidation = new AddItemValidation();
        deleteItemValidation = new DeleteItemValidation();
    }

    /**
     * Prints the result of the operation depending on whether
     * the operation has completed execution successfully or
     * cancelled by the user.
     *
     * @param command the Command object containing the search term
     * @param isCancelled the boolean variable indicating if operation was cancelled
     */
    public void printResult(Command command, boolean isCancelled) {
        if (isCancelled) {
            menuAssistantUi.printCommandCancelled(command.getCommand());
        } else if(!command.getCommand().equals("finditem")){
            menuAssistantUi.printCommandSuccess(command.getCommand());
        }
    }

    /**
     * Get the name of the item.
     *
     * @param command the Command object containing the search term
     * @param menu the ArrayList of Item objects to search through
     * @return boolean variable indicating if operation was cancelled
     */
    private boolean getName(Command command, Menu menu) {
        String name = "";
        boolean isValidName = false;

        while (!isValidName) {
            menuAssistantUi.promptItemName();
            name = menuAssistantUi.inputHandler();

            if(name.equals(CANCEL)) {
                return true;
            }

            name = name.trim();
            command.getArgumentMap().put(addItemValidation.LONG_NAME_FLAG, name);
            command.getArgumentMap().put(addItemValidation.SHORT_NAME_FLAG, name);

            try {
                addItemValidation.validateName(command);
                addItemValidation.validateDuplicateName(command, menu);
                isValidName = true;
            } catch (NameMinimumLengthException e) {
                menuAssistantUi.printError(Flags.Error.ITEM_NAME_MIN_LENGTH_ERROR);
            } catch (NameMaximumLengthException e) {
                menuAssistantUi.printError(Flags.Error.ITEM_NAME_MAX_LENGTH_ERROR);
            } catch (DuplicateNameException e) {
                menuAssistantUi.printError(Flags.Error.ITEM_DUPLICATE_NAME_ERROR);
            } catch (NameIsIntegerException e) {
                menuAssistantUi.printError(Flags.Error.ITEM_NAME_IS_INTEGER_ERROR);
            }

        }

        return false;
    }

    /**
     * Overloads getName() method above.
     * Get the name of the item to be updated.
     * Additionally, checks if the new name is equal to the current name (case-insensitive).
     *
     * @param command the Command object containing the search term
     * @param menu the ArrayList of Item objects to search through
     * @param currentName the current name of the item being updated in all lower case
     * @return boolean variable indicating if operation was cancelled
     */
    private boolean getName(Command command, Menu menu, String currentName) {
        String name = "";
        boolean isValidName = false;

        while (!isValidName) {
            menuAssistantUi.promptItemName();
            name = menuAssistantUi.inputHandler();

            if(name.equals(CANCEL)) {
                return true;
            }

            name = name.trim();
            command.getArgumentMap().put(addItemValidation.LONG_NAME_FLAG, name);
            command.getArgumentMap().put(addItemValidation.SHORT_NAME_FLAG, name);

            try {
                addItemValidation.validateName(command);
                if(!name.toLowerCase().equals(currentName)) {
                    addItemValidation.validateDuplicateName(command, menu);
                }
                isValidName = true;
            } catch (NameMinimumLengthException e) {
                menuAssistantUi.printError(Flags.Error.ITEM_NAME_MIN_LENGTH_ERROR);
            } catch (NameMaximumLengthException e) {
                menuAssistantUi.printError(Flags.Error.ITEM_NAME_MAX_LENGTH_ERROR);
            } catch (DuplicateNameException e) {
                menuAssistantUi.printError(Flags.Error.ITEM_DUPLICATE_NAME_ERROR);
            } catch (NameIsIntegerException e) {
                menuAssistantUi.printError(Flags.Error.ITEM_NAME_IS_INTEGER_ERROR);
            }

        }

        return false;
    }

    /**
     * Get the price of the item.
     *
     * @param command the Command object containing the search term
     * @param menu the ArrayList of Item objects to search through
     * @return boolean variable indicating if operation was cancelled
     */
    private boolean getPrice(Command command, Menu menu) {
        String price = "";
        boolean isValidPrice = false;

        while (!isValidPrice) {
            menuAssistantUi.promptItemPrice();
            price = menuAssistantUi.inputHandler();

            if(price.equals(CANCEL)) {
                return true;
            }

            command.getArgumentMap().put(addItemValidation.LONG_PRICE_FLAG, price);
            command.getArgumentMap().put(addItemValidation.SHORT_PRICE_FLAG, price);

            try {
                addItemValidation.validatePrice(command);
                isValidPrice = true;
            } catch (PriceMinimumLengthException e) {
                menuAssistantUi.printError(Flags.Error.ITEM_PRICE_MIN_LENGTH_ERROR);
            } catch (PriceInvalidNumberException e) {
                menuAssistantUi.printError(Flags.Error.ITEM_PRICE_INVALID_FORMAT_ERROR);
            } catch (PriceOverflowException e) {
                menuAssistantUi.printError(Flags.Error.ITEM_PRICE_OVERFLOW_ERROR);
            } catch (PriceNegativeException e) {
                menuAssistantUi.printError(Flags.Error.ITEM_PRICE_NEGATIVE_ERROR);
            } catch (PriceInvalidDecimalPlaceException e) {
                menuAssistantUi.printError(Flags.Error.ITEM_PRICE_INVALID_DECIMAL_PLACE_ERROR);
            }

        }

        return false;
    }

    /**
     * Assisted mode for user to add items to the menu iteratively.
     *
     * @param command the Command object containing the search term
     * @param menu the ArrayList of Item objects to search through
     * @return boolean variable indicating if operation was cancelled
     */
    public boolean addItem(Command command, Menu menu) {
        boolean isCancelled = false;
        isCancelled = getName(command, menu);

        if(isCancelled) {
            return true;
        }

        isCancelled = getPrice(command, menu);

        if(isCancelled) {
            return true;
        }

        String name = command.getArgumentMap().get(addItemValidation.LONG_NAME_FLAG);
        Double price = Double.valueOf(command.getArgumentMap().get(addItemValidation.LONG_PRICE_FLAG));
        Item item = new Item(name, price);
        menu.appendItem(item);
        menu.save();
        return false;
    }

    /**
     * Assisted mode for user to update items in the menu iteratively.
     *
     * @param command the Command object containing the search term
     * @param menu the ArrayList of Item objects to search through
     * @return boolean variable indicating if operation was cancelled
     */
    public boolean updateItem(Command command, Menu menu) {
        if(menu.getItems().size() == 0) {
            menuAssistantUi.printError(Flags.Error.EMPTY_MENU);
            return true;
        }

        boolean isCancelled = false;
        boolean isValidResponse = false;
        String toUpdateName = "";
        String toUpdatePrice = "";

        isCancelled = getIndex(command, menu);
        if(isCancelled) {
            return true;
        }
        int index = Integer.parseInt(command.getArgumentMap().get(deleteItemValidation.LONG_INDEX_FLAG));

        while (!isValidResponse) {
            menuAssistantUi.promptItemNameChange();
            toUpdateName = menuAssistantUi.inputHandler();
            if(toUpdateName.equals(CANCEL)) {
                return true;
            }
            toUpdateName = toUpdateName.toLowerCase().trim();
            if(toUpdateName.equals(NO) ||
                    toUpdateName.equals(YES)) {
                isValidResponse = true;
            }
            if(!isValidResponse) {
                menuAssistantUi.promptUpdateItemUnrecognisedAnswer();
            }
        }

        isValidResponse = false;
        String currentName = menu.getItem(index).getName().toLowerCase();

        if(toUpdateName.equals(YES)) {
            isCancelled = getName(command, menu, currentName);
            if(isCancelled) {
                return true;
            }
        }

        while (!isValidResponse) {
            menuAssistantUi.promptItemPriceChange();
            toUpdatePrice = menuAssistantUi.inputHandler();
            if(toUpdatePrice.equals(CANCEL)) {
                return true;
            }
            toUpdatePrice = toUpdatePrice.toLowerCase().trim();
            if(toUpdatePrice.equals(NO) ||
                    toUpdatePrice.equals(YES)) {
                isValidResponse = true;
            }
            if(!isValidResponse) {
                menuAssistantUi.promptUpdateItemUnrecognisedAnswer();
            }
        }

        if(toUpdatePrice.equals(YES)) {
            isCancelled = getPrice(command, menu);
            if(isCancelled) {
                return true;
            }
        }

        if(toUpdateName.equals(YES)) {
            String name = command.getArgumentMap().get(addItemValidation.LONG_NAME_FLAG);
            menu.getItem(index).setName(name);
        }

        if(toUpdatePrice.equals(YES)) {
            Double price = Double.valueOf(command.getArgumentMap().get(addItemValidation.LONG_PRICE_FLAG));
            menu.getItem(index).setPrice(price);
        }

        menu.save();
        return false;
    }

    /**
     * Get the index of the item.
     *
     * @param command the Command object containing the search term
     * @param menu the ArrayList of Item objects to search through
     * @return boolean variable indicating if operation was cancelled
     */
    private boolean getIndex(Command command, Menu menu) {
        String index = "";
        boolean isValidIndex = false;

        while (!isValidIndex) {
            menuAssistantUi.promptItemIndex();
            index = menuAssistantUi.inputHandler();

            if(index.equals(CANCEL)) {
                return true;
            }

            command.getArgumentMap().put(deleteItemValidation.LONG_INDEX_FLAG, index);
            command.getArgumentMap().put(deleteItemValidation.SHORT_INDEX_FLAG, index);

            try {
                deleteItemValidation.validateIndex(command, menu);
                isValidIndex = true;
            } catch (IndexInvalidNumberFormatException e) {
                menuAssistantUi.printError(Flags.Error.ITEM_INDEX_INVALID_FORMAT_ERROR);
            } catch (IndexOverflowException e) {
                menuAssistantUi.printError(Flags.Error.ITEM_INDEX_OVERFLOW_ERROR);
            } catch (IndexOutOfBoundException e) {
                menuAssistantUi.printError(Flags.Error.ITEM_INDEX_OUT_OF_BOUND_ERROR);
            }

        }
        return false;
    }

    /**
     * Assisted mode for user to delete items from the menu iteratively.
     *
     * @param command the Command object containing the search term
     * @param menu the ArrayList of Item objects to search through
     * @return boolean variable indicating if operation was cancelled
     */
    public boolean deleteItem(Command command, Menu menu) {
        if(menu.getItems().size() == 0) {
            menuAssistantUi.printError(Flags.Error.EMPTY_MENU);
            return true;
        }

        boolean isCancelled = false;
        isCancelled = getIndex(command, menu);

        if(isCancelled) {
            return true;
        }

        int index = Integer.parseInt(command.getArgumentMap().get(deleteItemValidation.LONG_INDEX_FLAG));
        menu.removeItem(index);
        menu.save();

        return false;
    }

    /**
     * Get the keyword to search for.
     *
     * @param command the Command object containing the search term
     * @param menu the ArrayList of Item objects to search through
     * @return boolean variable indicating if operation was cancelled
     */
    private boolean getKeyword(Command command, Menu menu) {
        String keyword = "";

        menuAssistantUi.promptItemKeyword();
        keyword = menuAssistantUi.inputHandler();

        if(keyword.equals(CANCEL)) {
            return true;
        }

        command.setArgumentString(keyword);

        menu.showResultsOfFind(command);

        return false;
    }

    /**
     * Assisted mode for user to search for items from the menu iteratively.
     *
     * @param command the Command object containing the search term
     * @param menu the ArrayList of Item objects to search through
     * @return boolean variable indicating if operation was cancelled
     */
    public boolean showResultsOfFind(Command command, Menu menu) {
        boolean isCancelled;
        isCancelled = getKeyword(command, menu);

        return isCancelled;
    }

}
