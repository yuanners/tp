package payment;

import app.Command;
import exception.OrderException;
import order.Order;
import order.Transaction;
import validation.order.RefundOrderValidation;

import java.util.ArrayList;

public class Refund {

    public Refund() {

    }

    /**
     * Refund entire order based on order ID given
     *
     * @param arg          user input
     * @param transactions whole transaction list
     * @throws OrderException custom exception for order related commands
     */
    public void refundTransaction(Command arg, Transaction transactions) throws OrderException {
        String orderID = arg.getArgumentString();
        Order refundOrder = new Order();
        ArrayList<Order> orderList = transactions.getOrderList();
        RefundOrderValidation refundOrderValidation = new RefundOrderValidation();
        try {
            refundOrderValidation.validateRefundOrder(arg);
            for (Order order : orderList) {
                String ID = order.getOrderId();
                if (ID.equals(orderID)) {
                    refundOrder = order;
                    break;
                }
            }
            refundOrder.setStatus("REFUNDED");
            transactions.save();

        } catch (OrderException o) {
            throw new OrderException(o.getMessage());
        }
    }
}
