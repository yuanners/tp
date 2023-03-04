package app;

import item.ItemList;
import order.OrderList;

import java.util.Scanner;

public class MoneyGoWhere {

    private ItemList items;
    private OrderList orders;

    public MoneyGoWhere() {
        //Load data from local store
        //Else initialize new store
    }

    private void handleCommand(String command, String arguments) {
        switch (command) {
        case "some_command":
            //Parse arguments into something that can be interpreted.
            //Do something
            break;
        default:
            //Handle error if command not found
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();

            //Parse the userInput and split command from arguments
            handleCommand("", "");

        }
    }


}

