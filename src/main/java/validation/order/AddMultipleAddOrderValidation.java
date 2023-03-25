package validation.order;

import app.Command;
import exception.order.MissingMultipleOrderArgumentException;
import exception.order.MissingMultpleOrderFlagException;
import exception.order.InvalidMultipleOrderFormatException;
import exception.order.InvalidIndexNegativeException;
import exception.order.InvalidIndexNumberFormatException;
import exception.order.InvalidIndexOutOfBoundsException;
import exception.order.InvalidQuantityNumberFormatException;
import exception.order.InvalidQuantityNegativeException;
import item.Menu;

public class AddMultipleAddOrderValidation extends AddOrderValidation {
    private Menu menu;

    public AddMultipleAddOrderValidation() {
        menu = new Menu();
    }

    public void validateFormat(Command arg) throws MissingMultipleOrderArgumentException,
            MissingMultpleOrderFlagException, InvalidMultipleOrderFormatException {
        String input = arg.getUserInput();
        String regex = "^\\/addorder\\s*-I\\s*\\[((\\d+:\\d+)|(\"[^\"]+\":\\d+)|([a-zA-Z]+\\d*:\\d+))" +
                "(,((\\d+:\\d+)|(\"[^\"]+\":\\d+)|([a-zA-Z]+\\d*:\\d+)))*\\]$\n";
        if (!input.matches(regex)) {
            if (arg.getArgumentString().contains("-I") || arg.getArgumentString().contains("--items")) {
                if (arg.getArgumentMap().get("I").length() < 1 && arg.getArgumentMap().get("items").length() < 1) {
                    throw new MissingMultipleOrderArgumentException();
                } else {
                    throw new InvalidMultipleOrderFormatException();
                }
            } else {
                throw new MissingMultpleOrderFlagException();
            }
        } else {
            if (arg.getArgumentMap().get("I").length() < 1 && arg.getArgumentMap().get("items").length() < 1) {
                throw new MissingMultipleOrderArgumentException();
            }
        }
    }

    public void validateArguments(Command arg, Menu items) throws InvalidIndexNegativeException,
            InvalidIndexNumberFormatException, InvalidIndexOutOfBoundsException,
            InvalidQuantityNumberFormatException, InvalidQuantityNegativeException {
        String input = arg.getUserInput();
        String orderPairsString = input.replaceAll("[^\\d\\s,:]", "");
        String[] orderPairs;
        if (orderPairsString.contains(",")) {
            orderPairs = orderPairsString.split(",\\s*");
        } else {
            orderPairs = new String[] {orderPairsString};
        }
        for (String order : orderPairs) {
            String[] numbers = order.trim().split("\\s+");
            if (isInteger(numbers[0])) {
                if (Integer.parseInt(numbers[0]) < 0) {
                    throw new InvalidIndexNegativeException();
                }
                if (!isValidIndex(numbers[0], items)) {
                    throw new InvalidIndexOutOfBoundsException();
                }
            } else {
                throw new InvalidIndexNumberFormatException();
            }
            if (isInteger(numbers[1])) {
                if (Integer.parseInt(numbers[1]) <= 0) {
                    throw new InvalidQuantityNegativeException();
                }
            } else {
                throw new InvalidQuantityNumberFormatException();
            }
        }
    }

    /*
        public Command validateAddMultipleOrder(Command arg) throws OrderException {

            String input = arg.getUserInput();
            String regex = "^\\/addorder\\s+-I\\s+\\[((\\d+:\\d+)|(\"[^\"]+\":\\d+)|([a-zA-Z]+\\d*:\\d+))" +
                    "(,((\\d+:\\d+)|(\"[^\"]+\":\\d+)|([a-zA-Z]+\\d*:\\d+)))*\\]$";


            if (input.matches(regex)) {
                return validateAddMultipleOrder2(input);
            } else {
                throw new OrderException(ui.getInvalidMultipleOrderFormat());
            }

        }

        private Command validateAddMultipleOrder2(String input) throws OrderException {
            int startOfArgumentsIndex = 14;
            String orderPairsString = input.substring(startOfArgumentsIndex);
            orderPairsString = orderPairsString.substring(0, orderPairsString.length() - 1);

            String[] orderPairs;

            if (orderPairsString.contains(",")) {
                orderPairs = orderPairsString.split(",\\s*");
            } else {
                orderPairs = new String[] {orderPairsString};
            }

            return validateAddMultipleOrder3(orderPairs);

        }

        private Command validateAddMultipleOrder3(String[] orderPairs) throws OrderException {

            String index;
            String finalCommandString = "";

            for (String order : orderPairs) {

                String[] elements = order.trim().split(":");


                if (!isInteger(elements[0])) {
                    index = Integer.toString(menu.findItemIndex(elements[0]));
                    elements[0] = index;
                }

                if (elements.length == 2 && isInteger(elements[0]) && isInteger(elements[1])) {

                    String itemIndex = elements[0];

                    if (!(isValidIndex(itemIndex, menu))) {
                        throw new OrderException(ui.getInvalidIndex());
                    }

                    if (Integer.parseInt(elements[1]) <= 0) {
                        throw new OrderException(ui.getInvalidOrderInteger());
                    }

                    finalCommandString += elements[0] + ":" + elements[1] + ",";

                } else {
                    throw new OrderException(ui.getInvalidMultipleOrderInteger());
                }

            }

            finalCommandString = finalCommandString.substring(0, finalCommandString.length() - 1);

            assert true : "This is true";

            return new Command("/addorder -I [" + finalCommandString + "]");

        }
    */
}
