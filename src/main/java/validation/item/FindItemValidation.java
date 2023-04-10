package validation.item;

import exception.item.MissingFindItemDescriptionException;
import exception.item.NameMinimumLengthException;

public class FindItemValidation extends ItemValidation {

    public void validateName(String input) throws MissingFindItemDescriptionException, NameMinimumLengthException {
        if(input == null || input == "") {
            throw new MissingFindItemDescriptionException();
        }

        if (input.trim().length() < 1) {
            throw new NameMinimumLengthException();
        }
    }

}
