package item;

public class Item {
    private String name;
    private double price;

    public Item(String name, Double price) throws ItemException {

        if(name == null) throw new ItemException("Name cannot be empty.");
        if(price == null) throw new ItemException("Price cannot be empty.");
        if(price < 0.00) throw new ItemException("Price cannot be negative.");

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
