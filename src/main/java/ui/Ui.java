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

    public String inputHandler(){
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
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

    public void printHelp() {
        System.out.println("There are 8 commands you can use in MoneyGoWhere. " +
                "For more details, please refer to the user guide.\n" +
                "1. additem\n" +
                "2. deleteitem\n" +
                "3. listitem\n" +
                "4. updateitem\n" +
                "5. finditem\n" +
                "6. addorder\n" +
                "7. listorder\n" +
                "8. refundorder"
        );
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
