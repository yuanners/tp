package ui;

import order.Order;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TransactionUi extends Ui {

    public void promptMoreOrderEntries() {
        System.out.println("Do you have more items to add? (yes/no/cancel)");
    }

    public void promptPayment() {
        System.out.println("Please use /pay -a <amount> -t <type> to make payment.");
    }
    public void printChangeGiven(Double change) {
        System.out.printf("The calculated change is $%.2f.\n", change);
    }

    public void printOrderList(ArrayList<Order> orders) {

        DecimalFormat df = new DecimalFormat("#.00");

        System.out.println("================================================");

        for (int i = 0; i < orders.size(); i++) {

            System.out.println("Order " + (i + 1));
            System.out.println("Order ID: " + orders.get(i).getOrderId());
            System.out.println("Order status: " + orders.get(i).getStatus());
            System.out.println("Order time: " + orders.get(i).getFormatDateTime());

            for (int j = 0; j < orders.get(i).getOrderEntries().size(); j++) {
                System.out.println((j + 1) + ". "
                        + orders.get(i).getOrderEntries().get(j).getItem().getName()
                        + " x" + orders.get(i).getOrderEntries().get(j).getQuantity());
            }

            String subtotal = df.format(orders.get(i).getSubTotal());
            System.out.println("\nSubtotal: $" + subtotal);
            System.out.println("================================================");

        }
    }

    public void printSuccessfulAddOrder() {
        System.out.println("Order added successfully!");
    }

    public void printSuccessfulPayment() {
        System.out.println("Order has been paid!");
    }

    public void printSuccessfulListOrder() {
        System.out.println("All transactions have been listed!");
    }

    public void printSuccessfulRefundOrder() {
        System.out.println("The order's status is now refunded!");
    }

    public void printOrderAdded(Double total) {
        DecimalFormat df = new DecimalFormat("#.00");
        String subtotal = df.format(total);
        System.out.println("\nSubtotal: $" + subtotal);
        System.out.println("Order has been added successfully. Total amount: $" + subtotal);
    }


//    public void printItemNotExistError() {
//        System.out.println("No such item exists.");
//    }
//
//    public void printSimilarItemError() {
//        System.out.println("Your input referenced multiple similar items. " +
//                "Please try again with a more specific item name.");
//    }

    public void helpAddOder() {
        System.out.println("/addorder -i <number> -q <number>");
    }

    @Override
    public void printError(Flags.Error error) {
        System.out.print("Error: ");
        switch (error) {
        //addorder flags
        case MISSING_ORDER_FLAG:
            System.out.println("Please input the item's index using -i <index> or --item <index>");
            break;
        case MISSING_ORDER_FLAG_ARGUMENT:
            System.out.println("Item index is empty. Please enter the item index.");
            break;
        case MISSING_QUANTITY_FLAG_ARGUMENT:
            System.out.println("Quantity is empty. Please enter the quantity.");
            break;
        case INVALID_ORDER_ITEM_INDEX_FORMAT:
            System.out.println("Index must be a number.");
            break;
        case NEGATIVE_ORDER_ITEM_INDEX:
            System.out.println("Index cannot be negative.");
        case INVALID_ORDER_ITEM_INDEX_OUT_OF_BOUNDS:
            System.out.println("Index does not exist.");
            break;
        case INVALID_QUANTITY_FORMAT:
            System.out.println("Quantity must be a number.");
            break;
        case INVALID_NEGATIVE_QUANTITY:
            System.out.println("Quantity must be more than 0.");
            break;
        //add multiple order flags
        case INVALID_MULTIPLE_ORDER_FORMAT_EXCEPTION:
            System.out.println("Please use the correct format: /addorder -I [index:quantity].");
            break;
        case MISSING_MULTIPLE_ORDER_ARGUMENT_EXCEPTION:
            System.out.println("Please specify the item index and quantity in [index:quantity] format after the flag.");
            break;
        case MISSING_MULTIPLE_ORDER_FLAG_EXCEPTION:
            System.out.println("Please use -I or --items flag to add multiple orders.");
            break;
        //payment flags
        case INVALID_PAY_TYPE:
            System.out.println("Please enter a valid payment type (Card/Cash/Others).");
            break;
        case INVALID_PAYMENT_AMOUNT_FOR_CARD:
            System.out.println("Please input exact amount for card payment.");
            break;
        case INVALID_PAY_AMOUNT_NEGATIVE:
            System.out.println("Payment amount must be more than 0.");
            break;
        case INVALID_PAY_AMOUNT_FORMAT:
            System.out.println("Payment amount must be a number.");
            break;
        case INVALID_PAY_AMOUNT_DECIMAL_PLACE:
            System.out.println("Payment amount cannot have more than 2 decimal place.");
            break;
        case INSUFFICIENT_PAY_AMOUNT:
            System.out.println("Insufficient amount. Payment amount must be more than or equals to subtotal.");
            break;
        case MISSING_PAY_TYPE_FLAG:
            System.out.println("Please include the payment type using -t or --type <type>.");
            break;
        case MISSING_PAY_TYPE_ARGUMENT:
            System.out.println("Payment type cannot be empty.");
            break;
        case MISSING_PAY_AMOUNT_FLAG:
            System.out.println("Please include the payment amount using -a or --amount <amount>.");
            break;
        case MISSING_PAY_AMOUNT_ARGUMENT:
            System.out.println("Payment amount cannot be empty.");
            break;
        case MISSING_PAY_COMMAND:
            System.out.println("Please use /pay -a <amount> -t <type> to make payment.");
            break;
            //refund flags
        case MISSING_REFUND_ORDER_FLAG:
            System.out.println("Please include the order ID using -i or --id <order id>.");
            break;
        case MISSING_REFUND_ORDER_ARGUMENT:
            System.out.println("Order ID cannot be empty.");
            break;
        case INVALID_REFUND_ORDER_ID:
            System.out.println("Invalid order ID.");
            break;
        case INVALID_REFUND_ORDER_TYPE:
            System.out.println("Order is already refunded.");
            break;
        }
    }
}

