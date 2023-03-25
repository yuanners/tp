package seedu.moneygowhere;

import static org.junit.jupiter.api.Assertions.assertEquals;

import app.Command;
import order.Transaction;
import org.junit.jupiter.api.Test;
import utility.Ui;
import app.Router;
import item.Menu;

class ItemTest {
    Ui ui;
    Menu menu;
    Router router;
    Transaction transaction;

    public ItemTest() {
        ui = new Ui();
        menu = new Menu();
        transaction = new Transaction();
        router = new Router(menu, transaction);
        runTest("/additem -p 2.50 -n \"chicken rice\"", router);
    }

    public void runTest(String input, Router router) {

        Ui ui = new Ui();


        ui.promptUserInput();
        String userInput = input;

        Command command = new Command(userInput);

        router.handleRoute(command);

    }

    @Test
    public void itemTest() {

        runTest("/additem -p 20.1 -n \"chicken rice100\"", router);
        assertEquals("chicken rice100", router.menu.getItems().
                get(router.menu.getItems().size() - 1).getName());

        assert router.menu.getItems().
                get(router.menu.getItems().size() - 1)
                .getName().equals("chicken rice100"): "Item name should be chicken rice100";

        assertEquals(20.10, router.menu.getItems().
                get(router.menu.getItems().size() - 1).getPrice());

    }

    @Test
    public void itemTest2() {
        runTest("/additem -p 2kuku0.01 -n \"chicken rice3\"", router);
        assert router.menu.getItems().
                get(router.menu.getItems().size() - 1)
                .getName().equals("chicken rice100"): "Item name should be chicken rice100";

        assertEquals("chicken rice100", router.menu.getItems().
                get(router.menu.getItems().size() - 1).getName());

    }

    @Test
    public void itemTest3() {
        // max 2dp error
        runTest("/additem -p 20.0001 -n \"chicken rice4\"", router);
        assert router.menu.getItems().
                get(router.menu.getItems().size() - 1)
                .getName().equals("chicken rice100"): "Item name should be chicken rice100";

        assertEquals("chicken rice100", router.menu.getItems().
                get(router.menu.getItems().size() - 1).getName());
    }

    @Test
    public void itemTest4() {
        int index = router.menu.getItems().size() - 1;

        runTest("/updateitem -i " + index + " -n \"chicken rice1000\"", router);
        assert router.menu.getItems().
                get(router.menu.getItems().size() - 1)
                .getName().equals("chicken rice1000"): "Item name should be changed to chicken rice1000";

        assertEquals("chicken rice1000", router.menu.getItems().
                get(router.menu.getItems().size() - 1).getName());
    }

    @Test
    public void itemTest5() {
        int index = router.menu.getItems().size() - 1;

        runTest("/updateitem -i " + index + " -p 2000.001", router);
        assert router.menu.getItems().
                get(router.menu.getItems().size() - 1).getPrice() == 20.1:
                "Item price should be 20.10";

        assertEquals(20.10, router.menu.getItems().
                get(router.menu.getItems().size() - 1).getPrice());
    }

    @Test
    public void itemTest6() {
        int index = router.menu.getItems().size() - 1;

        runTest("/updateitem -i " + index + " -p 123.45", router);
        assert router.menu.getItems().
                get(router.menu.getItems().size() - 1).getPrice() == 123.45:
                "Item price should be 123.45";

        assertEquals(123.45, router.menu.getItems().
                get(router.menu.getItems().size() - 1).getPrice());
    }

    @Test
    public void itemTest7() {
        int index = router.menu.getItems().size() - 1;

        runTest("/updateitem -i " + index + " -p 987.65 -n \"itemTest7 name 1 2 3\"", router);
        assert router.menu.getItems().
                get(router.menu.getItems().size() - 1)
                .getName().equals("itemTest7 name 1 2 3"): "Item name should be changed to itemTest7 name 1 2 3";

        assertEquals("itemTest7 name 1 2 3", router.menu.getItems().
                get(router.menu.getItems().size() - 1).getName());

        assert router.menu.getItems().
                get(router.menu.getItems().size() - 1).getPrice() == 987.65:
                "Item price should be 987.65";

        assertEquals(987.65, router.menu.getItems().
                get(router.menu.getItems().size() - 1).getPrice());
    }

    @Test
    public void itemTest8() {
        int index = router.menu.getItems().size() - 1;
        String newName = router.menu.getItems().
                get(0).getName();
        String currName = router.menu.getItems().
                get(index).getName();
        Double currPrice = router.menu.getItems().
                get(index).getPrice();

        runTest("/updateitem -i " + index + " -p 1.23 -n \"" + newName + "\"", router);
        assert router.menu.getItems().
                get(router.menu.getItems().size() - 1)
                .getName().equals(currName): "Item name should not change due to duplicate.";

        assertEquals(currName, router.menu.getItems().
                get(router.menu.getItems().size() - 1).getName());

        assert router.menu.getItems().
                get(router.menu.getItems().size() - 1).getPrice() == currPrice:
                "Item price should not change as name was duplicate.";

        assertEquals(currPrice, router.menu.getItems().
                get(router.menu.getItems().size() - 1).getPrice());
    }

    @Test
    public void itemTest9() {

        runTest("listitem", router);

        runTest("/deleteitem -i " + (router.menu.getItems().size()-1), router);

        runTest("listitem", router);
    }

}
