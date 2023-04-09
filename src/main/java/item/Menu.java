package item;

import java.io.IOException;
import java.util.ArrayList;

import java.lang.reflect.Type;

import exception.DuplicateArgumentFoundException;
import exception.FileIsEmptyException;
import exception.UnrecognisedCommandException;
import exception.item.MissingFindItemDescriptionException;
import app.Command;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonParseException;
import exception.item.NameMinimumLengthException;
import org.apache.commons.lang3.StringUtils;
import ui.Flags;
import ui.MenuUi;
import ui.StoreUi;
import ui.Ui;
import utility.Store;
import validation.Validation;
import validation.item.AddItemValidation;
import validation.item.DeleteItemValidation;
import validation.item.FindItemValidation;
import validation.item.UpdateItemValidation;

public class Menu {

    private ArrayList<Item> items;
    private Store store;
    private MenuUi menuUi;

    public Menu(String fileName) {
        this.menuUi = new MenuUi();
        this.store = new Store(fileName);
        Type type = new TypeToken<ArrayList<Item>>() {
        }.getType();

        try {
            this.items = store.load(type);
        } catch (IOException e) {
            new StoreUi().menuNotFound();
            this.items = new ArrayList<>();
            save();

        } catch (JsonParseException | NumberFormatException | FileIsEmptyException e) {

            if (new StoreUi().reinitializeMenu()) {
                this.items = new ArrayList<>();
                save();
            } else {
                System.exit(0);
            }
        }
    }


    public Menu(String dirName, String fileName) {
        this.menuUi = new MenuUi();
        this.store = new Store(dirName, fileName);
        Type type = new TypeToken<ArrayList<Item>>() {
        }.getType();

        try {
            this.items = store.load(type);
        } catch (IOException | JsonParseException | NumberFormatException | FileIsEmptyException e) {
            System.out.println(e.getMessage());
            this.items = new ArrayList<>();
        }
    }

    public void displayList(Command command) {
        try {
            Validation validation = new Validation();
            validation.validateNoArgumentCommand(command);
        } catch (UnrecognisedCommandException e) {
            Ui ui = new Ui();
            ui.printError(Flags.Error.UNRECOGNISED_COMMAND_ERROR);
            return;
        }
        MenuUi menuUi = new MenuUi();
        if (this.items.size() != 0) {
            menuUi.printMenu(items);
            menuUi.printCommandSuccess(command.getCommand());
        } else {
            menuUi.printError(Flags.Error.EMPTY_MENU);
        }
    }

    public void appendItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(int index) {
        this.items.remove(index);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item getItem(int index) {
        return items.get(index);
    }

    /**
     * Validates the add item command and calls processAddItem if command is valid
     *
     * @param command the Command object containing the search term
     */
    public void addItem(Command command) throws DuplicateArgumentFoundException {

        AddItemValidation addItemValidation = new AddItemValidation();
        boolean isValid = true;

        isValid = addItemValidation.validateFlags(command);
        if (!isValid) {
            return;
        }

        command.mapArgumentAlias(addItemValidation.LONG_NAME_FLAG, addItemValidation.SHORT_NAME_FLAG);
        command.mapArgumentAlias(addItemValidation.LONG_PRICE_FLAG, addItemValidation.SHORT_PRICE_FLAG);

        isValid = addItemValidation.validateCommand(command, this);
        if (!isValid) {
            return;
        }

        if (command.getArgumentMap().containsKey(addItemValidation.SHORT_NAME_FLAG)) {
            command.getArgumentMap().put(addItemValidation.SHORT_NAME_FLAG,
                    command.getArgumentMap().get(addItemValidation.SHORT_NAME_FLAG).trim());
        }

        if (command.getArgumentMap().containsKey(addItemValidation.LONG_NAME_FLAG)) {
            command.getArgumentMap().put(addItemValidation.LONG_NAME_FLAG,
                    command.getArgumentMap().get(addItemValidation.LONG_NAME_FLAG).trim());
        }

        processAddItem(command, addItemValidation);
        menuUi.printCommandSuccess(command.getCommand());
    }

    private void processAddItem(Command command, AddItemValidation addItemValidation) {
        String name = command.getArgumentMap().get(addItemValidation.LONG_NAME_FLAG);
        Double price = Double.valueOf(command.getArgumentMap().get(addItemValidation.LONG_PRICE_FLAG));
        Item item = new Item(name, price);
        appendItem(item);
        assert this.getItem(this.getItems().size() - 1).getName().equals(item.getName())
                : "Item failed to append";
        save();
    }

    /**
     * Updates a specified item on the menu by its given index.
     *
     * @param command the Command object containing the search term
     */
    public void updateItem(Command command) throws DuplicateArgumentFoundException {
        if (this.getItems().size() == 0) {
            menuUi.printError(Flags.Error.EMPTY_MENU);
            return;
        }

        UpdateItemValidation updateItemValidation = new UpdateItemValidation();
        boolean isValid = true;

        isValid = updateItemValidation.validateFlags(command);
        if (!isValid) {
            return;
        }
        command.mapArgumentAlias(updateItemValidation.LONG_INDEX_FLAG, updateItemValidation.SHORT_INDEX_FLAG);
        command.mapArgumentAlias(updateItemValidation.LONG_NAME_FLAG, updateItemValidation.SHORT_NAME_FLAG);
        command.mapArgumentAlias(updateItemValidation.LONG_PRICE_FLAG, updateItemValidation.SHORT_PRICE_FLAG);

        isValid = updateItemValidation.validateCommand(command, this);
        if (!isValid) {
            return;
        }

        if (command.getArgumentMap().containsKey(updateItemValidation.SHORT_NAME_FLAG)) {
            command.getArgumentMap().put(updateItemValidation.SHORT_NAME_FLAG,
                    command.getArgumentMap().get(updateItemValidation.SHORT_NAME_FLAG).trim());
        }

        if (command.getArgumentMap().containsKey(updateItemValidation.LONG_NAME_FLAG)) {
            command.getArgumentMap().put(updateItemValidation.LONG_NAME_FLAG,
                    command.getArgumentMap().get(updateItemValidation.LONG_NAME_FLAG).trim());
        }

        processUpdateItem(command, updateItemValidation);
        menuUi.printCommandSuccess(command.getCommand());

    }

    private void processUpdateItem(Command command, UpdateItemValidation updateItemValidation) {
        int index = Integer.parseInt(command.getArgumentMap().get(updateItemValidation.LONG_INDEX_FLAG));

        if (command.getArgumentMap().containsKey(updateItemValidation.LONG_NAME_FLAG)) {
            this.getItem(index).setName(command.getArgumentMap().get(updateItemValidation.LONG_NAME_FLAG));

        }

        if (command.getArgumentMap().containsKey(updateItemValidation.LONG_PRICE_FLAG)) {
            Double price = Double.valueOf(command.getArgumentMap().get(updateItemValidation.LONG_PRICE_FLAG));
            this.getItem(index).setPrice(price);
        }
        save();
    }

    /**
     * Deletes a specified item on the menu by its given index.
     *
     * @param command the Command object containing the search term
     */
    public void deleteItem(Command command) throws DuplicateArgumentFoundException {
        if (this.getItems().size() == 0) {
            menuUi.printError(Flags.Error.EMPTY_MENU);
            return;
        }

        DeleteItemValidation deleteItemValidation = new DeleteItemValidation();
        boolean isValid = true;

        isValid = deleteItemValidation.validateFlags(command);
        if (!isValid) {
            return;
        }
        command.mapArgumentAlias(deleteItemValidation.LONG_INDEX_FLAG, deleteItemValidation.SHORT_INDEX_FLAG);
        isValid = deleteItemValidation.validateCommand(command, this);
        if (!isValid) {
            return;
        }

        processDeleteItem(command, deleteItemValidation);
        menuUi.printCommandSuccess(command.getCommand());
    }

    private void processDeleteItem(Command command, DeleteItemValidation deleteItemValidation) {
        int index = Integer.parseInt(command.getArgumentMap().get(deleteItemValidation.LONG_INDEX_FLAG));
        removeItem(index);
        save();
    }

    /**
     * Finds the index of the first item in the provided ArrayList of Item objects
     * whose name contains the specified itemName, case-insensitively.
     *
     * @param itemName the name of the item to search for, case-insensitively
     * @return the index of the first matching item if found, or -1 if no matching item is found
     */
    public int findItemIndex(String itemName) {

        ArrayList<Item> menu = this.getItems();
        itemName = itemName.toLowerCase();

        if (itemName.contains("\"")) {
            itemName = itemName.replace("\"", "");
        }

        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).getName().toLowerCase().contains(itemName)) {
                return i;
            }
        }

        return -1;

    }

    /**
     * Finds the indexes of all items in the provided ArrayList of Item objects
     * whose names contain the specified itemName, case-insensitively.
     * If itemName is an exact match for an item's name, only the index of that item is returned.
     *
     * @param itemName the name of the item to search for, case-insensitively
     * @return an ArrayList of integers containing the indexes of all matching items, or empty if no result
     */
    public ArrayList<Integer> findMatchingItemNames(String itemName) {

        ArrayList<Integer> itemIndexes = new ArrayList<>();
        ArrayList<Item> menu = this.getItems();
        itemName = itemName.toLowerCase();

        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).getName().toLowerCase().contains(itemName)) {
                if (itemName.equals(menu.get(i).getName().toLowerCase())) {
                    itemIndexes.removeAll(itemIndexes);
                    itemIndexes.add(i);
                    break;
                }
                itemIndexes.add(i);
            }
        }

        return itemIndexes;
    }

    /**
     * Displays a header followed by the names and prices of all items
     * in the provided ArrayList of Item objects whose names contain
     * the search term specified in the provided Command object.
     *
     * @param command the Command object containing the search term
     */
    public void showResultsOfFind(Command command) {

        FindItemValidation findItemValidation = new FindItemValidation();

        ArrayList<Item> menu = this.getItems();
        ArrayList<Integer> indexes = new ArrayList<>();

        String itemName = command.getArgumentString().trim();

        if (itemName.contains("\"")) {
            itemName = itemName.replace("\"", "");
        }

        try {
            findItemValidation.validateName(itemName);
        } catch (MissingFindItemDescriptionException e) {
            menuUi.printError(Flags.Error.MISSING_FIND_ITEM_DESCRIPTION);
            return;
        } catch (NameMinimumLengthException e) {
            menuUi.printError(Flags.Error.EMPTY_FIND_ITEM_DESCRIPTION);
            return;
        }

        for (int i = 0; i < menu.size(); i++) {
            if (StringUtils.containsIgnoreCase(menu.get(i).getName(), itemName)) {
                indexes.add(i);
            }
        }

        if (indexes.size() == 0) {
            menuUi.printNoItemFound(itemName);
            return;
        }

        menuUi.printMenuHeader();
        for (int i = 0; i < indexes.size(); i++) {
            menuUi.printFindItem(indexes.get(i), menu);
        }

        menuUi.printCommandSuccess(command.getCommand());
    }

    /**
     * Has the same functionality as showResultsOfFind(),
     * but does not print the success message at the end.
     *
     * @param command the Command object containing the search term
     */
    public void showResultsOfFindWithoutSuccessMsg(Command command) {

        FindItemValidation findItemValidation = new FindItemValidation();

        ArrayList<Item> menu = this.getItems();
        ArrayList<Integer> indexes = new ArrayList<>();

        String itemName = command.getArgumentString().trim();

        try {
            findItemValidation.validateName(itemName);
        } catch (MissingFindItemDescriptionException e) {
            menuUi.printError(Flags.Error.MISSING_FIND_ITEM_DESCRIPTION);
            return;
        } catch (NameMinimumLengthException e) {
            menuUi.printError(Flags.Error.ITEM_NAME_MIN_LENGTH_ERROR);
            return;
        }

        if (itemName.contains("\"")) {
            itemName = itemName.replace("\"", "");
        }

        for (int i = 0; i < menu.size(); i++) {
            if (StringUtils.containsIgnoreCase(menu.get(i).getName(), itemName)) {
                indexes.add(i);
            }
        }

        if (indexes.size() == 0) {
            menuUi.printNoItemFound(itemName);
            return;
        }

        menuUi.printMenuHeader();
        for (int i = 0; i < indexes.size(); i++) {
            menuUi.printFindItem(indexes.get(i), menu);
        }

    }


    public void save() {
        try {
            store.save(items);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
