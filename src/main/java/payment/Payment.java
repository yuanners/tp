package payment;

import app.Command;
import exception.OrderException;
import order.Order;
import utility.Ui;
import validation.order.PaymentValidation;

import java.util.Scanner;


public class Payment {
    private Ui ui = new Ui();

    public Payment() {
    }

    public void makePayment(Order order) throws OrderException {
        boolean isValidPayment = false;
        Scanner sc = new Scanner(System.in);
        ui.promptPayment();
        while (!isValidPayment) {
            String userInput = sc.nextLine();
            Command arg = new Command(userInput);
            PaymentValidation paymentValidation = new PaymentValidation(arg);
            try {
                paymentValidation.validatePayment(arg, order);
                isValidPayment = true;
                arg.mapArgumentAlias("a", "amount");
                arg.mapArgumentAlias("t", "type");
                order.setPaymentType(arg.getArgumentMap().get("t").trim());
                double amount = Double.parseDouble(arg.getArgumentMap().get("a").trim());
                ui.printChangeGiven(calculateChange(amount, order));
                ui.printCommandSuccess(arg.getCommand());
            } catch (OrderException o) {
                ui.println(o.getMessage());
                ui.promptPayment();
            }
        }
    }

    public double calculateChange(Double amount, Order order) {
        return amount - order.getSubTotal();
    }

}
