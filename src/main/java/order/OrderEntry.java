package order;

import item.Item;

/**
 * The OrderEntry class represents a single entry in an order, consisting of an item and its quantity.
 */
public class OrderEntry {

    /**
     * The item in the order entry.
     */
    private Item item;

    /**
     * The quantity of the item in the order entry.
     */
    private int quantity;

    /**
     * Constructs an OrderEntry with the given item and quantity.
     *
     * @param item     the item in the order entry
     * @param quantity the quantity of the item in the order entry
     */
    public OrderEntry(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Returns the item in the order entry.
     *
     * @return the item in the order entry
     */
    public Item getItem() {
        return item;
    }


    /**
     * Returns the quantity of the item in the order entry.
     *
     * @return the quantity of the item in the order entry
     */
    public int getQuantity() {
        return quantity;
    }


}
