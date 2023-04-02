package seedu.moneygowhere;

import static org.junit.jupiter.api.Assertions.assertEquals;

import app.Command;
import item.MenuAssistant;
import org.junit.jupiter.api.Test;
import ui.MenuUi;
import item.Menu;

class ItemTest {
    Menu menu;

    public ItemTest() {
        menu = new Menu("menu.json");
    }

    @Test
    public void itemTest() {
        Command command = new Command("/additem -p 2.50 -n \"chicken rice\"");
        menu.addItem(command);

        Command command2 = new Command("/additem -p 20.1 -n \"chicken rice100\"");
        menu.addItem(command2);

        assertEquals("chicken rice100", menu.getItems().
                get(menu.getItems().size() - 1).getName());

        assertEquals(20.10, menu.getItem(menu.getItems().size() - 1).getPrice());
        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice100"): "Item name should be chicken rice100";

        assertEquals(20.10, menu.getItem(menu.getItems().size() - 1).getPrice());

        itemTest2();
        itemTest3();
        itemTest4();
        itemTest5();
        itemTest6();
        itemTest7();
        itemTest8();
        itemTest9();
        itemTest10();
        itemTest11();
        itemTest12();
    }

    public void itemTest2() {
        Command command = new Command("/additem -p 2kuku0.01 -n \"chicken rice3\"");
        menu.addItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice100"): "Item name should be chicken rice100";

        assertEquals("chicken rice100", menu.getItems().
                get(menu.getItems().size() - 1).getName());

    }

    public void itemTest3() {
        // max 2dp error
        Command command = new Command("/additem -p 20.0001 -n \"chicken rice4\"");
        menu.addItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice100"): "Item name should be chicken rice100";

        assertEquals("chicken rice100", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest4() {
        int index = menu.getItems().size() - 1;

        Command command = new Command("/updateitem -i " + index + " -n \"chicken rice1000\"");
        menu.updateItem(command);

        assert menu.getItems().
                get(index)
                .getName().equals("chicken rice1000"): "Item name should be changed to chicken rice1000";

        assertEquals("chicken rice1000", menu.getItems().
                get(index).getName());
    }

    public void itemTest5() {
        int index = menu.getItems().size() - 1;

        Command command = new Command("/updateitem -i " + index + " -p 2000.001");
        menu.updateItem(command);
        assert menu.getItems().
                get(index).getPrice() == 20.1:
                "Item price should be 20.10";

        assertEquals(20.10, menu.getItems().
                get(index).getPrice());
    }

    public void itemTest6() {
        int index = menu.getItems().size() - 1;

        Command command = new Command("/updateitem -i " + index + " -p 123.45");
        menu.updateItem(command);
        assert menu.getItems().
                get(index).getPrice() == 123.45:
                "Item price should be 123.45";

        assertEquals(123.45, menu.getItems().
                get(index).getPrice());
    }

    public void itemTest7() {
        int index = menu.getItems().size() - 1;

        Command command = new Command("/updateitem -i " + index + " -p 987.65 -n \"itemTest7 name 1 2 3\"");
        menu.updateItem(command);
        assert menu.getItems().
                get(index)
                .getName().equals("itemTest7 name 1 2 3"): "Item name should be changed to itemTest7 name 1 2 3";

        assertEquals("itemTest7 name 1 2 3", menu.getItems().
                get(index).getName());

        assert menu.getItems().
                get(index).getPrice() == 987.65:
                "Item price should be 987.65";

        assertEquals(987.65, menu.getItems().
                get(index).getPrice());
    }

    public void itemTest8() {
        int index = menu.getItems().size() - 1;
        String newName = menu.getItems().
                get(0).getName();
        String currName = menu.getItems().
                get(index).getName();
        Double currPrice = menu.getItems().
                get(index).getPrice();

        Command command = new Command("/updateitem -i " + index + " -p 1.23 -n \"" + newName + "\"");
        menu.updateItem(command);
        assert menu.getItems().
                get(index)
                .getName().equals(currName): "Item name should not change due to duplicate.";

        assertEquals(currName, menu.getItems().
                get(index).getName());

        assert menu.getItems().
                get(index).getPrice() == currPrice:
                "Item price should not change as name was duplicate.";

        assertEquals(currPrice, menu.getItems().
                get(index).getPrice());
    }

    public void itemTest9() {
        Command command = new Command("/deleteitem -i 1as.sdf");
        menu.deleteItem(command);

        int index = menu.getItems().size() - 1;

        assert menu.getItems().
                get(index)
                .getName().equals("itemTest7 name 1 2 3"): "Item name should be changed to itemTest7 name 1 2 3";

        assertEquals("itemTest7 name 1 2 3", menu.getItems().
                get(index).getName());

        assert menu.getItems().
                get(index).getPrice() == 987.65:
                "Item price should be 987.65";

        assertEquals(987.65, menu.getItems().
                get(index).getPrice());

    }

    public void itemTest10() {
        Command command = new Command("/deleteitem -i -1.0");
        menu.deleteItem(command);

        int index = menu.getItems().size() - 1;

        assert menu.getItems().
                get(index)
                .getName().equals("itemTest7 name 1 2 3"): "Item name should be changed to itemTest7 name 1 2 3";

        assertEquals("itemTest7 name 1 2 3", menu.getItems().
                get(index).getName());

        assert menu.getItems().
                get(index).getPrice() == 987.65:
                "Item price should be 987.65";
    }

    public void itemTest11() {
        Command command = new Command("/deleteitem -i 2.3");
        menu.deleteItem(command);

        int index = menu.getItems().size() - 1;

        assert menu.getItems().
                get(index)
                .getName().equals("itemTest7 name 1 2 3"): "Item name should be changed to itemTest7 name 1 2 3";

        assertEquals("itemTest7 name 1 2 3", menu.getItems().
                get(index).getName());

        assert menu.getItems().
                get(index).getPrice() == 987.65:
                "Item price should be 987.65";
    }

    public void itemTest12() {
        Command listcmd = new Command("/listitem");

        menu.displayList(listcmd);

        Command command = new Command("/deleteitem -i " + (menu.getItems().size()-1));
        menu.deleteItem(command);

        menu.displayList(listcmd);
    }

}
