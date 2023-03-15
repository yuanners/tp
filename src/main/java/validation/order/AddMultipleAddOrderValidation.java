package validation.order;

import item.Menu;
import utility.Ui;

public class AddMultipleAddOrderValidation extends AddOrderValidation {
    private Menu items = new Menu();
    private Ui ui = new Ui();
    public AddMultipleAddOrderValidation(){

    }
    /*
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
*/
}
