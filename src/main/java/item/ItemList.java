package item;

import java.util.ArrayList;

public class ItemList {

    private ArrayList<Item> items;

    public ItemList() {
        items = new ArrayList<>();
    }

    public void displayList() {
        System.out.printf("| %-5s | %-25s | %-5s |\n","Index","Name","Price");
        System.out.println("| " + "-".repeat(5) + " | " + "-".repeat(25) + " | " + "-".repeat(5) + " |");
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("| %-5d | %-25s | %-5.2f |\n", i, items.get(i).getName(), items.get(i).getPrice());
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

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
