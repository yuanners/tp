package item;

import java.util.ArrayList;

public class ItemList {

    private ArrayList<Item> items;

    public ItemList() {
        items = new ArrayList<>();
    }

    public void displayList() {
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + ".\t" + items.get(i).getName() + "\t" + items.get(i).getPrice());
        }
    }

    public void appendItems(Item item) {
        this.items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
