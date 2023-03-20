package validation.order;

import app.Command;
import exception.OrderException;
import order.Order;
import utility.Ui;
import validation.Validation;

public class PaymentValidation extends Validation {
    private Ui ui = new Ui();

    /**
     * Map the argument for convenience when the class is initialised
     *
     * @param arg user input command
     */
    public PaymentValidation(Command arg) {
        arg.mapArgumentAlias("a", "amount");
        arg.mapArgumentAlias("t", "type");
    }

    /**
     * Loop the try-catch block until the /pay command is validated
     *
     * @param arg   user input command
     * @param order list of order entries added
     * @throws OrderException custom exception for order validation
     */
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

    /**
     * Check if the user input contains the required command
     *
     * @param arg user input
     * @throws OrderException custom exception for order validation
     */
    public void checkCommand(Command arg) throws OrderException {
        if (!(arg.getUserInput().contains("/pay"))) {
            throw new OrderException(ui.printInvalidPayment());
        }
    }

    /**
     * Check if the required flags are present in the user input
     *
     * @param arg user input command
     * @throws OrderException custom exception for order validation
     */
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

    /**
     * Check if the user input type is one the these: cash/card/others
     *
     * @param arg user input command
     * @throws OrderException custom exception for order validation
     */
    public void checkType(Command arg) throws OrderException {
        String type = arg.getArgumentMap().get("t").trim();
        if (type.equalsIgnoreCase("cash") || type.equalsIgnoreCase("card")
                || type.equalsIgnoreCase("others")) {

        } else {
            throw new OrderException(ui.printInvalidPaymentType());
        }
    }

    /**
     * Check the user input amount is a double with 2 d.p., more than or equals to the amount to be paid
     *
     * @param arg   user input argument
     * @param order list of order entries
     * @throws OrderException custom exception for order validation
     */
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
