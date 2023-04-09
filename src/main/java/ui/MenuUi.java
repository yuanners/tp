package ui;

import item.Item;

import java.util.ArrayList;

public class MenuUi extends Ui {

    /**
     * Methods for printing output
     */

    public void printItemNotFound() {
        System.out.println("\nThe entered item cannot be found.");
    }

    public void printFindItem(int index, ArrayList<Item> menu) {
        System.out.printf("| %-5d | %-25s | %-8.2f |\n", index, menu.get(index).getName(), menu.get(index).getPrice());
    }

    public void printMenuHeader() {
        System.out.printf("\n| %-5s | %-25s | %-8s |\n", "Index", "Name", "Price");
        System.out.println("| " + "-".repeat(5) + " | " + "-".repeat(25)
                + " | " + "-".repeat(8) + " |");
    }

    public void printMenu(ArrayList<Item> menu) {
        printMenuHeader();
        for (int i = 0; i < menu.size(); ++i) {
            System.out.printf("| %-5d | %-25s | %-8.2f |\n", i, menu.get(i).getName(), menu.get(i).getPrice());
        }
    }

    public void printNoItemFound(String input) {
        System.out.println("\nNo menu items matching " + input + " were found!");
    }

    @Override
    public void printCommandSuccess(String cmd) {
        switch (cmd) {
        case "1":
            // Fallthrough
        case "additem":
            // Fallthrough
        case "/additem":
            System.out.println("\nItem added successfully.\n");
            break;
        case "4":
            // Fallthrough
        case "updateitem":
            // Fallthrough
        case "/updateitem":
            System.out.println("\nItem updated successfully.\n");
            break;
        case "2":
            // Fallthrough
        case "deleteitem":
            // Fallthrough
        case "/deleteitem":
            System.out.println("\nItem deleted successfully.\n");
            break;
        case "3":
            // Fallthrough
        case "listitem":
            // Fallthrough
        case "/listitem":
            System.out.println("\nAll items in the menu have been listed!\n");
            break;
        case "5":
            // Fallthrough
        case "finditem":
            // Fallthrough
        case "/finditem":
            System.out.println("\nfinditem completed!\n");
            break;
        case "addorder":
            //Fallthrough
        case "/addorder":
            System.out.println("\nOrder is added!\n");
            break;
        case "refundorder":
            //Fallthrough
        case "/refundorder":
            System.out.println("\nOrder is refunded!\n");
            break;
        default:
            // Fallthrough
        }
    }

    @Override
    public void printError(Flags.Error error) {
        System.out.print("\nError: ");
        switch (error) {
        case EMPTY_INPUT:
            System.out.println("Input is empty. Please enter something.");
            break;
        case EMPTY_MENU:
            System.out.println("There are no items on the menu.");
            break;

        // Item name in find item
        case MISSING_FIND_ITEM_DESCRIPTION:
            System.out.println("Please specify the keyword to search for.");
            break;
        case EMPTY_FIND_ITEM_DESCRIPTION:
            System.out.println("Keyword cannot be empty.");
            break;


        // Item Name
        case MISSING_ITEM_NAME_FLAG:
            System.out.println("Please include the item's name using: -n <name>");
            break;
        case ITEM_NAME_MIN_LENGTH_ERROR:
            System.out.println("Please specify the item's name after the \"-n\" : -n <name>");
            break;
        case ITEM_NAME_MAX_LENGTH_ERROR:
            System.out.println("Name exceeds the 25 character limit. Please choose a shorter name.");
            break;
        case ITEM_DUPLICATE_NAME_ERROR:
            System.out.println("Name already exists. Please choose a different name.");
            break;
        case ITEM_NAME_IS_INTEGER_ERROR:
            System.out.println("Name cannot be number. Please choose a different name.");
            break;

        // Item Price
        case MISSING_ITEM_PRICE_FLAG:
            System.out.println("Please include the item's price using: -p <price>");
            break;
        case ITEM_PRICE_NEGATIVE_ERROR:
            System.out.println("Price cannot be negative.");
            break;
        case ITEM_PRICE_OVERFLOW_ERROR:
            System.out.println("Price is too large. Please choose a smaller number.");
            break;
        case ITEM_PRICE_MIN_LENGTH_ERROR:
            System.out.println("Please specify the item's price after the \"-p\" : -p <price>");
            break;
        case ITEM_PRICE_INVALID_DECIMAL_PLACE_ERROR:
            System.out.println("Price can have at most 2 decimal points.");
            break;
        case ITEM_PRICE_INVALID_FORMAT_ERROR:
            System.out.println("Price must be a number.");
            break;

        // Item Name and Price
        case MISSING_ITEM_NAME_OR_PRICE_FLAG:
            System.out.println("Please include the item's name or price using: -n <name> OR -p <price>");
            break;
        case MISSING_ITEM_NAME_AND_PRICE_FLAG:
            System.out.println("Please include the item's name and price using: -n <name> -p <price>");
            break;

        // Item index
        case MISSING_ITEM_INDEX_FLAG:
            System.out.println("Please include the item's index using: -i <index>");
            break;
        case ITEM_INDEX_INVALID_FORMAT_ERROR:
            System.out.println("Index must be a number.");
            break;
        case ITEM_INDEX_OVERFLOW_ERROR:
            // Fallthrough
        case ITEM_INDEX_OUT_OF_BOUND_ERROR:
            System.out.println("Index does not exist.");
            break;

        default:
            // Fallthrough

        }

    }


}
