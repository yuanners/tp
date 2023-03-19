package statistic;

public class ItemRank {
    private String itemName;
    private int quantity;

    private double sales;

    public ItemRank(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public ItemRank(String itemName, double sales) {
        this.itemName = itemName;
        this.sales = sales;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }
}
