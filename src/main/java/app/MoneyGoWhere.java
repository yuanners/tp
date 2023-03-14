package app;

import exception.InvalidArgumentException;
import item.Item;
import item.Menu;
import validation.item.AddItemValidation;
import order.Order;
import order.Transaction;
import utility.Parser;
import utility.Ui;
import validation.item.DeleteItemValidation;
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

    public void handleCommand(Command command) throws InvalidArgumentException {
        Ui ui = new Ui();

        switch(command.getCommand()) {
        case "listitem":
            items.displayList();
            break;
        case "additem":
            //Print some header
            AddItemValidation addItemValidation = new AddItemValidation();

            if(!addItemValidation.isValidFormat(command, "n", "name") ||
                    !addItemValidation.isValidFormat(command, "p", "price")) {
                break;
            }

            command.mapArgumentAlias("name", "n");
            command.mapArgumentAlias("price", "p");

            if(!addItemValidation.isValid(command, items)) {
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

            DeleteItemValidation deleteItemValidation = new DeleteItemValidation();

            command.mapArgumentAlias("index", "i");

            if(!deleteItemValidation.isValidFormat(command, "i", "index")) {
                break;
            }
            if(!deleteItemValidation.isInteger(command.getArgumentMap().get("index"))) {
                break;
            }
            if(!deleteItemValidation.isValidIndex(command.getArgumentMap().get("index"), items)) {
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
            order.addOrder(command, parser, items);
            transactions.appendOrder(order);
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
                break;
            }

            Command command = new Command(userInput);

            try {
                handleCommand(command);
            } catch(InvalidArgumentException e) {
                ui.promptUserInputError();
            }
        }

        sc.close();
    }


}

