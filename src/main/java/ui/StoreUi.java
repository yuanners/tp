package ui;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class StoreUi {
    public boolean reinitializeMenu() {
        Scanner sc = new Scanner(System.in);
        int attempts = 0;

        System.out.println("Data file ./datestore/menu.json is corrupted.");
        System.out.print("Do you want to reinitialize a new empty menu? [Y]es or [N]o: ");

        while (attempts < 10) {
            try {
                String userInput = sc.nextLine().toUpperCase();

                if (userInput.equals("YES") || userInput.equals("Y")) {
                    System.out.println("Initializing empty menu ...");
                    return true;
                } else if (userInput.equals("NO") || userInput.equals("N")) {
                    System.out.println("Please fix ./datestore/menu.json before re-launching application");
                    return false;
                }

                System.out.print("Invalid input! Enter [Y]es or [N]o: ");
            } catch (NoSuchElementException e) {
                System.out.print("Invalid input! Enter [Y]es or [N]o: ");
            }
            attempts++;
        }
        System.out.println("Maximum number of attempts reached, exiting program.");
        System.out.println("Please fix ./datestore/menu.json before re-launching application");
        return false;
    }

    public boolean reinitializeTransactions() {
        Scanner sc = new Scanner(System.in);
        int attempts = 0;

        System.out.println("Data file ./datestore/orders.json is corrupted.");
        System.out.println("Do you want to reinitialize a new empty list of transactions? [Y]es or [N]o: ");

        while (attempts < 10) {
            try {
                String userInput = sc.nextLine().toUpperCase();

                if (userInput.equals("YES") || userInput.equals("Y")) {
                    System.out.println("Initializing empty list of transactions ...");
                    return true;
                } else if (userInput.equals("NO") || userInput.equals("N")) {
                    System.out.println("Please fix ./datestore/orders.json before re-launching application");
                    return false;
                }

                System.out.print("Invalid input! Enter [Y]es or [N]o: ");
            } catch (NoSuchElementException e) {
                System.out.print("Invalid input! Enter [Y]es or [N]o: ");
            }
            attempts++;
        }

        System.out.println("Maximum number of attempts reached, exiting program.");
        System.out.println("Please fix ./datestore/menu.json before re-launching application");
        return false;
    }

    public void menuNotFound() {
        System.out.println("Data file ./datestore/menu.json not found. Initializing new empty menu ...");
    }

    public void transactionsNotFound() {
        System.out.println("Data file ./datestore/orders.json not found. " +
                "Initializing new empty list of transactions ...");
    }
}
