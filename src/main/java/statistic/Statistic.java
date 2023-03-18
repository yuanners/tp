package statistic;

import order.Order;
import order.Transaction;

public class Statistic {

    public void totalRevenue(Transaction transaction){
        for(Order o : transaction.getOrderList()){
            System.out.println(o.getSubTotal());
        }
    }
}
