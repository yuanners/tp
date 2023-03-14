package validation.order;

import app.Command;
import exception.InvalidArgumentException;
import exception.InvalidFlagException;
import item.Menu;
import utility.Ui;

public class AddMultipleOrderValidation extends OrderValidation{
    private Menu items = new Menu();
    private Ui ui = new Ui();
    public AddMultipleOrderValidation(){

    }
    public boolean validateAddMultipleOrder(Command arg) throws InvalidArgumentException, InvalidFlagException {
        boolean isCorrectFormat = isValidFlag(arg) && isArgumentPresent(arg);
        boolean isValid = false;
        if(isCorrectFormat){
            String input = arg.getUserInput();
            String regex = "^addorder\\s+-I\\s+\\[\\d+\\s+\\d+(,\\s+\\d+\\s+\\d+)*\\]$";
            if(input.matches(regex)) {
                isValid = true;
            }
            regex = "^addorder\\s+-I\\s+\\[\\d+\\s+\\d+\\]$";
            if (input.matches(regex)) {
                isValid = true;
            }
        }
        return isValid;
    }

}
