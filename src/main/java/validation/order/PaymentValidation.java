package validation.order;

import app.Command;
import exception.DuplicateArgumentFoundException;
import exception.order.MissingPayCommandException;
import exception.order.MissingPayTypeFlagException;
import exception.order.MissingPayTypeArgumentException;
import exception.order.MissingPayAmountFlagException;
import exception.order.MissingPayAmountArgumentException;
import exception.order.InsufficientPayAmountException;
import exception.order.InvalidPayAmountDecimalPlaceException;
import exception.order.InvalidPayAmountNegativeException;
import exception.order.InvalidPayAmountFormatException;
import exception.order.InvalidPaymentAmountForCardException;
import exception.order.InvalidPayTypeException;
import order.Order;
import ui.Flags;
import ui.TransactionUi;
import validation.Validation;


public class PaymentValidation extends Validation {
    private TransactionUi transactionUi = new TransactionUi();

    /**
     * Map the argument for convenience when the class is initialised
     */
    public PaymentValidation() {
    }

    /**
     * Loop the try-catch block until the /pay command is validated
     *
     * @param arg   user input
     * @param order list of order entries
     */
    public boolean validatePayment(Command arg, Order order) throws DuplicateArgumentFoundException  {
        boolean isValid = false;
        try {
            validateCommand(arg);
            validateFlag(arg);
            validateType(arg);
            validateAmount(arg, order);
            isValid = true;
        } catch (MissingPayCommandException e) {
            transactionUi.printError(Flags.Error.MISSING_PAY_COMMAND);
        } catch (MissingPayTypeFlagException e) {
            transactionUi.printError(Flags.Error.MISSING_PAY_TYPE_FLAG);
        } catch (MissingPayTypeArgumentException e) {
            transactionUi.printError(Flags.Error.MISSING_PAY_TYPE_ARGUMENT);
        } catch (MissingPayAmountFlagException e) {
            transactionUi.printError(Flags.Error.MISSING_PAY_AMOUNT_FLAG);
        } catch (MissingPayAmountArgumentException e) {
            transactionUi.printError(Flags.Error.MISSING_PAY_AMOUNT_ARGUMENT);
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
        } catch (InvalidPayTypeException e) {
            transactionUi.printError(Flags.Error.INVALID_PAY_TYPE);
        }
        return isValid;


    }

    /**
     * Check if the user input contains the required command
     *
     * @param arg user input
     * @throws MissingPayCommandException user input does not contain /pay command
     */
    public void validateCommand(Command arg) throws MissingPayCommandException {
        if (!(arg.getUserInput().contains("/pay"))) {
            throw new MissingPayCommandException();
        }
    }

    /**
     * Check if the required flags and argument are present in the user input
     * arg user input command
     *
     * @throws MissingPayTypeFlagException       missing type flag
     * @throws MissingPayTypeArgumentException   missing type argument
     * @throws MissingPayAmountFlagException     missing amount flag
     * @throws MissingPayAmountArgumentException missing amount argument
     */
    public void validateFlag(Command arg) throws MissingPayTypeFlagException, MissingPayTypeArgumentException,
            MissingPayAmountFlagException, MissingPayAmountArgumentException, DuplicateArgumentFoundException {
        arg.mapArgumentAlias("a", "amount");
        arg.mapArgumentAlias("t", "type");
        if (arg.getArgumentString().contains("-t")) {
            if (arg.getArgumentMap().get("t") == null) {
                throw new MissingPayTypeArgumentException();
            }
        } else {
            throw new MissingPayTypeFlagException();
        }

        if (arg.getArgumentString().contains("-a")) {
            if (arg.getArgumentMap().get("a") == null) {
                throw new MissingPayAmountArgumentException();
            }
        } else {
            throw new MissingPayAmountFlagException();
        }

    }

    /**
     * Check if the user input type is one the these: cash/card/others
     *
     * @param arg user input command
     * @throws InvalidPayTypeException type is not one of these: cash/card/others
     */
    public void validateType(Command arg) throws InvalidPayTypeException, DuplicateArgumentFoundException {
        arg.mapArgumentAlias("t", "type");
        String type = "";
        if (arg.getArgumentMap().get("t") != null) {
            type = arg.getArgumentMap().get("t").trim();
        } else {
            type = arg.getUserInput();
        }
        if (type.equalsIgnoreCase("cash") || type.equalsIgnoreCase("card")
                || type.equalsIgnoreCase("others")) {

        } else {
            throw new InvalidPayTypeException();
        }
    }

    /**
     * Check the user input amount is a double with 2 d.p., more than or equals to the amount to be paid
     *
     * @param arg   user input
     * @param order list of order entries
     * @throws InvalidPayAmountFormatException       amount is not double
     * @throws InvalidPayAmountNegativeException     amount is negative
     * @throws InsufficientPayAmountException        amount is less than order subtotal
     * @throws InvalidPayAmountDecimalPlaceException amount has more than 2 d.p.
     * @throws InvalidPaymentAmountForCardException  amount is not exact for card payment
     */
    public void validateAmount(Command arg, Order order) throws InvalidPayAmountFormatException,
            InvalidPayAmountNegativeException, InsufficientPayAmountException,
            InvalidPayAmountDecimalPlaceException, InvalidPaymentAmountForCardException,
            DuplicateArgumentFoundException {
        arg.mapArgumentAlias("a", "amount");
        String amount = "";
        if (arg.getArgumentMap().get("a") != null) {
            amount = arg.getArgumentMap().get("a").trim();
        } else {
            amount = arg.getUserInput();
        }
        if (!isDouble(amount)) {
            throw new InvalidPayAmountFormatException();
        } else {
            Double amountPaid = Double.parseDouble(amount);

            if (amountPaid <= 0.00) {
                throw new InvalidPayAmountNegativeException();
            }
            if (amountPaid < order.getSubTotal()) {
                throw new InsufficientPayAmountException();
            }
            if (amount.contains(".")) {
                int decimalPoint = amount.length() - amount.indexOf('.') - 1;

                if (decimalPoint > 2) {
                    throw new InvalidPayAmountDecimalPlaceException();
                }
            }
            if (arg.getArgumentMap().get("a") != null) {
                if (arg.getArgumentMap().get("t").equalsIgnoreCase("card")) {
                    if (amountPaid != order.getSubTotal()) {
                        throw new InvalidPaymentAmountForCardException();
                    }
                }
            }
        }
    }

    /**
     * Check if the user input is a double
     *
     * @param input user input
     * @return validation outcome(true/false)
     */
    public boolean isDouble(String input) {
        try {
            Double.valueOf(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
