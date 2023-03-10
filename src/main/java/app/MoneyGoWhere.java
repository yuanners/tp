package app;

import exception.InvalidArgumentException;
import item.Item;
import item.ItemList;
import validation.item.AddItemValidation;
import validation.item.DeleteItemValidation;
import order.Order;
import order.OrderList;
import utility.Parser;
import utility.Ui;
import validation.ItemValidation;

import java.util.Scanner;


public class MoneyGoWhere {

    public ItemList items;
    public OrderList orderList;
    private Parser parser = new Parser();

    public MoneyGoWhere() {
        items = new ItemList();
        orderList = new OrderList();
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
            items.appendItems(item);
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

            items.deleteItems(Integer.parseInt(command.getArgumentMap().get("index")));

            System.out.println(ui.SUCCESSFUL_COMMAND);

            break;

        case "listorder":
            orderList.displayList();
            break;

        case "addorder":
            Order order = new Order();
            order.addOrder(command, parser, items);
            orderList.appendOrder(order);
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

