package validation.order;

import app.Command;
import exception.OrderException;
import order.Transaction;
import order.Order;
import utility.Ui;
import validation.Validation;

import java.util.ArrayList;

public class RefundOrderValidation extends Validation {
    private Transaction transaction;
    private Order order;
    private Ui ui;

    public RefundOrderValidation() {

    }

    public void validateRefundOrder(Command arg) throws OrderException {
        try{
            checkArgument(arg);
            checkOrderID(arg);
        } catch (OrderException o) {
            throw new OrderException(o.getMessage());
        }

    }

    public void checkArgument(Command arg) throws OrderException {
        if (arg.getArgumentString().length() < 1) {
            throw new OrderException(ui.printMissingOrderID());
        }
    }

    public void checkOrderID(Command arg) throws OrderException {
        boolean isValidID = false;
        String orderID = arg.getArgumentString();
        ArrayList<Order> orderList = transaction.getOrderList();
        for (Order order : orderList) {
            String ID = order.getOrderId();
            if (ID == orderID) {
                isValidID = true;
                break;
            }
        }

        if (!(isValidID)) {
            throw new OrderException(ui.printInvalidOrderID());
        }
    }

    public void checkOrderStatus(Command arg) throws OrderException{

    }
}
