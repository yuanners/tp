package app;

import exception.InvalidArgumentException;
import exception.InvalidFlagException;
import item.Item;
import item.Menu;
import validation.Validation;
import validation.item.AddItemValidation;
import order.Order;
import order.Transaction;
import utility.Parser;
import utility.Ui;
import validation.item.ItemValidation;

import java.util.Scanner;


public class MoneyGoWhere {

    public Menu items;
    public Transaction transactions;
    private Parser parser = new Parser();

    public MoneyGoWhere() {
        items = new Menu();
        transactions = new Transaction();
    }

    public void handleCommand(Command command) throws InvalidArgumentException, InvalidFlagException {
        Ui ui = new Ui();
        AddItemValidation addItemValidation = new AddItemValidation();
        ItemValidation itemValidation = new ItemValidation();
        Validation validation = new Validation();
        switch(command.getCommand()) {
        case "listitem":
            items.displayList();
            break;
        case "additem":
            //Print some header
            if(!validation.isValidFormat(command, "n", "name") ||
                    !validation.isValidFormat(command, "p", "price")) {
                break;
            }

            command.mapArgumentAlias("name", "n");
            command.mapArgumentAlias("price", "p");

            if(!addItemValidation.isValid(command)) {
                break;
            }

            String name = command.getArgumentMap().get("name");
            Double price = Double.valueOf(command.getArgumentMap().get("price"));

            Item item = new Item(name, price);
            items.appendItem(item);
            ui.printCommandSuccess(command.getCommand());

            items.save();

            break;
        case "deleteitem":
            command.mapArgumentAlias("index", "i");

            if(!validation.isValidFormat(command, "i", "index")) {
                break;
            }
            if(!validation.isInteger(command.getArgumentMap().get("index"))) {
                break;
            }
            if(!validation.isValidIndex(command.getArgumentMap().get("index"), items)) {
                break;
            }

            items.deleteItem(Integer.parseInt(command.getArgumentMap().get("index")));

            ui.printCommandSuccess(command.getCommand());

            items.save();

            break;

        case "listorder":
            transactions.displayList();
            break;

        case "addorder":
            Order order = new Order();
            if(order.addOrder(command, parser, items)) {
                transactions.appendOrder(order);
                ui.printCommandSuccess(command.getCommand());
            }
            break;

        default:
            ui.printInvalidCommand(command.getCommand());
        }
    }

    public void run() {

        Ui ui = new Ui();
        Scanner sc = new Scanner(System.in);

        while(true) {
            ui.promptUserInput();
            String userInput = sc.nextLine();

            if(userInput.equals("exit")) {
                ui.printExitMessage();
                break;
            }

            Command command = new Command(userInput);

            try {
                handleCommand(command);
            } catch(InvalidArgumentException e) {
                ui.promptUserInputError();
            } catch(InvalidFlagException i) {
            }
        }

        sc.close();
    }


}

