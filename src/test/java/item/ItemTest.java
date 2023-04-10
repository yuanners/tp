package item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import app.Command;
import exception.DuplicateArgumentFoundException;
import org.junit.jupiter.api.Test;

class ItemTest {
    Menu menu;

    public ItemTest() {
        menu = new Menu("menu.json");
    }

    @Test
    public void itemTest() throws DuplicateArgumentFoundException {
        Command listcmd = new Command("/listitem");

        Command command = new Command("/additem -p 2.50 -n \"chicken rice test\"");
        menu.addItem(command);

        Command command2 = new Command("/additem -p 20.1 -n \"chicken rice test100\"");
        menu.addItem(command2);

        assertEquals("chicken rice test100", menu.getItems().
                get(menu.getItems().size() - 1).getName());

        assertEquals(20.10, menu.getItem(menu.getItems().size() - 1).getPrice());
        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test100") : "Item name should be chicken rice test100";

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
        itemTest13();
        itemTest14();
        itemTest15();
        itemTest16();
        itemTest17();
        itemTest18();
        itemTest19();
        itemTest20();
        itemTest21();
        itemTest22();
        itemTest23();
        itemTest24();
        itemTest25();
        itemTest26();
        itemTest27();
        itemTest28();
        itemTest29();
        itemTest30();
        itemTest31();
        itemTest32();
        itemTest33();
        cleanup();
    }

    public void itemTest2() throws DuplicateArgumentFoundException {
        // invalid price error
        Command command = new Command("/additem -p 2kuku0.01 -n \"chicken rice test3\"");
        menu.addItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test100") : "Item name should be chicken rice test100";

        assertEquals("chicken rice test100", menu.getItems().
                get(menu.getItems().size() - 1).getName());

    }

    public void itemTest3() throws DuplicateArgumentFoundException {
        // max 2dp error
        Command command = new Command("/additem -p 20.0001 -n \"chicken rice test4\"");
        menu.addItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test100") : "Item name should be chicken rice test100";

        assertEquals("chicken rice test100", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest4() throws DuplicateArgumentFoundException {
        // tests valid update item
        int index = menu.getItems().size() - 1;

        Command command = new Command("/updateitem -i " + index + " -n \"chicken rice test1000\"");
        menu.updateItem(command);

        assert menu.getItems().
                get(index)
                .getName().equals("chicken rice test1000") : "Item name should be changed to chicken rice test1000";

        assertEquals("chicken rice test1000", menu.getItems().
                get(index).getName());
    }

    public void itemTest5() throws DuplicateArgumentFoundException {
        // tests max 2dp when updating item
        int index = menu.getItems().size() - 1;

        Command command = new Command("/updateitem -i " + index + " -p 2000.001");
        menu.updateItem(command);
        assert menu.getItems().
                get(index).getPrice() == 20.1 :
                "Item price should be 20.10";

        assertEquals(20.10, menu.getItems().
                get(index).getPrice());
    }

    public void itemTest6() throws DuplicateArgumentFoundException {
        // tests valid update price
        int index = menu.getItems().size() - 1;

        Command command = new Command("/updateitem -i " + index + " -p 123.45");
        menu.updateItem(command);
        assert menu.getItems().
                get(index).getPrice() == 123.45 :
                "Item price should be 123.45";

        assertEquals(123.45, menu.getItems().
                get(index).getPrice());
    }

    public void itemTest7() throws DuplicateArgumentFoundException {
        // tests valid update name and price
        int index = menu.getItems().size() - 1;

        Command command = new Command("/updateitem -i " + index + " -p 987.65 -n \"itemTest7 name 1 2 3\"");
        menu.updateItem(command);
        assert menu.getItems().
                get(index)
                .getName().equals("itemTest7 name 1 2 3") : "Item name should be changed to itemTest7 name 1 2 3";

        assertEquals("itemTest7 name 1 2 3", menu.getItems().
                get(index).getName());

        assert menu.getItems().
                get(index).getPrice() == 987.65 :
                "Item price should be 987.65";

        assertEquals(987.65, menu.getItems().
                get(index).getPrice());
    }

    public void itemTest8() throws DuplicateArgumentFoundException {
        // tests duplicate name update
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
                .getName().equals(currName) : "Item name should not change due to duplicate.";

        assertEquals(currName, menu.getItems().
                get(index).getName());

        assert menu.getItems().
                get(index).getPrice() == currPrice :
                "Item price should not change as name was duplicate.";

        assertEquals(currPrice, menu.getItems().
                get(index).getPrice());
    }

    public void itemTest9() throws DuplicateArgumentFoundException {
        // tests invalid deleteitem index
        Command command = new Command("/deleteitem -i 1as.sdf");
        menu.deleteItem(command);

        int index = menu.getItems().size() - 1;

        assert menu.getItems().
                get(index)
                .getName().equals("itemTest7 name 1 2 3") : "Item name should be changed to itemTest7 name 1 2 3";

        assertEquals("itemTest7 name 1 2 3", menu.getItems().
                get(index).getName());

        assert menu.getItems().
                get(index).getPrice() == 987.65 :
                "Item price should be 987.65";

        assertEquals(987.65, menu.getItems().
                get(index).getPrice());

    }

    public void itemTest10() throws DuplicateArgumentFoundException {
        // tests negative deleteitem index
        Command command = new Command("/deleteitem -i -1.0");
        menu.deleteItem(command);

        int index = menu.getItems().size() - 1;

        assert menu.getItems().
                get(index)
                .getName().equals("itemTest7 name 1 2 3") : "Item name should be changed to itemTest7 name 1 2 3";

        assertEquals("itemTest7 name 1 2 3", menu.getItems().
                get(index).getName());

        assert menu.getItems().
                get(index).getPrice() == 987.65 :
                "Item price should be 987.65";
    }

    public void itemTest11() throws DuplicateArgumentFoundException {
        // tests non-integer deleteitem index
        Command command = new Command("/deleteitem -i 2.3");
        menu.deleteItem(command);

        int index = menu.getItems().size() - 1;

        assert menu.getItems().
                get(index)
                .getName().equals("itemTest7 name 1 2 3") : "Item name should be changed to itemTest7 name 1 2 3";

        assertEquals("itemTest7 name 1 2 3", menu.getItems().
                get(index).getName());

        assert menu.getItems().
                get(index).getPrice() == 987.65 :
                "Item price should be 987.65";
    }

    public void itemTest12() throws DuplicateArgumentFoundException {
        //test valid deleteitem
        int menuSize = menu.getItems().size();
        Command command = new Command("/deleteitem -i " + (menu.getItems().size() - 1));
        menu.deleteItem(command);

        assert menuSize != menu.getItems().size() : "Should have 1 item on the menu";
    }

    public void itemTest13() throws DuplicateArgumentFoundException {
        //test missing -n
        Command command = new Command("/additem -p 2");
        menu.addItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test") : "Item name should be chicken rice test";

        assertEquals("chicken rice test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest14() throws DuplicateArgumentFoundException {
        //test missing -p
        Command command = new Command("/additem -n Hokkien Mee test");
        menu.addItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test") : "Item name should be chicken rice test";

        assertEquals("chicken rice test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest15() throws DuplicateArgumentFoundException {
        //test missing -n and -p
        Command command = new Command("/additem");
        menu.addItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test") : "Item name should be chicken rice test";

        assertEquals("chicken rice test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest16() throws DuplicateArgumentFoundException {
        //test all integer name
        Command command = new Command("/additem -p 2 -n \"1234\"");
        menu.addItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test") : "Item name should be chicken rice test";

        assertEquals("chicken rice test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest17() throws DuplicateArgumentFoundException {
        //test empty name
        Command command = new Command("/additem -p 2 -n \"\"");
        menu.addItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test") : "Item name should be chicken rice test";

        assertEquals("chicken rice test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest18() throws DuplicateArgumentFoundException {
        //test negative price
        Command command = new Command("/additem -p -2 -n \"Hokkien Mee test\"");
        menu.addItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test") : "Item name should be chicken rice test";

        assertEquals("chicken rice test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest19() throws DuplicateArgumentFoundException {
        //test empty price
        Command command = new Command("/additem -p -n \"chicken\"");
        menu.addItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test") : "Item name should be chicken rice test";

        assertEquals("chicken rice test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest20() throws DuplicateArgumentFoundException {
        // tests missing -i
        Command command = new Command("/deleteitem");
        menu.deleteItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test") : "Item name should be chicken rice test";

        assertEquals("chicken rice test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest21() throws DuplicateArgumentFoundException {
        // tests decimal index
        Command command = new Command("/deleteitem -i 0.0");
        menu.deleteItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test") : "Item name should be chicken rice test";

        assertEquals("chicken rice test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest22() throws DuplicateArgumentFoundException {
        // tests all letters in index
        Command command = new Command("/deleteitem -i asdf");
        menu.deleteItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test") : "Item name should be chicken rice test";

        assertEquals("chicken rice test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest23() throws DuplicateArgumentFoundException {
        // tests delete empty index
        Command command = new Command("/deleteitem -i");
        menu.deleteItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test") : "Item name should be chicken rice test";

        assertEquals("chicken rice test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest24() throws DuplicateArgumentFoundException {
        // tests missing index update item
        Command command = new Command("/updateitem -n \"chicken rice test1000\"");
        menu.updateItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test") : "Item name should be chicken rice test";

        assertEquals("chicken rice test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest25() throws DuplicateArgumentFoundException {
        // tests missing -n updateitem
        Command command = new Command("/updateitem -i 0 \"chicken rice test1000\"");
        menu.updateItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test") : "Item name should be chicken rice test";

        assertEquals("chicken rice test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest26() throws DuplicateArgumentFoundException {
        // tests missing -p update item
        Command command = new Command("/updateitem -i 0 200");
        menu.updateItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getPrice() == 2.5 : "Item name should be chicken rice test";

        assertEquals(2.5, menu.getItems().
                get(menu.getItems().size() - 1).getPrice());
    }

    public void itemTest27() throws DuplicateArgumentFoundException {
        // tests missing name updateitem
        Command command = new Command("/updateitem -i 0 -n");
        menu.updateItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test") : "Item name should be chicken rice test";

        assertEquals("chicken rice test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest28() throws DuplicateArgumentFoundException {
        // tests missing price updateitem
        Command command = new Command("/updateitem -i 0 -p");
        menu.updateItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test") : "Item name should be chicken rice test";

        assertEquals("chicken rice test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest29() throws DuplicateArgumentFoundException {
        // tests all integer name updateitem
        Command command = new Command("/updateitem -i 0 -n \"12345\"");
        menu.updateItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test") : "Item name should be chicken rice test";

        assertEquals("chicken rice test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest30() throws DuplicateArgumentFoundException {
        // tests empty name updateitem
        Command command = new Command("/updateitem -i 0 -n\"\"");
        menu.updateItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test") : "Item name should be chicken rice test";

        assertEquals("chicken rice test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest31() throws DuplicateArgumentFoundException {
        // tests negative integer updateitem
        Command command = new Command("/updateitem -i 0 -p -23");
        menu.updateItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test") : "Item name should be chicken rice test";

        assertEquals("chicken rice test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest32() throws DuplicateArgumentFoundException {
        // tests empty price updateitem
        Command command = new Command("/updateitem -i 0 -p");
        menu.updateItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("chicken rice test") : "Item name should be chicken rice test";

        assertEquals("chicken rice test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void itemTest33() throws DuplicateArgumentFoundException {
        // tests valid update name
        Command command = new Command("/updateitem -i 0 -n \"Hokkien Mee test\"");
        menu.updateItem(command);

        assert menu.getItems().
                get(menu.getItems().size() - 1)
                .getName().equals("Hokkien Mee test") : "Item name should be Hokkien Mee test";

        assertEquals("Hokkien Mee test", menu.getItems().
                get(menu.getItems().size() - 1).getName());
    }

    public void cleanup() throws DuplicateArgumentFoundException {
        // clean up menu
        int menuSize = menu.getItems().size();
        Command command = new Command("/deleteitem -i " + (menuSize-1));
        menu.deleteItem(command);

    }


}
