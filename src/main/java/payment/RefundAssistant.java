package payment;

import app.Command;
import exception.order.InvalidRefundOrderID;
import exception.order.InvalidRefundOrderType;
import order.Order;
import order.Transaction;
import ui.Flags;
import ui.TransactionUi;
import validation.order.RefundOrderValidation;

import java.util.ArrayList;
import java.util.Scanner;

public class RefundAssistant {
    private final String CANCEL = "/cancel";
    private TransactionUi transactionUi = new TransactionUi();
    private Scanner scan = new Scanner(System.in);
    private Refund refund = new Refund();
    private RefundOrderValidation refundOrderValidation = new RefundOrderValidation();

    public boolean getID(Command command, Transaction transaction) {
        boolean isValidID = false;
        while (!isValidID) {
            transactionUi.promptOrderID();
            String input = scan.nextLine();
            if (input.equalsIgnoreCase(CANCEL)) {
                return true;
            }
            try {
                refundOrderValidation.validateRefund(command, transaction);
                isValidID = true;
            } catch (InvalidRefundOrderID e) {
                transactionUi.printError(Flags.Error.INVALID_REFUND_ORDER_ID);
            } catch (InvalidRefundOrderType e) {
                transactionUi.printError(Flags.Error.INVALID_REFUND_ORDER_TYPE);
            }
        }
        return false;
    }

    public boolean refundOrder(Command command, Transaction transaction){
        boolean isCancelled = getID(command, transaction);
        if(isCancelled){
            return true;
        }
        Order refundOrder = new Order();
        ArrayList<Order> orderList = transaction.getOrderList();
        refund.getOrder(command, transaction);
        return false;
    }
}
