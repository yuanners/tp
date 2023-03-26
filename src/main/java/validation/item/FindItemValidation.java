package validation.item;

import exception.item.MissingFindItemDescriptionException;

public class FindItemValidation extends ItemValidation {

    public void validateName(String input) throws MissingFindItemDescriptionException {
        if(input == null || input == "") {
            throw new MissingFindItemDescriptionException();
        }
    }

}
