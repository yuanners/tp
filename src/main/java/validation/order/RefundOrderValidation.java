package validation.order;

import app.Command;
import exception.DuplicateArgumentFoundException;
import exception.order.InvalidRefundOrderID;
import exception.order.InvalidRefundOrderType;
import exception.order.MissingRefundOrderArgument;
import exception.order.MissingRefundOrderFlag;
import order.Order;
import order.Transaction;
import validation.Validation;

import java.util.ArrayList;

public class RefundOrderValidation extends Validation {
    private Order refundOrder = new Order();

    public RefundOrderValidation() {
    }

    /**
     * Check if flags and arguments are present
     *
     * @param arg user input
     * @throws MissingRefundOrderFlag     no -i/--id flag in user input
     * @throws MissingRefundOrderArgument no argument for -i/--id flag in user input
     */
    public void validateFlag(Command arg) throws MissingRefundOrderFlag, MissingRefundOrderArgument,
            DuplicateArgumentFoundException {
        arg.mapArgumentAlias("i", "id");
        if (arg.getArgumentString().contains("-i")) {
            if (arg.getArgumentMap().get("i") == null) {
                throw new MissingRefundOrderArgument();
            }
        } else {
            throw new MissingRefundOrderFlag();
        }
    }

    /**
     * Check if the order ID given/refund status is valid
     *
     * @param arg user input
     * @throws InvalidRefundOrderType order is already refunded
     * @throws InvalidRefundOrderID   invalid order ID
     */
    public void validateRefund(Command arg, Transaction transaction) throws
            InvalidRefundOrderType, InvalidRefundOrderID, DuplicateArgumentFoundException {
        arg.mapArgumentAlias("i", "id");
        boolean isValidID = false;
        String orderID = "";
        if (arg.getArgumentString() != null && arg.getArgumentString().length() > 1) {
            orderID = arg.getArgumentMap().get("i").trim();
        } else {
            orderID = arg.getUserInput();
        }
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
                throw new InvalidRefundOrderType();
            }
        } else {
            throw new InvalidRefundOrderID();
        }
    }
}
