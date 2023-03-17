package validation.order;

import app.Command;
import exception.OrderException;
import order.Transaction;
import order.Order;
import utility.Ui;
import validation.Validation;

import java.util.ArrayList;

public class RefundOrderValidation extends Validation {
    private Transaction transaction = new Transaction();
    private Order refundOrder = new Order();
    private Ui ui = new Ui();

    public RefundOrderValidation() {

    }

    public void validateRefundOrder(Command arg) throws OrderException {
        try {
            checkArgument(arg);
            checkOrder(arg);
        } catch (OrderException o) {
            throw new OrderException(o.getMessage());
        }

    }

    public void checkArgument(Command arg) throws OrderException {
        if (arg.getArgumentString().length() < 1) {
            throw new OrderException(ui.printMissingOrderID());
        }
    }

    public void checkOrder(Command arg) throws OrderException {
        boolean isValidID = false;
        String orderID = arg.getArgumentString();
        ArrayList<Order> orderList = transaction.getOrderList();
        for (Order order : orderList) {
            String ID = order.getOrderId();
            if (ID.equals(orderID)) {
                isValidID = true;
                refundOrder = order;
                break;
            }
        }

        if (isValidID) {
            if (refundOrder.getStatus().equals("REFUNDED")) {
                throw new OrderException(ui.printInvalidRefundStatus());
            }
        } else {
            throw new OrderException(ui.printInvalidOrderID());
        }
    }
}
