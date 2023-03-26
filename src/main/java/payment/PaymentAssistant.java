package payment;

import app.Command;
import exception.order.InsufficientPayAmountException;
import exception.order.InvalidPayAmountDecimalPlaceException;
import exception.order.InvalidPayAmountFormatException;
import exception.order.InvalidPayAmountNegativeException;
import exception.order.InvalidPaymentAmountForCardException;
import exception.order.InvalidPayTypeException;
import order.Order;
import ui.Flags;
import ui.TransactionUi;
import validation.order.PaymentValidation;
import java.util.Scanner;

public class PaymentAssistant {
    private final String CANCEL = "/cancel";
    private TransactionUi transactionUi = new TransactionUi();
    private Scanner scan = new Scanner(System.in);
    Payment payment = new Payment();
    private String amount;
    private String type;
    private PaymentValidation paymentValidation = new PaymentValidation();

    public boolean getAmount(Command command, Order order) {
        boolean isValidAmount = false;
        while (!isValidAmount) {
            transactionUi.promptPaymentAmount();
            String input = scan.nextLine();
            Command arg = new Command(input);
            if (input.equalsIgnoreCase(CANCEL)) {
                return true;
            }
            try {
                paymentValidation.validateAmount(arg, order);
                amount = input;
                isValidAmount = true;
            } catch (InsufficientPayAmountException e) {
                transactionUi.printError(Flags.Error.INSUFFICIENT_PAY_AMOUNT);
            } catch (InvalidPayAmountDecimalPlaceException e) {
                transactionUi.printError(Flags.Error.INVALID_PAY_AMOUNT_DECIMAL_PLACE);
            } catch (InvalidPayAmountNegativeException e) {
                transactionUi.printError(Flags.Error.INVALID_PAY_AMOUNT_NEGATIVE);
            } catch (InvalidPayAmountFormatException e) {
                transactionUi.printError(Flags.Error.INVALID_PAY_AMOUNT_FORMAT);
            } catch (InvalidPaymentAmountForCardException e) {
                transactionUi.printError(Flags.Error.INVALID_PAYMENT_AMOUNT_FOR_CARD);
            }
        }
        return false;
    }

    public boolean getType(Command command, Order order) {
        boolean isValidType = false;
        while (!isValidType) {
            transactionUi.promptPaymentType();
            String input = scan.nextLine();
            Command arg = new Command(input);
            if (input.equalsIgnoreCase(CANCEL)) {
                return true;
            }
            try {
                paymentValidation.validateType(arg);
                type = input;
                isValidType = true;
            } catch (InvalidPayTypeException e) {
                transactionUi.printError(Flags.Error.INVALID_PAY_TYPE);
            }
        }
        return false;
    }

    public boolean makePayment(Command command, Order order) {
        boolean isCancelled = false;
        isCancelled = getAmount(command, order);

        if(isCancelled) {
            return true;
        }

        isCancelled = getType(command, order);

        if(isCancelled) {
            return true;
        }
        Payment payment = new Payment();
        command.mapArgumentAlias("a", "amount");
        command.mapArgumentAlias("t", "type");
        order.setPaymentType(type);
        order.setStatus("COMPLETED");
        double amountToPay = Double.parseDouble(amount);
        if (amountToPay != order.getSubTotal()) {
            transactionUi.printChangeGiven(payment.calculateChange(amountToPay, order));
        }
        return false;
    }
}
