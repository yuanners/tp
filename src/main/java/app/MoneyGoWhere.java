package app;

import item.Item;
import item.ItemList;
import order.Order;
import order.OrderList;
import validation.ItemValidation;
import utility.Parser;
import utility.Ui;
import validation.invalidArgumentException;

import java.util.Scanner;


public class MoneyGoWhere {

    public ItemList items;
    private OrderList orderList;
    private Parser parser = new Parser();

    public MoneyGoWhere() {
        items = new ItemList();
        orderList = new OrderList();
    }


    public void handleCommand (Command command) throws invalidArgumentException {
        Ui ui = new Ui ();
        switch (command.getCommand ()) {
            case "listitem":
                items.displayList ();
                break;
            case "additem":
                //Print some header
                ItemValidation itemValidation = new ItemValidation ();

                if (!itemValidation.isValidFormat(command)) break;

                command.duplicateArgument("name", "n");
                command.duplicateArgument("price", "p");

                if (!itemValidation.isValid(command)) break;

                String name = command.getArgumentMap().get("name");
                Double price = Double.valueOf(command.getArgumentMap().get("price"));


                Item item = new Item(name, price);
                items.appendItems(item);

                break;
        case "deleteitem":
            command.duplicateArgument("index", "i");

            try {
                int index = Integer.parseInt(command.getArgumentMap().get("index"));
                items.deleteItems(index);
            } catch (IndexOutOfBoundsException e) {
                ui.printInvalidIndex();
            } catch (NumberFormatException e) {
                ui.printRequiresInteger();
            }
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
            } catch (invalidArgumentException e) {

            }
        }

        sc.close();
    }


}

