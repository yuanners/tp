package app;

import item.Item;
import item.ItemList;
import item.ItemException;
import order.Order;
import order.OrderEntry;
import validation.ItemValidation;
import order.OrderList;
import utility.Parser;
import utility.Ui;
import validation.invalidArgumentException;
import validation.orderValidation;

import java.util.Map;
import java.util.Scanner;

public class MoneyGoWhere {

    public ItemList items;
    public OrderEntry OrderEntry;
    private OrderList orders;
    private Parser parser;

    public MoneyGoWhere() {
        items = new ItemList();
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
            //Do something
            break;
        case "addorder":
            orderValidation orderValidation = new orderValidation ();
            if (!orderValidation.isValidFormat (command)) break;

            command.duplicateArgument ("item", "i");
            command.duplicateArgument ("quantity", "q");

            if (!orderValidation.isValid (command)) break;

            int itemIndex = Integer.parseInt (command.getArgumentMap ().get ("item").trim());
            int quantity;

            if (command.getArgumentMap ().get ("quantity") != null) {
                quantity = Integer.parseInt (command.getArgumentMap ().get ("quantity").trim());
            } else {
                quantity = 1;
            }
            OrderEntry = new OrderEntry (items.getItems ().get (itemIndex), quantity);
            //System.out.println ("Name is: "+orderEntry.getItem ().getName ()+orderEntry.getQuantity ());
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

