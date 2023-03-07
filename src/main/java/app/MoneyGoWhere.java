package app;

import item.Item;
import item.ItemList;
import order.OrderList;
import utility.Parser;
import utility.Ui;

import java.util.Map;
import java.util.Scanner;

public class MoneyGoWhere {

    private ItemList items;
    private OrderList orders;
    private Parser parser;

    public MoneyGoWhere() {
        items = new ItemList();
    }

    private void handleCommand(Command command) {
        Ui ui = new Ui();
        switch (command.getCommand()) {
        case "listitem":
            items.displayList();
            break;
        case "additem":
            //Print some header
            command.duplicateArgument("name", "n");
            command.duplicateArgument("price", "p");

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
            //Do something
            break;
        case "addorder":
            //Do something
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
            handleCommand(command);
        }

        sc.close();
    }


}

