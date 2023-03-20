package validation.order;

import app.Command;
import exception.OrderException;
import order.Order;
import utility.Ui;
import validation.Validation;

public class PaymentValidation extends Validation {
    private Ui ui = new Ui();

    public PaymentValidation(Command arg) {
        arg.mapArgumentAlias("a", "amount");
        arg.mapArgumentAlias("t", "type");
    }

    public void validatePayment(Command arg, Order order) throws OrderException {
        boolean isValid = false;
        while (!isValid) {
            try {
                checkCommand(arg);
                checkFlag(arg);
                checkAmount(arg, order);
                checkType(arg);
                isValid = true;
            } catch (OrderException o) {
                throw new OrderException(o.getMessage());
            }
        }

    }

    public void checkCommand(Command arg) throws OrderException {
        if (!(arg.getUserInput().contains("/pay"))) {
            throw new OrderException(ui.printInvalidPayment());
        }
    }

    public void checkFlag(Command arg) throws OrderException {
        if (arg.getArgumentString().contains("-t") || arg.getArgumentString().contains("-a")
                || arg.getArgumentString().contains("--type") || arg.getArgumentString().contains("--amount")) {
            if (arg.getArgumentMap().get("a") != null && arg.getArgumentMap().get("t") != null) {

            } else {
                throw new OrderException(ui.printMissingPaymentArgument());
            }
        } else {
            throw new OrderException(ui.printMissingPaymentFlag());
        }
    }

    public void checkType(Command arg) throws OrderException {
        String type = arg.getArgumentMap().get("t").trim();
        if (type.equalsIgnoreCase("cash") || type.equalsIgnoreCase("card")
                || type.equalsIgnoreCase("others")) {

        } else {
            throw new OrderException(ui.printInvalidPaymentType());
        }
    }

    public void checkAmount(Command arg, Order order) throws OrderException {
        String amount = arg.getArgumentMap().get("a").trim();
        if (!isDouble(amount)) {
            throw new OrderException(ui.printInvalidPaymentAmount());
        } else {
            Double amountPaid = Double.parseDouble(amount);

            if (amountPaid < 0.00) {
                throw new OrderException(ui.printInvalidPaymentAmount());
            }
            if (amountPaid < order.getSubTotal()) {
                throw new OrderException(ui.printInsufficientAmount());
            }
            if (amount.contains(".")) {
                int decimalPoint = amount.length() - amount.indexOf('.') - 1;

                if (decimalPoint > 2) {
                    throw new OrderException(ui.printInvalidPaymentAmount());
                }
            }

        }
    }

    public boolean isDouble(String input) {
        try {
            Double.valueOf(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
