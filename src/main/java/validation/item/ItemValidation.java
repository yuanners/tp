package validation.item;

import validation.Validation;

public class ItemValidation extends Validation {
    public final String SHORT_NAME_FLAG = "n";
    public final String LONG_NAME_FLAG = "name";
    public final String SHORT_PRICE_FLAG = "p";
    public final String LONG_PRICE_FLAG = "price";
    public final String SHORT_INDEX_FLAG = "i";
    public final String LONG_INDEX_FLAG = "index";

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
            Integer.parseInt(input);
        } catch (NumberFormatException n) {
            return 1;
        }
        return 0;
    }

}
