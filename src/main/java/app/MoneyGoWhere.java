package app;

import item.Menu;
import order.Transaction;
import utility.Ui;

import java.util.Scanner;


public class MoneyGoWhere {

    private Menu menu;
    private Transaction transactions;


    public MoneyGoWhere() {
        menu = new Menu();
        transactions = new Transaction();
    }

    /**
     * Runs the MoneyGoWhere application. This method prompts the user for input using the Ui class, creates a Command
     * object based on the user input, and passes the Command object to the Router to process the command.
     * The loop continues until the user types "exit" to exit the application.
     */
    public void run() {

        Ui ui = new Ui();
        Scanner sc = new Scanner(System.in);
        Router router = new Router(menu, transactions, ui);

        while (true) {
            ui.promptUserInput();
            String userInput = sc.nextLine();

            if (userInput.equals("exit")) {
                ui.println(ui.getExitMessage());
                break;
            }

            Command command = new Command(userInput);
            router.handleRoute(command);
        }

        sc.close();
    }


}

