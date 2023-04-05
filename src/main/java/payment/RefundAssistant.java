package payment;

import app.Command;
import exception.DuplicateArgumentFoundException;
import exception.order.InvalidRefundOrderID;
import exception.order.InvalidRefundOrderType;
import order.Order;
import order.Transaction;
import ui.Flags;
import ui.TransactionUi;
import validation.order.RefundOrderValidation;

import java.util.ArrayList;

public class RefundAssistant {
    private final String CANCEL = "/cancel";
    private String orderID = "";
    private TransactionUi transactionUi = new TransactionUi();
    private RefundOrderValidation refundOrderValidation = new RefundOrderValidation();

    /**
     * Get the refund order ID from user and validate it
     *
     * @param transaction list of orders
     * @return whether the user entered "/cancel"
     */
    public boolean getID(Transaction transaction) throws DuplicateArgumentFoundException {
        boolean isValidID = false;

        while (!isValidID) {
            transactionUi.promptOrderID();
            String input = transactionUi.inputHandler();

            if (input.equalsIgnoreCase(CANCEL)) {
                return true;
            }
            Command command = new Command(input);

            try {
                refundOrderValidation.validateRefund(command, transaction);
                orderID = input;
                isValidID = true;
            } catch (InvalidRefundOrderID e) {
                transactionUi.printError(Flags.Error.INVALID_REFUND_ORDER_ID);
            } catch (InvalidRefundOrderType e) {
                transactionUi.printError(Flags.Error.INVALID_REFUND_ORDER_TYPE);
            }
        }

        return false;
    }

    /**
     * Refund the entire order based on the input ID
     *
     * @param transaction list of orders
     * @return whether the user entered "/cancel"
     */
    public boolean refundOrder(Transaction transaction) throws DuplicateArgumentFoundException {
        if (transaction.getOrderList().isEmpty()) {
            transactionUi.printEmptyTransaction();
            return true;
        }
        boolean isCancelled = getID(transaction);

        if (isCancelled) {
            return true;
        }
        Order refundOrder = new Order();
        ArrayList<Order> orderList = transaction.getOrderList();

        for (Order order : orderList) {
            String ID = order.getOrderId();

            if (ID.equals(orderID)) {
                refundOrder = order;
                break;
            }

        }


        refundOrder.setStatus("REFUNDED");
        transaction.save();

        return false;
    }
}
