package validation.item;

import utility.Ui;
import validation.Validation;

public class ItemValidation extends Validation {
    Ui ui = new Ui();

    /**
     * Checks if the given input is a double
     * @param input Given input
     * @return Validation result (true/false)
     */
    public boolean isDouble(String input) {
        try {
            Double.valueOf(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Check if the input after items and quantity flags are integer
     *
     * @param input the input after flags
     * @return 0 if String can be converted to an integer,
     *         1 if input contains non-numeric characters,
     *         2 if input is too large to be stored in an int
     */
    public int isInteger(String input) {
        try {
            if (!input.matches("^\\d+$")) {
                return 1;
            }
            Integer.parseInt(input);
        } catch (NumberFormatException n) {
            return 2;
        }
        return 0;
    }

}
