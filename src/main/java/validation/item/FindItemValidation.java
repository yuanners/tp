package validation.item;

import app.Command;
import exception.ItemException;

public class FindItemValidation extends ItemValidation {

    public boolean validateName(String input) throws ItemException {
        if(input == null || input == "") {
            throw new ItemException(ui.getItemNameMinLengthError());
        }
        return true;
    }

}
