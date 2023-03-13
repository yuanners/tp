package validation.item;

import app.Command;
import item.Menu;
import utility.Ui;

public class DeleteItemValidation extends ItemValidation {
    private Ui ui = new Ui();

    public boolean isValidFormat(Command c) {
        String args = c.getArgumentString();

        if (!(args.contains("i") || args.contains("index"))) {
            ui.println(ui.INVALID_DELETEITEM_FORMAT);
            return false;
        }

        return true;
    }
}
