package payment;

import app.Command;
import exception.item.IndexInvalidNumberFormatException;
import exception.item.IndexOutOfBoundException;
import exception.item.IndexOverflowException;
import item.Menu;
import order.Transaction;
import ui.Flags;
import ui.MenuUi;
import ui.TransactionUi;
import utility.Ui;
import validation.item.AddItemValidation;
import validation.item.DeleteItemValidation;
import validation.order.RefundOrderValidation;

import java.util.Scanner;

public class RefundAssistant {
    private TransactionUi transactionUi;
    private Scanner scan;
    private RefundOrderValidation refundOrderValidation;
    private final String CANCEL = "/cancel";
    private final String YES = "yes";
    private final String NO = "no";
    /*
    public RefundAssistant() {
        transactionUi = new TransactionUi();
        scan = new Scanner(System.in);
        refundOrderValidation = new RefundOrderValidation();
    }

    private boolean getID(Command command, Transaction transaction) {
        boolean isValidID = false;

        while (!isValidID) {
            transactionUi.promptOrderID();
            index = sc.nextLine();

            if(index.equals(CANCEL)) {
                return true;
            }
        }
        return false;
    }

*/
}
