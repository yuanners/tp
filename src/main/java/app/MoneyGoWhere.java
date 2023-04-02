package app;

import item.Menu;
import order.Transaction;
import ui.Ui;

import java.util.Scanner;


public class MoneyGoWhere {

    public Menu menu;
    public Transaction transactions;
    private final String ORDER_DATA_FILE = "orders.json";
    private final String MENU_DATA_FILE = "menu.json";


    public MoneyGoWhere() {
        menu = new Menu(MENU_DATA_FILE);
        transactions = new Transaction(ORDER_DATA_FILE);
    }

    /**
     * Runs the MoneyGoWhere application. This method prompts the user for input using the Ui class, creates a Command
     * object based on the user input, and passes the Command object to the Router to process the command.
     * The loop continues until the user types "exit" to exit the application.
     */
    public void run() {

        Ui ui = new Ui();
        Scanner sc = new Scanner(System.in);
        Router router = new Router(menu, transactions);

        ui.printWelcomeMessage();

        while (true) {
            ui.promptUserInput();
            String userInput = sc.nextLine();

            if (userInput.equals("exit")) {
                ui.printExit();
                break;
            }
            if(!userInput.isBlank()){
                Command command = new Command(userInput);
                router.handleRoute(command);
            } else {
                System.out.println("INPUT IS BLANK");
            }
        }

        sc.close();
    }


}

