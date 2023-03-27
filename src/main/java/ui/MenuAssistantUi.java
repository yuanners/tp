package ui;

public class MenuAssistantUi extends MenuUi {

    /**
     * Prompts for item name
     **/

    public void promptItemName() {
        System.out.println("Please enter the item's name: ");
    }

    public void promptItemNameChange() {
        System.out.println("Would you like to update item name? (yes/no)");
    }

    public void promptNewItemName() {
        System.out.println("Please enter the new name: ");
    }

    /**
     * Prompts for item price
     **/

    public void promptItemPrice() {
        System.out.println("Please enter the item's price: ");
    }

    public void promptItemPriceChange() {
        System.out.println("Would you like to update item price? (yes/no)");
    }

    public void promptNewItemPrice() {
        System.out.println("Please enter the new price: ");
    }

    /**
     * Prompts for item index
     **/

    public void promptItemIndex() {
        System.out.println("Please enter the item's index: ");
    }

    /**
     * Prompts for item keyword
     **/

    public void promptItemKeyword() {
        System.out.println("Please enter the keyword to search for: ");
    }

    /**
     * Misc
     **/

    public void promptUpdateItemUnrecognisedAnswer() {
        System.out.println("Sorry your input was not recognised.\nPlease answer with \"yes\" or \"no\".");
    }

    @Override
    public void printCommandCancelled(String cmd) {
        switch (cmd) {
        case "1":
            // Fallthrough
        case "additem":
            // Fallthrough
        case "/additem":
            System.out.println("Adding of item has been cancelled. All changes are discarded.");
            break;
        case "4":
            // Fallthrough
        case "updateitem":
            // Fallthrough
        case "/updateitem":
            System.out.println("Update of item has been cancelled. All changes are discarded.");
            break;
        case "2":
            // Fallthrough
        case "deleteitem":
            // Fallthrough
        case "/deleteitem":
            System.out.println("Deletion of item has been cancelled. All changes are discarded.");
            break;
        case "3":
            // Fallthrough
        case "listitem":
            // Fallthrough
        case "/listitem":
            System.out.println("List item has been cancelled.");
            break;
        case "5":
            // Fallthrough
        case "finditem":
            // Fallthrough
        case "/finditem":
            System.out.println("Find has been cancelled.");
            break;
        case "addorder":
            //Fallthrough
        case "/addorder":
            System.out.println("Add order has been cancelled.");
            break;
        case "refundorder":
            //Fallthrough
        case "/refundorder":
            System.out.println("Refund order has been cancelled.");
            break;
        default:
            // Fallthrough
        }
    }

    @Override
    public void printError(Flags.Error error) {
        System.out.print("Error: ");
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


        // Item Name
        case MISSING_ITEM_NAME_FLAG:
            // Fallthrough
        case ITEM_NAME_MIN_LENGTH_ERROR:
            System.out.println("Name cannot be empty.");
            break;
        case ITEM_NAME_MAX_LENGTH_ERROR:
            System.out.println("Name exceeds the 25 character limit. Please choose a shorter name.");
            break;
        case ITEM_DUPLICATE_NAME_ERROR:
            System.out.println("Name already exists. Please choose a different name.");
            break;

        // Item Price
        case MISSING_ITEM_PRICE_FLAG:
            // Fallthrough
        case ITEM_PRICE_MIN_LENGTH_ERROR:
            System.out.println("Price cannot be empty.");
            break;
        case ITEM_PRICE_NEGATIVE_ERROR:
            System.out.println("Price cannot be negative.");
            break;
        case ITEM_PRICE_OVERFLOW_ERROR:
            System.out.println("Price is too large. Please choose a smaller number.");
            break;
        case ITEM_PRICE_INVALID_DECIMAL_PLACE_ERROR:
            System.out.println("Price can have at most 2 decimal points.");
            break;
        case ITEM_PRICE_INVALID_FORMAT_ERROR:
            System.out.println("Price must be a number.");
            break;

        // Item Name and Price
        case MISSING_ITEM_NAME_OR_PRICE_FLAG:
            System.out.println("Either name or price have to be provided.");
            break;
        case MISSING_ITEM_NAME_AND_PRICE_FLAG:
            System.out.println("Both name and price have to be provided.");
            break;

        // Item index
        case MISSING_ITEM_INDEX_FLAG:
            System.out.println("Index cannot be empty.");
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
