package item;

import java.io.IOException;
import java.util.ArrayList;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonParseException;
import utility.Store;
import utility.Ui;

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

    public void displayList() {
        Ui ui = new Ui();

        ui.printTableHeader("Index", "Name", "Price");
        for (int i = 0; i < items.size(); i++) {
            ui.printItemMenu(i, items.get(i).getName(), items.get(i).getPrice());
        }
    }

    public void appendItems(Item item) {
        this.items.add(item);
    }

    public void deleteItems(int index) {
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
}
