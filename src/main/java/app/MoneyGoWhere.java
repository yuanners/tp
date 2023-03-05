package app;

import item.ItemList;
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
        //Load data from local store
        //Else initialize new store
    }

    private void handleCommand(String command, String arguments) {
        Parser parser = new Parser();
        Map<String, String> argumentsMap = parser.formatArguments(arguments);

        switch (command) {
        case "some_command":
            //Do something
            break;
        default:
            //Handle error if command not found
        }
    }

    public void run() {

        Ui ui = new Ui();
        Parser parser = new Parser();
        Scanner sc = new Scanner(System.in);

        while (true) {
            ui.printUserInput();
            String userInput = sc.nextLine();

            if (userInput.equals("exit")) {
                break;
            }

            String[] userInputs = parser.formatInput(userInput);
            handleCommand(userInputs[0], userInputs[1]);
        }

        sc.close();
    }


}

