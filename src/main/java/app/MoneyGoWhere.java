package app;

import exception.ItemException;
import exception.OrderException;
import item.Menu;
import item.MenuAssistant;
import order.Order;
import order.Transaction;
import payment.Refund;
import utility.Parser;
import utility.Ui;

import java.util.Scanner;


public class MoneyGoWhere {

    public Menu menu;
    public MenuAssistant menuAssistant;
    public Transaction transactions;
    private Parser parser = new Parser();

    public MoneyGoWhere() {
        menu = new Menu();
        menuAssistant = new MenuAssistant();
        transactions = new Transaction();
    }

    public void handleCommand(Command command) {
        Ui ui = new Ui();
        boolean isCancelled;
        try {
            switch (command.getCommand()) {
            case "/listitem":
                // Fallthrough

            case "listitem":
                menu.displayList();
                break;

            case "additem":
                isCancelled = menuAssistant.addItem(command, menu);
                menuAssistant.printResult(command, isCancelled);
                break;

            case "/additem":
                menu.addItem(command);
                ui.printCommandSuccess(command.getCommand());
                break;

            case "finditem":
                isCancelled = menuAssistant.showResultsOfFind(command, menu);
                menuAssistant.printResult(command, isCancelled);
                break;

            case "/finditem":
                menu.showResultsOfFind(command);
                ui.printCommandSuccess(command.getCommand());
                break;

            case "deleteitem":
                isCancelled = menuAssistant.deleteItem(command, menu);
                menuAssistant.printResult(command, isCancelled);
                break;

            case "/deleteitem":
                menu.deleteItem(command);
                ui.printCommandSuccess(command.getCommand());
                break;
            case "listorder":
                //Fall through

            case "/listorder":
                transactions.displayList();
                break;

            case "/addorder":
                Order order = new Order();
                order.addOrder(command, menu);
                transactions.appendOrder(order);
                ui.printCommandSuccess(command.getCommand());
                break;
            case "/refundorder":
                Refund refund = new Refund();
                refund.refundTransaction(command, transactions);
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

