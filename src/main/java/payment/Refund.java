package payment;

import app.Command;
import order.Order;
import order.OrderEntry;
import order.Transaction;

import java.util.ArrayList;

public class Refund {
    private Transaction transaction;
    private Order order;

    public Refund() {

    }

    public void refundTransaction(Command arg) {
        String orderID = arg.getArgumentString();
        ArrayList<Order> orderList = transaction.getOrderList();
        for (Order order : orderList) {
            String ID = order.getOrderId();
            if(ID == orderID){

            }else{

            }
        }

        Transaction refundedTransaction = transaction.getTransactionById(refundedOrder.getTransactionId());

        if (refundedTransaction.getState() == TransactionState.COMPLETE){
            refundedTransaction.setState(TransactionState.REFUNDED);
            refundedOrder.setState(OrderState.REFUNDED);
    }
}