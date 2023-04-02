package ui;

import java.util.NoSuchElementException;

public class StoreUi extends Ui {
    public boolean reinitializeMenu() {

        System.out.println("Data file ./datestore/menu.json is corrupted.");
        System.out.print("Do you want to reinitialize a new empty menu? [Y]es or [N]o: ");

        while (true) {
            try {
                String userInput = super.inputHandler().toUpperCase();

                if (userInput.equals("YES") || userInput.equals("Y")) {
                    System.out.println("Initializing empty menu ...");
                    return true;
                } else if (userInput.equals("NO") || userInput.equals("N")) {
                    System.out.println("Please fix ./datestore/menu.json before re-launching application");
                    return false;
                }

                System.out.print("Invalid input! Enter [Y]es or [N]o: ");
            } catch (NoSuchElementException e) {
                return false;
            }
        }
    }

    public boolean reinitializeTransactions() {

        System.out.println("Data file ./datestore/orders.json is corrupted.");
        System.out.println("Do you want to reinitialize a new empty list of transactions? [Y]es or [N]o: ");

        while (true) {
            try {
                String userInput = super.inputHandler().toUpperCase();

                if (userInput.equals("YES") || userInput.equals("Y")) {
                    System.out.println("Initializing empty list of transactions ...");
                    return true;
                } else if (userInput.equals("NO") || userInput.equals("N")) {
                    System.out.println("Please fix ./datestore/orders.json before re-launching application");
                    return false;
                }

                System.out.print("Invalid input! Enter [Y]es or [N]o: ");
            } catch (NoSuchElementException e) {
                return false;
            }
        }
    }

    public void menuNotFound() {
        System.out.println("Data file ./datestore/menu.json not found. Initializing new empty menu ...");
    }

    public void transactionsNotFound() {
        System.out.println("Data file ./datestore/orders.json not found. " +
                "Initializing new empty list of transactions ...");
    }
}
