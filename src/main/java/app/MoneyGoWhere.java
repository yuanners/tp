package app;

import exception.ItemException;
import exception.OrderException;
import item.Menu;
import item.MenuAssistant;
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

    public void handleCommand(Command command, Scanner sc) {
        Ui ui = new Ui();

        try {
            switch (command.getCommand()) {
            case "listitem":
                menu.displayList();
                break;

            case "/additem":
                MenuAssistant menuAssistant = new MenuAssistant();
                boolean isCancelled = menuAssistant.addItem(command, menu, sc);
                if (isCancelled) {
                    ui.printCommandCancelled(command.getCommand());
                } else {
                    ui.printCommandSuccess(command.getCommand());
                }
                break;

            case "additem":
                menu.addItem(command, menu);
                ui.printCommandSuccess(command.getCommand());
                break;

            case "finditem":
                menu.showResultsOfFind(command, menu.getItems());
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
                order.addOrder(command, menu);
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

            handleCommand(command, sc);
        }

        sc.close();
    }


}

