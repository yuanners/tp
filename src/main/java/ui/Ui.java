package ui;

import java.util.Scanner;

public class Ui {

    public void printWelcomeMessage() {
        System.out.println("░█▀▄▀█ █▀▀█ █▀▀▄ █▀▀ █──█ ░█▀▀█ █▀▀█ ░█──░█ █──█ █▀▀ █▀▀█ █▀▀\n" +
                "░█░█░█ █──█ █──█ █▀▀ █▄▄█ ░█─▄▄ █──█ ░█░█░█ █▀▀█ █▀▀ █▄▄▀ █▀▀\n" +
                "░█──░█ ▀▀▀▀ ▀──▀ ▀▀▀ ▄▄▄█ ░█▄▄█ ▀▀▀▀ ░█▄▀▄█ ▀──▀ ▀▀▀ ▀─▀▀ ▀▀▀");
        System.out.println("Welcome to MoneyGoWhere!\n");
    }

    public void promptUserInput() {
        System.out.println("Please enter a command:");
    }

    public String inputHandler() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().trim();
        return userInput;
    }

    public void printInvalidCommand(String command) {
        System.out.println("The command: " + command + " is not a valid command.");
    }

    public void printCommandSuccess(String command) {
        System.out.println("The command: " + command + " was successfully executed!");
    }

    public void printCommandCancelled(String command) {
        System.out.println("The command: " + command + " has been cancelled.");
    }

    public void printAssistedHelp() {
        System.out.println("\nThere are 10 commands you can use as a new user in MoneyGoWhere.\n" +
                "For more details, please refer to the User Guide.\n" +
                "0. help\n" +
                "1. additem\n" +
                "2. listitem\n" +
                "3. deleteitem\n" +
                "4. updateitem\n" +
                "5. finditem\n" +
                "6. addorder\n" +
                "7. listorder\n" +
                "8. refundorder\n" +
                "9. /report {-r <type>} {-s <type} {-y <year>} {-f <start-date> -t <end-date>}\n\n" +
                "To see commands for experienced users, use `/help`.\n"
        );
    }

    public void printHelp() {
        System.out.println("\nThere are 10 commands you can use as an experienced user in MoneyGoWhere.\n" +
                "For more details, please refer to the User Guide.\n" +
                "0.  /help\n" +
                "1.  /additem -n \"<name>\" -p <price>\n" +
                "2.  /listitem\n" +
                "3.  /deleteitem -i <index>\n" +
                "4.  /updateitem -i <index> {-n <name>} {-p <price>}\n" +
                "5.  /finditem -n \"<name>\"\n" +
                "6a. /addorder -i <index> -q <quantity>\n" +
                "6b. /addorder -I [<index>:<quantity>, ...]\n" +
                "7.  /listorder\n" +
                "8.  /refundorder -i <order ID>\n" +
                "9.  /report {-r <type>} {-s <type} {-y <year>} {-f <start-date> -t <end-date>}\n\n" +
                "To see commands for new users, use `help`.\n");
    }

    public void printExit() {
        System.out.println("Thank you for using MoneyGoWhere. Goodbye!");
    }


    public void printError(Flags.Error error) {
        System.out.print("Error: ");
        switch (error) {
        case EMPTY_INPUT:
            System.out.println("Input is empty. Please enter something.");
            break;
        case DOUBLE_OVERFLOW:
            System.out.println("Double overflow! Please enter a double within the valid range.");
            break;
        case INTEGER_OVERFLOW:
            System.out.println("Integer overflow! Please enter an integer within the valid range.");
            break;
        case INVALID_INDEX:
            System.out.println("Please enter a valid index!");
            break;
        case UNRECOGNISED_COMMAND_ERROR:
            System.out.println("This command is not recognised.");
            break;
        case DUPLICATE_ARGUMENT_FOUND:
            System.out.println("Multiple options found. Please enter a valid command.");
            break;
        default:
            // Fallthrough
        }
    }

}
