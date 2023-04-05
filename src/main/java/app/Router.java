package app;

import exception.DuplicateArgumentFoundException;
import exception.UnrecognisedCommandException;
import item.Menu;
import item.MenuAssistant;
import order.Order;
import order.OrderAssistant;
import order.Transaction;
import payment.Payment;
import payment.Refund;
import statistic.Statistic;
import payment.RefundAssistant;
import ui.Flags;
import ui.TransactionUi;
import ui.Ui;
import validation.Validation;

/**
 * The Router class handles the routing of commands, based on whether they start with a slash (/) or not.
 * It contains methods for processing the different types of commands and directing them to the appropriate
 * classes for execution.
 */
public class Router {
    private Ui ui;
    private Menu menu;
    private Transaction transactions;
    private TransactionUi transactionUi;
    private Payment payment;

    /**
     * Constructs a new Router object with the specified menu and transactions.
     *
     * @param menu         the menu object to use for item-related commands
     * @param transactions the transactions object to use for order-related commands
     */
    public Router(Menu menu, Transaction transactions) {
        this.ui = new Ui();
        this.menu = menu;
        this.transactions = transactions;
        this.transactionUi = new TransactionUi();
        this.payment = new Payment();
    }

    /**
     * Processes professional commands (starting with a slash (/)) and directs them to the appropriate method
     * for execution.
     *
     * @param command the Command object containing the professional command to process
     */
    private void proRoute(Command command) throws DuplicateArgumentFoundException {
        switch (command.getCommand()) {
        case "help":
            ui.printAssistedHelp();
            break;
        case "/help":
            ui.printHelp(command);
            break;
        case "/additem":
            menu.addItem(command);
            break;
        case "/deleteitem":
            menu.deleteItem(command);
            break;
        case "/listitem":
            menu.displayList(command);
            break;
        case "/updateitem":
            menu.updateItem(command);
            break;
        case "/finditem":
            menu.showResultsOfFind(command);
            break;
        case "/addorder":
            new Order(command, menu, transactions, transactionUi, payment);
            break;
        case "/listorder":
            transactions.displayList(command);
            break;
        case "/refundorder":
            Refund refund = new Refund();
            refund.refundTransaction(command, transactions);
            break;
        case "/report":
            Statistic.handleStatisticRoute(command, transactions, menu);
            break;
        default:
            ui.printInvalidCommand(command.getCommand());
        }

    }

    /**
     * Processes assisted commands (not starting with a slash (/)) and directs them to the appropriate method
     * for execution.
     *
     * @param command the Command object containing the assisted command to process
     */
    private void assistRoute(Command command) throws DuplicateArgumentFoundException {
        Validation validation = new Validation();

        try {
            validation.validateNoArgumentCommand(command);

            MenuAssistant menuAssistant = new MenuAssistant();
            OrderAssistant orderAssistant = new OrderAssistant();
            RefundAssistant refundAssistant = new RefundAssistant();
            boolean isCancelled;

            switch (command.getCommand()) {
            case "?":
            case "help":
                ui.printAssistedHelp();
                break;
            case "1":
            case "additem":
                isCancelled = menuAssistant.addItem(command, menu);
                menuAssistant.printResult(command, isCancelled);
                break;
            case "2":
            case "deleteitem":
                isCancelled = menuAssistant.deleteItem(command, menu);
                menuAssistant.printResult(command, isCancelled);
                break;
            case "3":
            case "listitem":
                menu.displayList(command);
                break;
            case "4":
            case "updateitem":
                isCancelled = menuAssistant.updateItem(command, menu);
                menuAssistant.printResult(command, isCancelled);
                break;
            case "5":
            case "finditem":
                isCancelled = menuAssistant.showResultsOfFind(command, menu);
                menuAssistant.printResult(command, isCancelled);
                break;
            case "6":
            case "addorder":
                orderAssistant.assistedAddOrder(menu, transactions);
                break;
            case "7":
            case "listorder":
                transactions.displayList(command);
                break;
            case "8":
            case "refundorder":
                isCancelled = refundAssistant.refundOrder(transactions);
                menuAssistant.printResult(command, isCancelled);
                break;
            default:
                ui.printInvalidCommand(command.getCommand());
            }
        } catch (UnrecognisedCommandException e) {
            ui.printError(Flags.Error.UNRECOGNISED_COMMAND_ERROR);
        }

    }

    /**
     * This method handles the routing of commands based on the first character of the command string.
     * If the first character of the command is "/", the method invokes the proRoute method to process the command.
     * If the first character of the command is not "/", the method invokes the assistRoute method to process the
     * command.
     *
     * @param command The command to be processed.
     */
    public void handleRoute(Command command) throws DuplicateArgumentFoundException {
        if (command.getCommand().charAt(0) == '/') {
            proRoute(command);
        } else {
            assistRoute(command);
        }
    }
}
