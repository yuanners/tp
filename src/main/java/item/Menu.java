package item;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import java.lang.reflect.Type;

import app.Command;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonParseException;
import exception.ItemException;
import utility.Store;
import utility.Ui;
import validation.item.AddItemValidation;
import validation.item.DeleteItemValidation;

public class Menu {

    private ArrayList<Item> items;
    private Store store;

    public Menu() {
        this.store = new Store("menu.json");
        Type type = new TypeToken<ArrayList<Item>>() {
        }.getType();

        try {
            this.items = store.load(type);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            this.items = new ArrayList<>();
        } catch (JsonParseException e) {
            System.out.println(e.getMessage());
            this.items = new ArrayList<>();
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            this.items = new ArrayList<>();
        }
    }

    public Menu(Store store) {
        this.store = store;
        Type type = new TypeToken<ArrayList<Item>>() {
        }.getType();

        try {
            this.items = store.load(type);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            this.items = new ArrayList<>();
        } catch (JsonParseException e) {
            System.out.println(e.getMessage());
            this.items = new ArrayList<>();
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            this.items = new ArrayList<>();
        }
    }

    public void displayList() {
        Ui ui = new Ui();
        ui.printMenu(items);
        ui.printCommandSuccess("listitem");
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

    public void save() {
        try {
            store.save(items);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addItem(Command command, Menu items) throws ItemException {
        try {
            AddItemValidation addItemValidation = new AddItemValidation();
            addItemValidation.validateFlags(command);
            command.mapArgumentAlias(addItemValidation.LONG_NAME_FLAG, addItemValidation.SHORT_NAME_FLAG);
            command.mapArgumentAlias(addItemValidation.LONG_PRICE_FLAG, addItemValidation.SHORT_PRICE_FLAG);
            addItemValidation.validateCommand(command, items);
        } catch (ItemException e) {
            throw new ItemException(e.getMessage());
        }

        String name = command.getArgumentMap().get("name");
        Double price = Double.valueOf(command.getArgumentMap().get("price"));
        Item item = new Item(name, price);
        appendItem(item);
        save();
    }

    public void deleteItem(Command command, Menu items) throws ItemException {
        try {
            DeleteItemValidation deleteItemValidation = new DeleteItemValidation();
            deleteItemValidation.validateFlags(command);
            command.mapArgumentAlias(deleteItemValidation.LONG_INDEX_FLAG, deleteItemValidation.SHORT_INDEX_FLAG);
            deleteItemValidation.validateCommand(command, items);
        } catch (ItemException e) {
            throw new ItemException(e.getMessage());
        }

        int index = Integer.parseInt(command.getArgumentMap().get("index"));
        removeItem(index);
        save();
    }

    public int findItemIndex(String itemName, ArrayList<Item> menu) {

        Ui ui = new Ui();
        itemName = itemName.toLowerCase();

        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).getName().toLowerCase().contains(itemName)) {
                return i;
            }
        }

        ui.printItemNotFound();
        return -1;

    }

    public ArrayList<Integer> findMatchingItemNames(String itemName, ArrayList<Item> menu) {

        ArrayList<Integer> itemIndexes = new ArrayList<>();
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

    public void showResultsOfFind(Command command, ArrayList<Item> menu) {

        Ui ui = new Ui();
        ui.printMenuHeader();

        String itemName = command.getArgumentString();

        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).getName().contains(itemName)) {
                ui.printFindItem(i, menu);
            }
        }
    }
}
