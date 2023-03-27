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

    public AddMultipleAddOrderValidation(Menu menu) {
        this.menu = menu;
    }

    public Command validateFormat(Command arg) throws MissingMultipleOrderArgumentException,
            MissingMultpleOrderFlagException, InvalidMultipleOrderFormatException,
            InvalidQuantityNumberFormatException, InvalidIndexOutOfBoundsException {

        String input = arg.getUserInput();
        String regex = "\\/addorder\\s*-I\\s*\\[((\\d+:\\d+)|([^\"]+:\\d+)|([a-zA-Z]+\\d*:\\d+))" +
                "(,((\\d+:\\d+)|([^\"]+:\\d+)|([a-zA-Z]+\\d*:\\d+)))*\\]$";
        String regex2 = "\\/addorder\\s*--items\\s*\\[((\\d+:\\d+)|([^\"]+:\\d+)|([a-zA-Z]+\\d*:\\d+))" +
                "(,((\\d+:\\d+)|([^\"]+:\\d+)|([a-zA-Z]+\\d*:\\d+)))*\\]$";
        String regex3 = "\\/addorder\\s*-I\\s*\\[((\\d+:\\d+)|(\"[^\"]+\":\\d+)|([a-zA-Z]+\\d*:\\d+))" +
                "(,((\\d+:\\d+)|(\"[^\"]+\":\\d+)|([a-zA-Z]+\\d*:\\d+)))*\\]$";

        System.out.println("Input: " + input);

        if (!input.matches(regex) && !input.matches(regex2) && !input.matches(regex3)) {

            if (arg.getArgumentString().contains("-I") || arg.getArgumentString().contains("--items")) {

                String itemsArg = arg.getArgumentMap().get("I");

                if (itemsArg.isEmpty()) {
                    itemsArg = arg.getArgumentMap().get("items");
                }

                if (itemsArg == null || itemsArg.isEmpty()) {
                    throw new MissingMultipleOrderArgumentException();
                } else {
                    throw new InvalidMultipleOrderFormatException();
                }

            } else {
                throw new MissingMultpleOrderFlagException();
            }
        } else {
            return splitMultipleOrdersIntoArrayList(input);
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
            orderPairs = new String[]{orderPairsString};
        }

        for (String order : orderPairs) {
            String[] numbers = order.trim().split(":");

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

    private Command splitMultipleOrdersIntoArrayList(String input)
            throws InvalidQuantityNumberFormatException, InvalidIndexOutOfBoundsException,
            InvalidMultipleOrderFormatException {

        int startOfArgumentsIndex;
        int startOfArgumentsIndexWhenShortFlagUsed = 14;
        int startOfArgumentsIndexWhenLongFlagUsed = 19;

        if (input.contains("-I")) {
            startOfArgumentsIndex = startOfArgumentsIndexWhenShortFlagUsed;
        } else {
            startOfArgumentsIndex = startOfArgumentsIndexWhenLongFlagUsed;
        }

        String orderPairsString = input.substring(startOfArgumentsIndex);
        orderPairsString = orderPairsString.substring(0, orderPairsString.length() - 1);

        String[] orderPairs;

        if (orderPairsString.contains(",")) {
            orderPairs = orderPairsString.split(",\\s*");
        } else {
            orderPairs = new String[]{orderPairsString};
        }

        return castIntoProCommandFormat(orderPairs);

    }

    private Command castIntoProCommandFormat(String[] orderPairs)
            throws InvalidIndexOutOfBoundsException, InvalidQuantityNumberFormatException,
            InvalidMultipleOrderFormatException {

        String index;
        String finalCommandString = "";

        for (String order : orderPairs) {

            String[] elements = order.trim().split(":");

            if (elements[0].contains("\"")) {
                elements[0] = elements[0].replace("\"", "");
            }

            if (!isInteger(elements[0])) {
                index = Integer.toString(menu.findItemIndex(elements[0]));
                elements[0] = index;
            }

            if (elements.length == 2 && isInteger(elements[0]) && isInteger(elements[1])) {

                String itemIndex = elements[0];

                if (!(isValidIndex(itemIndex, menu))) {
                    throw new InvalidIndexOutOfBoundsException();
                }

                if (Integer.parseInt(elements[1]) <= 0) {
                    throw new InvalidQuantityNumberFormatException();
                }

                finalCommandString += elements[0] + ":" + elements[1] + ",";

            } else {
                throw new InvalidMultipleOrderFormatException();
            }

        }

        finalCommandString = finalCommandString.substring(0, finalCommandString.length() - 1);

        assert true : "This is true";

        return new Command("/addorder -I [" + finalCommandString + "]");

    }

}
