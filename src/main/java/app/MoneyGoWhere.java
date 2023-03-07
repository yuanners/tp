package app;

import com.google.gson.JsonParseException;
import item.Item;
import item.ItemList;
import order.OrderList;
import utility.Parser;
import utility.Store;
import utility.Ui;

import java.io.IOException;
import java.util.Scanner;

public class MoneyGoWhere {

    private ItemList items;
    private OrderList orders;
    private Parser parser;

    private Store itemStore;

    public MoneyGoWhere() {

        this.itemStore = new Store("test.txt");
        try {
            this.items = itemStore.load(ItemList.class);
        }catch (IOException e){
            System.out.println(e.getMessage());
            this.items = new ItemList();
        }
        catch (JsonParseException e){
            System.out.println("JSON CORRUPTED");
            this.items = new ItemList();
        }

    }

    private void handleCommand(Command command) {
        switch (command.getCommand()) {
        case "listitem":
            //Print some header
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
            try{
                itemStore.save(items);

            }catch(IOException e){
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

