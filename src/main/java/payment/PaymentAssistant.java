package payment;

import app.Command;
import exception.DuplicateArgumentFoundException;
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

public class PaymentAssistant {
    private String CANCEL = "/cancel";
    private String amount;
    private String type;
    private TransactionUi transactionUi = new TransactionUi();
    private PaymentValidation paymentValidation = new PaymentValidation();

    /**
     * Get the payment amount from user and validate it
     * @param order order to pay
     * @return whether the user entered "/cancel"
     */
    public boolean getAmount(Order order) throws DuplicateArgumentFoundException  {
        boolean isValidAmount = false;
        while (!isValidAmount) {
            transactionUi.promptPaymentAmount();
            String input = transactionUi.inputHandler();
            Command arg = new Command(input);
            if (input.equalsIgnoreCase(CANCEL)) {
                return true;
            }
            try {
                paymentValidation.validateAmount(arg, order);
                amount = input;
                checkAmount(order);
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

    /**
     * Get payment type from user and validate it
     * @return whether user entered "/cancel"
     */
    public boolean getType() throws DuplicateArgumentFoundException {
        boolean isValidType = false;
        while (!isValidType) {
            transactionUi.promptPaymentType();
            String input = transactionUi.inputHandler();
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

    /**
     * Check if the payment amount is exact if paying by card
     * @param order order to pay
     * @throws InvalidPaymentAmountForCardException amount not exact when paying by card
     */
    public void checkAmount(Order order) throws InvalidPaymentAmountForCardException {
        double amountToPay = Double.parseDouble(amount);
        if (type.equalsIgnoreCase("card") && amountToPay != order.getSubTotal()) {
            throw new InvalidPaymentAmountForCardException();
        }
    }

    /**
     * Set the order status and payment type, calculate change if amount is not exact
     * @param order order to pay
     * @return whether user entered "/cancel"
     */
    public boolean makePayment(Order order) throws DuplicateArgumentFoundException  {
        boolean isCancelled = false;
        isCancelled = getType();

        if (isCancelled) {
            order.setStatus("CANCELED");
            return true;
        }

        isCancelled = getAmount(order);

        if (isCancelled) {
            order.setStatus("CANCELED");
            return true;
        }

        Payment payment = new Payment();
        order.setPaymentType(type);
        order.setStatus("COMPLETED");
        double amountToPay = Double.parseDouble(amount);
        if (amountToPay != order.getSubTotal()) {
            transactionUi.printChangeGiven(payment.calculateChange(amountToPay, order));
        }
        transactionUi.printSuccessfulPayment();
        return false;
    }
}
