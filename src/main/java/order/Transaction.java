package order;

import app.Command;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import exception.FileIsEmptyException;
import exception.UnrecognisedCommandException;
import ui.Flags;
import ui.StoreUi;
import ui.TransactionUi;
import ui.Ui;
import utility.Store;
import validation.Validation;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * The OrderList class represents a list of orders.
 */
public class Transaction {
    /**
     * The list of orders.
     */
    private ArrayList<Order> transactions;

    /**
     * The store used to load and save the order list.
     */
    private Store store;

    public Transaction(String fileName) {
        this.store = new Store(fileName);
        Type type = new TypeToken<ArrayList<Order>>() {
        }.getType();

        try {
            this.transactions = store.load(type);
        } catch (IOException e) {
            new StoreUi().transactionsNotFound();
            this.transactions = new ArrayList<>();
            save();

        } catch (JsonParseException | NumberFormatException | FileIsEmptyException e) {
            if (new StoreUi().reinitializeTransactions()) {
                this.transactions = new ArrayList<>();
                save();
            } else {
                System.exit(0);
            }
        }
    }

    public Transaction() {
        this.transactions = new ArrayList<>();
    }

    public Transaction(String dirName, String fileName) {
        this.store = new Store(dirName, fileName);
        Type type = new TypeToken<ArrayList<Order>>() {
        }.getType();

        try {
            this.transactions = store.load(type);
        } catch (IOException | FileIsEmptyException | JsonParseException | NumberFormatException e) {
            System.out.println(e.getMessage());
            this.transactions = new ArrayList<>();
        }
    }

    /**
     * Appends the given order to the order list and saves the changes.
     *
     * @param order the order to append to the order list
     */
    public void appendOrder(Order order) {
        this.transactions.add(order);
        save();
    }

    /**
     * Returns the list of orders.
     *
     * @return the list of orders
     */
    public ArrayList<Order> getOrderList() {
        return this.transactions;
    }

    /**
     * Displays the order list using the UI class.
     */
    public void displayList(Command command) {
        try {
            Validation validation = new Validation();
            validation.validateNoArgumentCommand(command);
        } catch (UnrecognisedCommandException e) {
            Ui ui = new Ui();
            ui.printError(Flags.Error.UNRECOGNISED_COMMAND_ERROR);
            return;
        }

        TransactionUi transactionUi = new TransactionUi();
        transactionUi.printOrderList(this.transactions);
        transactionUi.printSuccessfulListOrder();
    }

    /**
     * Saves the order list using the store.
     */
    public void save() {
        try {
            store.save(transactions);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
