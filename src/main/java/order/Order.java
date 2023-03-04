package order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Order {

    private String orderId;
    private LocalDateTime dateTime;
    private ArrayList<OrderEntry> orderEntries;


    public Order() {
        this.orderId = UUID.randomUUID().toString();
        this.dateTime = LocalDateTime.now();
        this.orderEntries = new ArrayList<>();
    }

    public double subtotal() {
        double subtotal = 0.00;


        //Compute total base on order

        return subtotal;
    }
}
