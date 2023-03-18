package item;


public class Item {
    private String name;
    private double price;

    public Item(String name, Double price) {
        assert name != null : "Item name is null";
        assert !name.equals("") : "Item name is empty";
        assert price != null : "Item price is null";
        assert price >= 0.00 : "Item price is negative";

        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
