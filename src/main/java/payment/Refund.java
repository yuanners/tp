package payment;

import app.Command;
import exception.DuplicateArgumentFoundException;
import exception.order.InvalidRefundOrderID;
import exception.order.InvalidRefundOrderType;
import exception.order.MissingRefundOrderArgument;
import exception.order.MissingRefundOrderFlag;
import order.Order;
import order.Transaction;
import ui.Flags;
import ui.TransactionUi;
import validation.order.RefundOrderValidation;

import java.util.ArrayList;

public class Refund {
    private TransactionUi transactionUi = new TransactionUi();

    public Refund() {

    }

    /**
     * Validate the refundorder command before refunding it
     *
     * @param arg          user command
     * @param transactions list of orders
     */
    public void refundTransaction(Command arg, Transaction transactions) throws DuplicateArgumentFoundException {
        RefundOrderValidation refundOrderValidation = new RefundOrderValidation();

        try {
            if (!(transactions.getOrderList().isEmpty())) {
                refundOrderValidation.validateFlag(arg);
                refundOrderValidation.validateRefund(arg, transactions);
                getOrder(arg, transactions);
            } else {
                transactionUi.printEmptyTransaction();
            }
        } catch (MissingRefundOrderFlag e) {
            transactionUi.printError(Flags.Error.MISSING_REFUND_ORDER_FLAG);
        } catch (MissingRefundOrderArgument e) {
            transactionUi.printError(Flags.Error.MISSING_REFUND_ORDER_ARGUMENT);
        } catch (InvalidRefundOrderID e) {
            transactionUi.printError(Flags.Error.INVALID_REFUND_ORDER_ID);
        } catch (InvalidRefundOrderType e) {
            transactionUi.printError(Flags.Error.INVALID_REFUND_ORDER_TYPE);
        }
    }

    /**
     * Refund entire order based on order ID given
     *
     * @param arg          user input
     * @param transactions whole transaction list
     */
    public void getOrder(Command arg, Transaction transactions) throws DuplicateArgumentFoundException  {
        arg.mapArgumentAlias("i", "id");
        String orderID = arg.getArgumentMap().get("i").trim();
        Order refundOrder = new Order();
        ArrayList<Order> orderList = transactions.getOrderList();

        for (Order order : orderList) {
            String ID = order.getOrderId();
            if (ID.equals(orderID)) {
                refundOrder = order;
                break;
            }
        }

        refundOrder.setStatus("REFUNDED");
        transactions.save();
        transactionUi.printSuccessfulRefundOrder();
    }
}
