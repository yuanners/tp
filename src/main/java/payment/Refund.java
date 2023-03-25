package payment;

import app.Command;
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
     * Refund entire order based on order ID given
     *
     * @param arg          user input
     * @param transactions whole transaction list
     */
    public void refundTransaction(Command arg, Transaction transactions) {
        String orderID = arg.getArgumentString();
        Order refundOrder = new Order();
        ArrayList<Order> orderList = transactions.getOrderList();
        RefundOrderValidation refundOrderValidation = new RefundOrderValidation();
        try {
            refundOrderValidation.validateFlag(arg);
            refundOrderValidation.validateRefund(arg, transactions);
            for (Order order : orderList) {
                String ID = order.getOrderId();
                if (ID.equals(orderID)) {
                    refundOrder = order;
                    break;
                }
            }
            refundOrder.setStatus("REFUNDED");
            transactions.save();

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
}
