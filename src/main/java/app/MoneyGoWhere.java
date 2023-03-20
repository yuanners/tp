package app;

import exception.ItemException;
import exception.OrderException;
import item.Menu;
import item.MenuAssistant;
import order.Order;
import order.Transaction;
import payment.Payment;
import payment.Refund;
import utility.Ui;

import java.util.Scanner;


public class MoneyGoWhere {

    public Menu menu;
    public MenuAssistant menuAssistant;
    public Transaction transactions;

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
            case "/help":
                // Fallthrough
            case "help":
                ui.printHelp();
                break;

            case "1":
                // Fallthrough
            case "1.":
                // Fallthrough
            case "additem":
                isCancelled = menuAssistant.addItem(command, menu);
                menuAssistant.printResult(command, isCancelled);
                break;
            case "/additem":
                menu.addItem(command);
                ui.printCommandSuccess(command.getCommand());
                break;

            case "2":
                // Fallthrough
            case "2.":
                // Fallthrough
            case "deleteitem":
                isCancelled = menuAssistant.deleteItem(command, menu);
                menuAssistant.printResult(command, isCancelled);
                break;
            case "/deleteitem":
                menu.deleteItem(command);
                ui.printCommandSuccess(command.getCommand());
                break;

            case "3":
                // Fallthrough
            case "3.":
                // Fallthrough
            case "/listitem":
                // Fallthrough
            case "listitem":
                menu.displayList();
                break;

            case "4":
                // Fallthrough
            case "4.":
                // Fallthrough
            case "updateitem":
                isCancelled = menuAssistant.updateItem(command, menu);
                menuAssistant.printResult(command, isCancelled);
                break;
            case "/updateitem":
                menu.updateItem(command);
                ui.printCommandSuccess(command.getCommand());
                break;

            case "5":
                // Fallthrough
            case "5.":
                // Fallthrough
            case "finditem":
                isCancelled = menuAssistant.showResultsOfFind(command, menu);
                menuAssistant.printResult(command, isCancelled);
                break;
            case "/finditem":
                menu.showResultsOfFind(command);
                ui.printCommandSuccess(command.getCommand());
                break;

            case "6":
                // Fallthrough
            case "6.":
                // Fallthrough
            case "/addorder":
                Order order = new Order();
                order.addOrder(command, menu);
                Payment payment = new Payment();
                ui.printOrderAdded(order.getSubTotal());
                payment.makePayment(order);
                transactions.appendOrder(order);
                break;

            case "7":
                // Fallthrough
            case "7.":
                // Fallthrough
            case "listorder":
                //Fall through
            case "/listorder":
                transactions.displayList();
                break;

            case "8":
                // Fallthrough
            case "8.":
                // Fallthrough
            case "/refundorder":
                Refund refund = new Refund();
                refund.refundTransaction(command, transactions);
                ui.printCommandSuccess(command.getCommand());
                break;
            default:
                ui.printInvalidCommand(command.getCommand());
            }
        } catch (ItemException | OrderException e) {
            ui.println(e.getMessage());
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

