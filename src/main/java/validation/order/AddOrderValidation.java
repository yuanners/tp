package validation.order;

import app.Command;
import exception.InvalidArgumentException;
import exception.InvalidFlagException;
import item.Menu;
import utility.Ui;

public class AddOrderValidation extends OrderValidation{
    private Menu items = new Menu();
    private Ui ui = new Ui();
    public AddOrderValidation(){

    }
    public boolean validateAddOrder(Command arg) throws InvalidArgumentException, InvalidFlagException {
        boolean isValid = false;
        if(isValidFlagArgument(arg)){
            String itemIndex = arg.getArgumentMap().get("item").trim();
            if(isValidIndex(itemIndex, items)) {
                isValid = true;
            }
        }
        return isValid;
    }

}
