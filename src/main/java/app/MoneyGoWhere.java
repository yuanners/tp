package app;

import exception.InvalidArgumentException;
import item.Item;
import item.Menu;
import validation.item.AddItemValidation;
import validation.item.DeleteItemValidation;
import order.Order;
import order.Transactions;
import utility.Parser;
import utility.Ui;

import java.util.Scanner;


public class MoneyGoWhere {

    public Menu items;
    public Transactions transactions;
    private Parser parser = new Parser();

    public MoneyGoWhere() {
        items = new Menu();
        transactions = new Transactions();
    }



    public void handleCommand(Command command) throws InvalidArgumentException {
        Ui ui = new Ui();
        AddItemValidation addItemValidation = new AddItemValidation();
        DeleteItemValidation deleteItemValidation = new DeleteItemValidation();
        switch (command.getCommand()) {
        case "listitem":
            items.displayList();
            break;
        case "additem":
            //Print some header
            if (!addItemValidation.isValidFormat(command)) {
                break;
            }

            command.mapArgumentAlias("name", "n");
            command.mapArgumentAlias("price", "p");

            if (!addItemValidation.isValid(command)) {
                break;
            }

            String name = command.getArgumentMap().get("name");
            Double price = Double.valueOf(command.getArgumentMap().get("price"));

            Item item = new Item(name, price);
            items.appendItem(item);
            System.out.println(ui.SUCCESSFUL_COMMAND);

            items.save();

            break;
        case "deleteitem":
            command.mapArgumentAlias("index", "i");

            if (!deleteItemValidation.isValidFormat(command)) {
                break;
            }
            if (!deleteItemValidation.isInteger(command.getArgumentMap().get("index"))) {
                break;
            }
            if (!deleteItemValidation.isValidIndex(command.getArgumentMap().get("index"), items)) {
                break;
            }

            items.deleteItem(Integer.parseInt(command.getArgumentMap().get("index")));

            System.out.println(ui.SUCCESSFUL_COMMAND);

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
            //Handle error if command not found
        }
    }

    public void run() {

        Ui ui = new Ui();
        Scanner sc = new Scanner(System.in);

        while (true) {
            ui.printUserInput();
            String userInput = sc.nextLine();

            if (userInput.equals("exit")) {
                break;
            }

            Command command = new Command(userInput);

            try {
                handleCommand(command);
            } catch (InvalidArgumentException e) {
                ui.println(ui.PROMPT_MESSAGE);
            }
        }

        sc.close();
    }


}

