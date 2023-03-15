package app;

import exception.InvalidArgumentException;
import exception.InvalidFlagException;
import exception.ItemException;
import item.Menu;
import order.Order;
import order.Transaction;
import utility.Parser;
import utility.Ui;

import java.util.Scanner;


public class MoneyGoWhere {

    public Menu items;
    public Transaction transactions;
    private Parser parser = new Parser();

    public MoneyGoWhere() {
        items = new Menu();
        transactions = new Transaction();
    }

    public void handleCommand(Command command) throws InvalidArgumentException, InvalidFlagException{
        Ui ui = new Ui();

        try {
            switch(command.getCommand()) {
            case "listitem":
                items.displayList();
                break;
            case "additem":
                items.addItem(command, items);
                ui.printCommandSuccess(command.getCommand());
                break;
            case "deleteitem":
                items.deleteItem(command, items);
                ui.printCommandSuccess(command.getCommand());
                break;
            case "listorder":
                transactions.displayList();
                break;

            case "addorder":
                Order order = new Order();
                order.addOrder(command, parser, items);
                transactions.appendOrder(order);
                ui.printCommandSuccess(command.getCommand());
                break;

            default:
                ui.printInvalidCommand(command.getCommand());
            }
        } catch(ItemException e) {
            ui.println(e.getMessage());
        } catch(InvalidArgumentException a) {
            ui.println(a.getMessage());
        } catch(InvalidFlagException f) {
            ui.println(f.getMessage());
        }

    }

    public void run() {

        Ui ui = new Ui();
        Scanner sc = new Scanner(System.in);

        while(true) {
            ui.promptUserInput();
            String userInput = sc.nextLine();

            if(userInput.equals("exit")) {
                ui.println(ui.EXIT_MESSAGE);
                break;
            }

            Command command = new Command(userInput);

            try {
                handleCommand(command);
            } catch(InvalidFlagException f) {
                ui.println(f.getMessage());
            } catch(InvalidArgumentException a) {
                ui.println(a.getMessage());
            }
        }

        sc.close();
    }


}

