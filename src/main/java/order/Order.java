package order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Order implements OrderInterface {

    private String orderId;
    private LocalDateTime dateTime;
    private ArrayList<OrderEntry> orderEntries;


    public Order() {
        this.orderId = UUID.randomUUID().toString();
        this.dateTime = LocalDateTime.now();
        this.orderEntries = new ArrayList<>();
    }

    public Order(ArrayList<OrderEntry> orderEntries) {
        this.orderId = UUID.randomUUID().toString();
        this.dateTime = LocalDateTime.now();
        this.orderEntries = orderEntries;
    }

    @Override
    public double getSubTotal() {

        double subtotal = 0;

        for (OrderEntry i : this.orderEntries) {
            subtotal += (i.getQuantity() * i.getItem().getPrice());
        }

        return subtotal;

    }
}
