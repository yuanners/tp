package item;

import java.io.IOException;
import java.util.ArrayList;

import java.lang.reflect.Type;

import app.Command;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonParseException;
import exception.InvalidArgumentException;
import exception.item.*;
import org.apache.commons.lang3.StringUtils;
import ui.Flags;
import ui.MenuUi;
import utility.Store;
import utility.Ui;
import validation.item.AddItemValidation;
import validation.item.DeleteItemValidation;
import validation.item.FindItemValidation;
import validation.item.UpdateItemValidation;

public class Menu {

    private ArrayList<Item> items;
    private Store store;
    private MenuUi menuUi;

    public Menu() {
        menuUi = new MenuUi();
        this.store = new Store("menu.json");
        Type type = new TypeToken<ArrayList<Item>>() {
        }.getType();

        try {
            this.items = store.load(type);
        } catch (IOException | JsonParseException | NumberFormatException e) {
            System.out.println(e.getMessage());
            this.items = new ArrayList<>();
        }
    }

    public Menu(boolean isTest) {
        this.items = new ArrayList<>();
    }

    public void displayList() {
        MenuUi ui = new MenuUi();
        ui.printMenu(items);
        ui.printSuccessfulListItem();
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

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     * Adds an item and its price onto the menu.
     *
     * @param command the Command object containing the search term
     * @throws ItemException if the command format, name or price is invalid
     */
    public void addItem(Command command) {
        AddItemValidation addItemValidation = new AddItemValidation();
        try {
            addItemValidation.validateFlags(command);
            command.mapArgumentAlias(addItemValidation.LONG_NAME_FLAG, addItemValidation.SHORT_NAME_FLAG);
            command.mapArgumentAlias(addItemValidation.LONG_PRICE_FLAG, addItemValidation.SHORT_PRICE_FLAG);

            addItemValidation.validateArgument(command);
            addItemValidation.validateName(command);
            addItemValidation.validateDuplicateName(command, this);
            addItemValidation.validatePrice(command);

            String name = command.getArgumentMap().get(addItemValidation.LONG_NAME_FLAG);
            Double price = Double.valueOf(command.getArgumentMap().get(addItemValidation.LONG_PRICE_FLAG));
            Item item = new Item(name, price);
            appendItem(item);
            assert this.getItem(this.getItems().size() - 1).getName().equals(item.getName())
                    : "Item failed to append";
            save();
        } catch (InvalidArgumentException e) {
            menuUi.printError(Flags.Error.EMPTY_INPUT);
        } catch (MissingNameAndPriceFlag e) {
            menuUi.printError(Flags.Error.MISSING_ITEM_NAME_AND_PRICE_FLAG);
        } catch (MissingNameFlagException e) {
            menuUi.printError(Flags.Error.MISSING_ITEM_NAME_FLAG);
        } catch (MissingPriceFlagException e) {
            menuUi.printError(Flags.Error.MISSING_ITEM_PRICE_FLAG);
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
        }

    }

    /**
     * Updates a specified item on the menu by its given index.
     *
     * @param command the Command object containing the search term
     * @throws ItemException if the command format is invalid
     *                       or index does not exist
     */
    public void updateItem(Command command) {
        if (this.getItems().size() == 0) {
            menuUi.printError(Flags.Error.EMPTY_MENU);
            return;
        }

        UpdateItemValidation updateItemValidation = new UpdateItemValidation();
        AddItemValidation addItemValidation = new AddItemValidation();
        DeleteItemValidation deleteItemValidation = new DeleteItemValidation();

        try {
            updateItemValidation.validateFlags(command);
            command.mapArgumentAlias(updateItemValidation.LONG_INDEX_FLAG, updateItemValidation.SHORT_INDEX_FLAG);
            command.mapArgumentAlias(updateItemValidation.LONG_NAME_FLAG, updateItemValidation.SHORT_NAME_FLAG);
            command.mapArgumentAlias(updateItemValidation.LONG_PRICE_FLAG, updateItemValidation.SHORT_PRICE_FLAG);

            updateItemValidation.validateArgument(command);
            deleteItemValidation.validateIndex(command, this);

            int indexOfItem = Integer.parseInt(command.getArgumentMap().get(deleteItemValidation.LONG_INDEX_FLAG));
            if(command.getArgumentMap().containsKey( updateItemValidation.LONG_NAME_FLAG)) {
                addItemValidation.validateName(command);

                String newName = command.getArgumentMap().get(updateItemValidation.LONG_NAME_FLAG).toLowerCase();
                String currentName = this.getItem(indexOfItem).getName().toLowerCase();
                if(!newName.equals(currentName)) {
                    addItemValidation.validateDuplicateName(command, this);
                }
            }

            if(command.getArgumentMap().containsKey(updateItemValidation.LONG_PRICE_FLAG)) {
                addItemValidation.validatePrice(command);
            }

            int index = Integer.parseInt(command.getArgumentMap().get(updateItemValidation.LONG_INDEX_FLAG));

            if (command.getArgumentMap().containsKey(updateItemValidation.LONG_NAME_FLAG)) {
                this.getItem(index).setName(command.getArgumentMap().get(updateItemValidation.LONG_NAME_FLAG));
            }

            if (command.getArgumentMap().containsKey(updateItemValidation.LONG_PRICE_FLAG)) {
                Double price = Double.valueOf(command.getArgumentMap().get(updateItemValidation.LONG_PRICE_FLAG));
                this.getItem(index).setPrice(price);
            }
            save();
        } catch (MissingIndexFlagException e) {
            menuUi.printError(Flags.Error.MISSING_ITEM_INDEX_FLAG);
        } catch (MissingNameOrPriceFlagException e) {
            menuUi.printError(Flags.Error.MISSING_ITEM_NAME_OR_PRICE_FLAG);
        }catch (InvalidArgumentException e) {
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
        }

    }

    /**
     * Deletes a specified item on the menu by its given index.
     *
     * @param command the Command object containing the search term
     * @throws ItemException if the command format is invalid
     *                       or index does not exist
     */
    public void deleteItem(Command command) {
        if (this.getItems().size() == 0) {
            menuUi.printError(Flags.Error.EMPTY_MENU);
            return;
        }

        DeleteItemValidation deleteItemValidation = new DeleteItemValidation();
        try {
            deleteItemValidation.validateFlags(command);
            command.mapArgumentAlias(deleteItemValidation.LONG_INDEX_FLAG, deleteItemValidation.SHORT_INDEX_FLAG);

            deleteItemValidation.validateArgument(command);
            deleteItemValidation.validateIndex(command, this);

            int index = Integer.parseInt(command.getArgumentMap().get(deleteItemValidation.LONG_INDEX_FLAG));
            removeItem(index);
            save();
        } catch (MissingIndexFlagException e) {
            menuUi.printError(Flags.Error.MISSING_ITEM_INDEX_FLAG);
        }catch (IndexInvalidNumberFormatException e) {
            menuUi.printError(Flags.Error.ITEM_INDEX_INVALID_FORMAT_ERROR);
        } catch (IndexOverflowException e) {
            menuUi.printError(Flags.Error.ITEM_INDEX_OVERFLOW_ERROR);
        } catch (IndexOutOfBoundException e) {
            menuUi.printError(Flags.Error.ITEM_INDEX_OUT_OF_BOUND_ERROR);
        } catch (InvalidArgumentException e) {
            menuUi.printError(Flags.Error.EMPTY_INPUT);
        }

    }

    /**
     * Finds the index of the first item in the provided ArrayList of Item objects
     * whose name contains the specified itemName, case-insensitively.
     *
     * @param itemName the name of the item to search for, case-insensitively
     * @return the index of the first matching item if found, or -1 if no matching item is found
     */
    public int findItemIndex(String itemName) {

        Ui ui = new Ui();
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

        ui.printItemNotFound();
        return -1;

    }

    /**
     * Finds the indexes of all items in the provided ArrayList of Item objects
     * whose names contain the specified itemName, case-insensitively.
     * If itemName is an exact match for an item's name, only the index of that item is returned.
     *
     * @param itemName the name of the item to search for, case-insensitively
     * @return an ArrayList of integers containing the indexes of all matching items,
     *     or an empty list if no matching item is found
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

        Ui ui = new Ui();
        FindItemValidation findItemValidation = new FindItemValidation();

        ArrayList<Item> menu = this.getItems();
        ArrayList<Integer> indexes = new ArrayList<>();

        String itemName = command.getArgumentString().trim();

        try {
            findItemValidation.validateName(itemName);
        } catch (MissingFindItemDescriptionException e) {
            menuUi.printError(Flags.Error.MISSING_FIND_ITEM_DESCRIPTION);
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
            ui.printNoItemsFound(itemName);
            return;
        }

        ui.printMenuHeader();
        for (int i = 0; i < indexes.size(); i++) {
            ui.printFindItem(indexes.get(i), menu);
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
