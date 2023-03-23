package ui;

import item.Item;

import java.util.ArrayList;

public class MenuUi extends Ui {
    enum MenuErrorType {

    }

    /**
     * Methods for prompting user input
     */

    public void promptItemName() {
        System.out.println("Please enter the item's name: ");
    }

    public void promptItemNameChange() {
        System.out.println("Would you like to update item name? (yes/no)");
    }

    public void promptUpdateItemUnrecognisedAnswer() {
        System.out.println("Sorry your input was not recognised.\nPlease answer with \"yes\" or \"no\".");
    }

    //TODO: To be removed - same as promptItemName()
    public void promptNewItemName() {
        System.out.println("Please enter the new name: ");
    }

    public void promptItemPrice() {
        System.out.println("Please enter the item's price: ");
    }

    public void promptItemPriceChange() {
        System.out.println("Would you like to update item price? (yes/no)");
    }

    public void promptNewItemPrice() {
        System.out.println("Please enter the new price: ");
    }

    public void promptItemIndex() {
        System.out.println("Please enter the item's index: ");
    }

    public void promptItemKeyword() {
        System.out.println("Please enter the keyword to search for: ");
    }

    /**
     * Methods for printing output
     */

    public void printFindItem(int index, ArrayList<Item> menu) {
        System.out.printf("| %-5d | %-25s | %-5.2f |\n", index, menu.get(index).getName(), menu.get(index).getPrice());
    }

    public void printMenuHeader() {
        System.out.printf("| %-5s | %-25s | %-5s |\n", "Index", "Name", "Price");
        System.out.println("| " + "-".repeat(5) + " | " + "-".repeat(25) + " | " + "-".repeat(5) + " |");
    }

    public void printMenu(ArrayList<Item> menu) {
        printMenuHeader();
        for (int i = 0; i < menu.size(); ++i) {
            System.out.printf("| %-5d | %-25s | %-5.2f |\n", i, menu.get(i).getName(), menu.get(i).getPrice());
        }
    }

//    public void printEmptyMenuError() {
//        System.out.println("There are no items on the menu.");
//    }
//
//    public void printDuplcateItemNameError() {
//        System.out.println("Please enter a valid payment type (Card/Cash/Others).");
//    }
//
//    public void printEmptyNameError() {
//        System.out.println("Name cannot be empty.");
//    }
//
//    public void printExceedNameLengthError() {
//        System.out.println("Name exceeds the 25 character limit.");
//    }
//
//    public void printEmptyPriceError() {
//        System.out.println("Price cannot be empty.");
//    }
//
//    public void printNegativePriceError() {
//        System.out.println("Price cannot be negative.");
//    }
//
//    public void printInvalidAddItemCommand() {
//        System.out.println("additem command format is invalid.");
//    }
//
//    public void printInvalidDeleteItemCommand() {
//        System.out.println("deleteitem command format is invalid.");
//    }
//
//    public void printPriceDecimalError() {
//        System.out.println("Price must have at most 2 decimal points.");
//    }
//
//    public void printPriceNotNumberError() {
//        System.out.println("Price must be a number.");
//    }
//
//    public void printInvalidItemIndexError() {
//        System.out.println("This input requires a valid item index!");
//    }

    public void printNoItemFound(String input) {
        System.out.println("No menu items matching " + input + " were found!");
    }

    public void printSuccessfulListItem() {
        System.out.println("All items in the menu have been listed!");
    }

    public void printSuccessfulAddItem() {
        System.out.println("Item added successfully!");
    }

    public void printSuccessfulDeleteItem() {
        System.out.println("Item deleted successfully!");
    }

    public void printSuccessfulUpdateItem() {
        System.out.println("Item updated successfully!");
    }

    public void printFindItemComplete() {
        System.out.println("finditem completed!");
    }

    @Override
    public void printError(Flags.Error error) {
        switch (error) {
        case INPUT_EMPTY:
            System.out.println("Input is empty. Please enter something.");
            break;
        case DOUBLE_OVERFLOW:
            System.out.println("Double overflow! Please enter a double within the valid range.");
            break;
        case INTEGER_OVERFLOW:
            System.out.println("Integer overflow! Please enter an integer within the valid range.");
            break;
        case ADDITEM_FORMAT_INVALID:
            System.out.println("additem command format is invalid.");
            break;
        case DELETEITEM_FORMAT_INVALID:
            System.out.println("deleteitem command format is invalid.");
            break;
        case ITEM_NAME_EMPTY:
            System.out.println("Name cannot be empty.");
            break;
        case ITEM_NAME_TOO_LONG:
            System.out.println("Name exceeds the 25 character limit.");
            break;
        case ITEM_PRICE_EMPTY:
            System.out.println("Price cannot be empty.");
            break;
        case ITEM_PRICE_NEGATIVE:
            System.out.println("Price cannot be negative.");
            break;
        case ITEM_PRICE_INVALID_DECIMAL_PLACE:
            System.out.println("Price must have at most 2 decimal points.");
            break;
        case ITEM_INDEX_INVALID:
            System.out.println("This input requires a valid item index!");
            break;
        case ITEM_PRICE_NOT_A_NUMBER:
            System.out.println("Price must be a number.");
            break;
        }
    }


}
