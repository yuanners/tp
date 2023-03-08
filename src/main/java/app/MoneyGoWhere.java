package app;

import item.Item;
import item.ItemList;
import order.Order;
import order.OrderList;
import validation.ItemValidation;
import utility.Parser;
import utility.Store;
import utility.Ui;
import exception.InvalidArgumentException;

import java.util.Scanner;


public class MoneyGoWhere {

    public ItemList items;
    private OrderList orderList;
    private Parser parser = new Parser();

    private Store itemStore;

    public MoneyGoWhere() {
        items = new ItemList();
        orderList = new OrderList();
    }


    public void handleCommand(Command command) throws InvalidArgumentException {
        Ui ui = new Ui();
        ItemValidation itemValidation = new ItemValidation();
        switch (command.getCommand()) {
        case "listitem":
            items.displayList();
            break;
        case "additem":
            //Print some header
            if (!itemValidation.isValid(command)){
                break;
            }

            command.duplicateArgument("name", "n");
            command.duplicateArgument("price", "p");

            if (!itemValidation.isValid(command)){
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
            command.duplicateArgument("index", "i");

            if (!itemValidation.isValidFormatDelete(command)) {
                break;
            }
            if (!itemValidation.isInteger(command.getArgumentMap().get("index"))) {
                break;
            }
            if (!itemValidation.isValidIndex(command.getArgumentMap().get("index"), items)) {
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
                ui.println (ui.PROMPT_MESSAGE);
            }
        }

        sc.close();
    }


}

