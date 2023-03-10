package validation.item;

import app.Command;
import item.ItemList;
import utility.Ui;

public class DeleteItemValidation extends ItemValidation {

    public boolean isValidIndex(String input, ItemList items) {
        Ui ui = new Ui();
        try {
            items.getItem(Integer.parseInt(input));
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidIndex();
            return false;
        }
        return true;
    }

    public boolean isValidFormat(Command c) {
        Ui ui = new Ui();

        String args = c.getArgumentString();

        if (!(args.contains("i") || args.contains("index"))) {
            ui.println(ui.INVALID_DELETEITEM_FORMAT);
            return false;
        }

        return true;
    }
}
