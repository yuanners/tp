package validation.order;

import app.Command;
import exception.OrderException;
import item.Menu;
import utility.Ui;

public class AddMultipleAddOrderValidation extends AddOrderValidation {
    private Menu items = new Menu();
    private Ui ui = new Ui();

    public AddMultipleAddOrderValidation() {

    }

    public void validateAddMultipleOrder(Command arg) throws OrderException {
        String input = arg.getUserInput();
        String regex = "^addorder\\s+-I\\s+\\[((\\d+:\\d+)(,\\s*(\\d+:\\d+))*)?\\]$";

        if(input.matches(regex)) {
            String orderPairsString = input.replaceAll("[^\\d\\s,:]", "");
            String[] orderPairs;
            if(orderPairsString.contains(",")) {
                orderPairs = orderPairsString.split(",\\s*");
            } else {
                orderPairs = new String[]{orderPairsString};
            }

            for(String order : orderPairs) {
                String[] numbers = order.trim().split(":");
                if(numbers.length == 2 && isInteger(numbers[0]) && isInteger(numbers[1])) {
                    String itemIndex = numbers[0];
                    if(!(isValidIndex(itemIndex, items))) {
                        throw new OrderException(ui.getInvalidIndex());
                    }
                } else {
                    throw new OrderException(ui.getInvalidMultipleOrderInteger());
                }
            }
        } else {
            throw new OrderException(ui.getInvalidMultipleOrderFormat());
        }
    }

}
