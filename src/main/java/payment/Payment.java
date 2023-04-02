package payment;

import app.Command;
import item.MenuAssistant;
import order.Order;
import ui.TransactionUi;
import validation.order.PaymentValidation;

import java.util.Scanner;


public class Payment {
    private TransactionUi transactionUi = new TransactionUi();

    public Payment() {
    }

    /**
     * Display prompt to pay immediately after an order is added
     *
     * @param order list of order entries added
     */
    public void makePayment(Order order) {
        boolean isValidPayment = false;
        transactionUi.promptPayment();

        while (!isValidPayment) {
            String userInput = transactionUi.inputHandler();
            Command arg = new Command(userInput);

            if (userInput.equalsIgnoreCase("pay")) {
                PaymentAssistant paymentAssistant = new PaymentAssistant();
                MenuAssistant menuAssistant = new MenuAssistant();
                Command pay = new Command("addorder");
                boolean isCancelled = paymentAssistant.makePayment(order);
                menuAssistant.printResult(pay, isCancelled);
                break;

            } else {
                PaymentValidation paymentValidation = new PaymentValidation();
                isValidPayment = paymentValidation.validatePayment(arg, order);

                if (!isValidPayment) {
                    transactionUi.promptUserInput();
                    continue;
                }

                arg.mapArgumentAlias("a", "amount");
                arg.mapArgumentAlias("t", "type");
                order.setPaymentType(arg.getArgumentMap().get("t").trim());
                order.setStatus("COMPLETED");
                double amount = Double.parseDouble(arg.getArgumentMap().get("a").trim());
                if (amount != order.getSubTotal()) {
                    transactionUi.printChangeGiven(calculateChange(amount, order));
                }
                transactionUi.printSuccessfulPayment();
            }

        }
    }

    /**
     * Compute the change to be given
     *
     * @param amount amount paid
     * @param order  list of order entries
     * @return change to give
     */
    public double calculateChange(Double amount, Order order) {
        return amount - order.getSubTotal();
    }

}
