package app;

import exception.ItemException;
import exception.OrderException;
import item.Menu;
import order.Order;
import order.Transaction;
import utility.Parser;
import utility.Ui;

import java.util.Scanner;


public class MoneyGoWhere {

    public Menu menu;
    public Transaction transactions;
    private Parser parser = new Parser();

    public MoneyGoWhere() {
        menu = new Menu();
        transactions = new Transaction();
    }

    public void handleCommand(Command command) {
        Ui ui = new Ui();

        try {
            switch (command.getCommand()) {
            case "listitem":
                menu.displayList();
                break;

            case "additem":
                menu.addItem(command, menu);
                ui.printCommandSuccess(command.getCommand());
                break;

            case "finditem":
                menu.findItem(command, menu.getItems());
                ui.printCommandSuccess(command.getCommand());
                break;

            case "deleteitem":
                menu.deleteItem(command, menu);
                ui.printCommandSuccess(command.getCommand());
                break;

            case "listorder":
                transactions.displayList();
                break;

            case "addorder":
                Order order = new Order();
                order.addOrder(command, parser, menu);
                transactions.appendOrder(order);
                ui.printCommandSuccess(command.getCommand());
                break;

            default:
                ui.printInvalidCommand(command.getCommand());
            }
        } catch (ItemException e) {
            ui.println(e.getMessage());
        } catch (OrderException o) {
            ui.println(o.getMessage());
        }

    }

    public void run() {

        Ui ui = new Ui();
        Scanner sc = new Scanner(System.in);

        while (true) {
            ui.promptUserInput();
            String userInput = sc.nextLine();

            if (userInput.equals("exit")) {
                ui.println(ui.getExitMessage());
                break;
            }

            Command command = new Command(userInput);

            handleCommand(command);
        }

        sc.close();
    }


}

