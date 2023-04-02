package ui;

public class MenuAssistantUi extends MenuUi {

    /**
     * Prompts for item name
     **/

    public void promptItemName() {
        System.out.println("\nPlease enter the item's name or use \"/cancel\" to abort: ");
    }

    public void promptItemNameChange() {
        System.out.println("\nWould you like to update item name? (yes/no) or use \"/cancel\" to abort");
    }

    /**
     * Prompts for item price
     **/

    public void promptItemPrice() {
        System.out.println("\nPlease enter the item's price or use \"/cancel\" to abort: ");
    }

    public void promptItemPriceChange() {
        System.out.println("\nWould you like to update item price? (yes/no) or use \"/cancel\" to abort");
    }

    /**
     * Prompts for item index
     **/

    public void promptItemIndex() {
        System.out.println("\nPlease enter the item's index or use \"/cancel\" to abort: ");
    }

    /**
     * Prompts for item keyword
     **/

    public void promptItemKeyword() {
        System.out.println("\nPlease enter the keyword to search for or use \"/cancel\" to abort: ");
    }

    /**
     * Misc
     **/

    public void promptUpdateItemUnrecognisedAnswer() {
        System.out.println("\nSorry your input was not recognised.\nPlease answer with \"yes\" or \"no\""
                + " or use \"/cancel\" to abort.");
    }

    @Override
    public void printCommandCancelled(String cmd) {
        switch (cmd) {
        case "1":
            // Fallthrough
        case "additem":
            // Fallthrough
        case "/additem":
            System.out.println("\nAdding of item has been cancelled. All changes are discarded.\n");
            break;
        case "4":
            // Fallthrough
        case "updateitem":
            // Fallthrough
        case "/updateitem":
            System.out.println("\nUpdate of item has been cancelled. All changes are discarded.\n");
            break;
        case "2":
            // Fallthrough
        case "deleteitem":
            // Fallthrough
        case "/deleteitem":
            System.out.println("\nDeletion of item has been cancelled. All changes are discarded.\n");
            break;
        case "3":
            // Fallthrough
        case "listitem":
            // Fallthrough
        case "/listitem":
            System.out.println("\nList item has been cancelled.\n");
            break;
        case "5":
            // Fallthrough
        case "finditem":
            // Fallthrough
        case "/finditem":
            System.out.println("\nFind has been cancelled.\n");
            break;
        case "addorder":
            //Fallthrough
        case "/addorder":
            System.out.println("\nAdd order has been cancelled.\n");
            break;
        case "refundorder":
            //Fallthrough
        case "/refundorder":
            System.out.println("\nRefund order has been cancelled.\n");
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
        case ITEM_NAME_IS_INTEGER_ERROR:
            System.out.println("Name cannot be number. Please choose a different name.");
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
