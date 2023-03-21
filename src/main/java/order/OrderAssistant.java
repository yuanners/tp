package order;

import item.Menu;
import utility.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderAssistant {

    Ui ui;
    Scanner sc;
    private final ArrayList<String> CANCEL = new ArrayList<>() {
        {
            add("/cancel");
            add("cancel");
        }
    };
    private final ArrayList<String> YES = new ArrayList<>() {
        {
            add("yes");
            add("y");
            add("YES");
            add("Y");
        }
    };
    private final ArrayList<String> NO = new ArrayList<>() {
        {
            add("no");
            add("n");
            add("NO");
            add("N");
        }
    };

    public OrderAssistant() {
        ui = new Ui();
        sc = new Scanner(System.in);
    }

    public boolean addOrder(Menu menu) {

        Order order = new Order();

        boolean hasMoreOrderEntry = true;

        while (hasMoreOrderEntry) {

            String itemName = getItem();
            if (CANCEL.contains(itemName)) {
                return true;
            }

            String quantity = getQuantity();
            if (CANCEL.contains(quantity)) {
                return true;
            }

        }

        // Returns false when there are no more orderEntries to add
        return false;
    }

    private String getItem() {

        String item = "";

        ui.promptItemName();
        item = sc.nextLine();

        return item;

    }

    private String getQuantity() {

        String quantity = "";

        ui.promptItemQuantity();
        quantity = sc.nextLine();

        return quantity;
    }

}
