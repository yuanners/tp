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
    public void validateAddOrder(Command arg) throws InvalidArgumentException, InvalidFlagException {

        try{
            checkValidFlagArgument(arg);
            String itemIndex = arg.getArgumentMap().get("item").trim();
            if(!isValidIndex(itemIndex, items)) {
                throw new InvalidArgumentException(ui.INVALID_INDEX);
            }
        } catch(InvalidArgumentException a) {
            throw new InvalidArgumentException(a.getMessage());
        }

    }

}
