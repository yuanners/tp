package item;

import app.Command;
import exception.ItemException;
import utility.Ui;
import validation.item.AddItemValidation;
import validation.item.DeleteItemValidation;

import java.util.Scanner;

public class MenuAssistant {
    Ui ui;
    Scanner sc;
    private final String CANCEL = "/cancel";
    private final String YES = "yes";
    private final String NO = "no";
    private AddItemValidation addItemValidation;
    private DeleteItemValidation deleteItemValidation;
    public MenuAssistant() {
        ui = new Ui();
        sc = new Scanner(System.in);
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
            ui.printCommandCancelled(command.getCommand());
        } else {
            ui.printCommandSuccess(command.getCommand());
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
            ui.promptItemName();
            name = sc.nextLine();

            if(name.equals(CANCEL)) {
                return true;
            }

            command.getArgumentMap().put(addItemValidation.LONG_NAME_FLAG, name);
            command.getArgumentMap().put(addItemValidation.SHORT_NAME_FLAG, name);

            try {
                addItemValidation.validateName(command);
                addItemValidation.validateDuplicateName(command, menu);
                isValidName = true;
            } catch(ItemException e) {
                ui.println(e.getMessage());
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
            ui.promptItemName();
            name = sc.nextLine();

            if(name.equals(CANCEL)) {
                return true;
            }

            command.getArgumentMap().put(addItemValidation.LONG_NAME_FLAG, name);
            command.getArgumentMap().put(addItemValidation.SHORT_NAME_FLAG, name);

            try {
                addItemValidation.validateName(command);
                if(!name.toLowerCase().equals(currentName)) {
                    addItemValidation.validateDuplicateName(command, menu);
                }
                isValidName = true;
            } catch(ItemException e) {
                ui.println(e.getMessage());
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
            ui.promptItemPrice();
            price = sc.nextLine();

            if(price.equals(CANCEL)) {
                return true;
            }

            command.getArgumentMap().put(addItemValidation.LONG_PRICE_FLAG, price);
            command.getArgumentMap().put(addItemValidation.SHORT_PRICE_FLAG, price);

            try {
                addItemValidation.validatePrice(command);
                isValidPrice = true;
            } catch(ItemException e) {
                ui.println(e.getMessage());
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
            ui.println(ui.getEmptyMenu());
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
            ui.promptItemNameChange();
            toUpdateName = sc.nextLine();
            if(toUpdateName.equals(CANCEL)) {
                return true;
            }
            toUpdateName = toUpdateName.toLowerCase().trim();
            if(toUpdateName.equals(NO) ||
                    toUpdateName.equals(YES)) {
                isValidResponse = true;
            }
            if(!isValidResponse) {
                ui.promptUpdateItemUnrecognisedAnswer();
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
            ui.promptItemPriceChange();
            toUpdatePrice = sc.nextLine();
            if(toUpdatePrice.equals(CANCEL)) {
                return true;
            }
            toUpdatePrice = toUpdatePrice.toLowerCase().trim();
            if(toUpdatePrice.equals(NO) ||
                    toUpdatePrice.equals(YES)) {
                isValidResponse = true;
            }
            if(!isValidResponse) {
                ui.promptUpdateItemUnrecognisedAnswer();
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
            ui.promptItemIndex();
            index = sc.nextLine();

            if(index.equals(CANCEL)) {
                return true;
            }

            command.getArgumentMap().put(deleteItemValidation.LONG_INDEX_FLAG, index);
            command.getArgumentMap().put(deleteItemValidation.SHORT_INDEX_FLAG, index);

            try {
                deleteItemValidation.validateIndex(command, menu);
                isValidIndex = true;
            } catch(ItemException e) {
                ui.println(e.getMessage());
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
            ui.println(ui.getEmptyMenu());
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
        boolean isRunning = false;

        ui.promptItemKeyword();
        keyword = sc.nextLine();

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
