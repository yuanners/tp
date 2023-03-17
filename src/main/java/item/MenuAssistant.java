package item;

import app.Command;
import exception.ItemException;
import utility.Ui;
import validation.item.AddItemValidation;

import java.util.Scanner;

public class MenuAssistant {
    Scanner sc;
    public MenuAssistant() {
        sc = new Scanner(System.in);
    }

    private boolean getName(Command command, Menu menu) {
        String name = "";
        boolean isValidName = false;
        Ui ui = new Ui();
        AddItemValidation addItemValidation = new AddItemValidation();


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
        Ui ui = new Ui();
        AddItemValidation addItemValidation = new AddItemValidation();


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

}
