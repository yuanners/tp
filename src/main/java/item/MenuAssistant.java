package item;

import app.Command;
import exception.ItemException;
import utility.Ui;
import validation.item.AddItemValidation;
import validation.item.DeleteItemValidation;

import java.util.Scanner;

public class MenuAssistant {
    Ui ui;
    Scanner sc;
    AddItemValidation addItemValidation;
    DeleteItemValidation deleteItemValidation;
    public MenuAssistant() {
        ui = new Ui();
        sc = new Scanner(System.in);
        addItemValidation = new AddItemValidation();
        deleteItemValidation = new DeleteItemValidation();
    }

    public void printResult(Command command, boolean isCancelled) {
        if (isCancelled) {
            ui.printCommandCancelled(command.getCommand());
        } else {
            ui.printCommandSuccess(command.getCommand());
        }
    }

    private boolean getName(Command command, Menu menu) {
        String name = "";
        boolean isValidName = false;

        while (!isValidName) {
            ui.promptItemName();
            name = sc.nextLine();

            if(name.equals("/cancel")) {
                return true;
            }

            command.getArgumentMap().put(addItemValidation.LONG_NAME_FLAG, name);
            command.getArgumentMap().put(addItemValidation.SHORT_NAME_FLAG, name);

            try {
                addItemValidation.validateName(command, menu);
                isValidName = true;
            } catch(ItemException e) {
                ui.println(e.getMessage());
            }

        }

        return false;
    }

    private boolean getPrice(Command command, Menu menu) {
        String price = "";
        boolean isValidPrice = false;

        while (!isValidPrice) {
            ui.promptItemPrice();
            price = sc.nextLine();

            if(price.equals("/cancel")) {
                return true;
            }

            command.getArgumentMap().put(addItemValidation.LONG_PRICE_FLAG, price);
            command.getArgumentMap().put(addItemValidation.SHORT_PRICE_FLAG, price);

            try {
                addItemValidation.validatePrice(command);
                isValidPrice = true;
            } catch(ItemException e) {
                ui.println(e.getMessage());
            }

        }

        return false;
    }

    public boolean addItem(Command command, Menu menu) {
        boolean isCancelled = false;
        isCancelled = getName(command, menu);

        if(isCancelled) {
            return true;
        }

        isCancelled = getPrice(command, menu);

        if(isCancelled) {
            return true;
        }

        String name = command.getArgumentMap().get("name");
        Double price = Double.valueOf(command.getArgumentMap().get("price"));
        Item item = new Item(name, price);
        menu.appendItem(item);
        menu.save();
        return false;
    }

    private boolean getIndex(Command command, Menu menu) {
        String index = "";
        boolean isValidIndex = false;

        while (!isValidIndex) {
            ui.promptItemIndex();
            index = sc.nextLine();

            if(index.equals("/cancel")) {
                return true;
            }

            command.getArgumentMap().put(deleteItemValidation.LONG_INDEX_FLAG, index);
            command.getArgumentMap().put(deleteItemValidation.SHORT_INDEX_FLAG, index);

            try {
                deleteItemValidation.validateIndex(command, menu);
                isValidIndex = true;
            } catch(ItemException e) {
                ui.println(e.getMessage());
            }

        }
        return false;
    }
    public boolean deleteItem(Command command, Menu menu) {
        if(menu.getItems().size() == 0) {
            ui.println(ui.getEmptyMenu());
            return true;
        }

        boolean isCancelled = false;
        isCancelled = getIndex(command, menu);

        if(isCancelled) {
            return true;
        }

        int index = Integer.parseInt(command.getArgumentMap().get("index"));
        menu.removeItem(index);
        menu.save();

        return false;
    }

    private boolean getKeyword(Command command, Menu menu) {
        String keyword = "";
        boolean isRunning = false;

        ui.promptItemKeyword();
        keyword = sc.nextLine();

        if(keyword.equals("/cancel")) {
            return true;
        }

        command.setArgumentString(keyword);
        menu.showResultsOfFind(command, menu.getItems());


        return false;
    }
    public boolean showResultsOfFind(Command command, Menu menu) {
        boolean isCancelled = false;
        isCancelled = getKeyword(command, menu);

        return isCancelled;
    }

}
