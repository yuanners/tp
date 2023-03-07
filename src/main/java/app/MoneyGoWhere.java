package app;

import item.Item;
import item.ItemList;
import item.ItemException;
import validation.ItemValidation;
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
        switch (command.getCommand()) {
        case "listitem":
            //Print some header
            items.displayList();
            break;
        case "additem":
            //Print some header
            ItemValidation itemValidation = new ItemValidation();

            if(!itemValidation.isValidFormat(command)) break;

            command.duplicateArgument("name", "n");
            command.duplicateArgument("price", "p");

            if(!itemValidation.isValid(command)) break;

            String name = command.getArgumentMap().get("name");
            Double price = Double.valueOf(command.getArgumentMap().get("price"));

            try {
                Item item = new Item(name, price);
                items.appendItems(item);
            } catch (ItemException e) {
                //print error message
                System.out.println(e.getMessage());
            }

            break;
        case "deleteitem":
            //Do something
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

