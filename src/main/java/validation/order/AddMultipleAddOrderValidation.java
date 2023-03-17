package validation.order;

import app.Command;
import exception.OrderException;
import item.Menu;
import utility.Ui;

public class AddMultipleAddOrderValidation extends AddOrderValidation {
    private Menu menu = new Menu();
    private Ui ui = new Ui();

    public AddMultipleAddOrderValidation() {

    }

    public Command validateAddMultipleOrder(Command arg) throws OrderException {

        String input = arg.getUserInput();
        String regex = "^addorder\\s+-I\\s+\\[((\\d+:\\d+)|(\"[^\"]+\":\\d+)|([a-zA-Z]+\\d*:\\d+))" +
                "(,((\\d+:\\d+)|(\"[^\"]+\":\\d+)|([a-zA-Z]+\\d*:\\d+)))*\\]$";

        if (input.matches(regex)) {
            return validateAddMultipleOrder2(input);
        } else {
            throw new OrderException(ui.getInvalidMultipleOrderFormat());
        }

    }

    private Command validateAddMultipleOrder2(String input) throws OrderException {

        String orderPairsString = input.substring(13);
        orderPairsString = orderPairsString.substring(0, orderPairsString.length() - 1);

        String[] orderPairs;

        if (orderPairsString.contains(",")) {
            orderPairs = orderPairsString.split(",\\s*");
        } else {
            orderPairs = new String[]{orderPairsString};
        }

        return validateAddMultipleOrder3(orderPairs);

    }

    private Command validateAddMultipleOrder3(String[] orderPairs) throws OrderException {

        String index;
        String finalCommandString = "";

        for (String order : orderPairs) {

            String[] elements = order.trim().split(":");



            if (!isInteger(elements[0])) {
                index = Integer.toString(menu.findItemIndex(elements[0], menu.getItems()));
                elements[0] = index;
            }

            if (elements.length == 2 && isInteger(elements[0]) && isInteger(elements[1])) {

                String itemIndex = elements[0];

                if (!(isValidIndex(itemIndex, menu))) {
                    throw new OrderException(ui.getInvalidIndex());
                }

                finalCommandString += elements[0] + ":" + elements[1] + ",";

            } else {
                throw new OrderException(ui.getInvalidMultipleOrderInteger());
            }

        }

        finalCommandString = finalCommandString.substring(0, finalCommandString.length() - 1);

        assert true : "This is true";

        return new Command("addorder -I [" + finalCommandString + "]");

    }

}
