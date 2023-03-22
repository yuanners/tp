package ui;

public abstract class Ui {

    public void promptUserInput() {
        System.out.println("Please enter a command: ");
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

    public String printInvalidFlags(String command) {
        return "The usage of " + command + " is invalid.";
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

    public void printExit(){
        System.out.println("Thank you for using MoneyGoWhere. Goodbye!");
    }

    public void printEmptyMessageError(){
        System.out.println("Input is empty. Please enter something.");
    }

    public void printDoubleOverflowError(){
        System.out.println("Double overflow! Please enter a double within the valid range.");
    }

    public void printIntegerOverflowError(){
        System.out.println("Integer overflow! Please enter an integer within the valid range.");
    }

    public void printInvalidIndexError(){
        System.out.println("Please enter a valid index!");
    }

    public void printError(UiFlag.ErrorFlag error){
        System.out.println(error);
    }

}
