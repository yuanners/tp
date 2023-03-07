package item;

import java.util.ArrayList;
import utility.Ui;

public class ItemList {

    private ArrayList<Item> items;

    public ItemList() {
        items = new ArrayList<>();
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
}
