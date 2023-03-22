//package seedu.moneygowhere;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import app.Command;
//import app.MoneyGoWhere;
//import org.junit.jupiter.api.Test;
//import utility.Ui;
//
//class ItemTest {
//    MoneyGoWhere moneyGoWhere;
//
//    public ItemTest() {
//        moneyGoWhere = new MoneyGoWhere();
//        runTest("/additem -p 2.50 -n \"chicken rice\"", moneyGoWhere);
//    }
//
//    public void runTest(String input, MoneyGoWhere moneyGoWhere) {
//
//        Ui ui = new Ui();
//
//
//        ui.promptUserInput();
//        String userInput = input;
//
//        Command command = new Command(userInput);
//
//        moneyGoWhere.handleCommand(command);
//
//    }
//
//    @Test
//    public void itemTest() {
//
//        runTest("/additem -p 20.1 -n \"chicken rice100\"", moneyGoWhere);
//        assertEquals("chicken rice100", moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1).getName());
//
//        assert moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1)
//                .getName().equals("chicken rice100"): "Item name should be chicken rice100";
//
//        assertEquals(20.10, moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1).getPrice());
//
//    }
//
//    @Test
//    public void itemTest2() {
//        runTest("/additem -p 2kuku0.01 -n \"chicken rice3\"", moneyGoWhere);
//        assert moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1)
//                .getName().equals("chicken rice100"): "Item name should be chicken rice100";
//
//        assertEquals("chicken rice100", moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1).getName());
//
//    }
//
//    @Test
//    public void itemTest3() {
//        // max 2dp error
//        runTest("/additem -p 20.0001 -n \"chicken rice4\"", moneyGoWhere);
//        assert moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1)
//                .getName().equals("chicken rice100"): "Item name should be chicken rice100";
//
//        assertEquals("chicken rice100", moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1).getName());
//    }
//
//    @Test
//    public void itemTest4() {
//        int index = moneyGoWhere.menu.getItems().size() - 1;
//
//        runTest("/updateitem -i " + index + " -n \"chicken rice1000\"", moneyGoWhere);
//        assert moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1)
//                .getName().equals("chicken rice1000"): "Item name should be changed to chicken rice1000";
//
//        assertEquals("chicken rice1000", moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1).getName());
//    }
//
//    @Test
//    public void itemTest5() {
//        int index = moneyGoWhere.menu.getItems().size() - 1;
//
//        runTest("/updateitem -i " + index + " -p 2000.001", moneyGoWhere);
//        assert moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1).getPrice() == 20.1:
//                "Item price should be 20.10";
//
//        assertEquals(20.10, moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1).getPrice());
//    }
//
//    @Test
//    public void itemTest6() {
//        int index = moneyGoWhere.menu.getItems().size() - 1;
//
//        runTest("/updateitem -i " + index + " -p 123.45", moneyGoWhere);
//        assert moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1).getPrice() == 123.45:
//                "Item price should be 123.45";
//
//        assertEquals(123.45, moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1).getPrice());
//    }
//
//    @Test
//    public void itemTest7() {
//        int index = moneyGoWhere.menu.getItems().size() - 1;
//
//        runTest("/updateitem -i " + index + " -p 987.65 -n \"itemTest7 name 1 2 3\"", moneyGoWhere);
//        assert moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1)
//                .getName().equals("itemTest7 name 1 2 3"): "Item name should be changed to itemTest7 name 1 2 3";
//
//        assertEquals("itemTest7 name 1 2 3", moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1).getName());
//
//        assert moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1).getPrice() == 987.65:
//                "Item price should be 987.65";
//
//        assertEquals(987.65, moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1).getPrice());
//    }
//
//    @Test
//    public void itemTest8() {
//        int index = moneyGoWhere.menu.getItems().size() - 1;
//        String newName = moneyGoWhere.menu.getItems().
//                get(0).getName();
//        String currName = moneyGoWhere.menu.getItems().
//                get(index).getName();
//        Double currPrice = moneyGoWhere.menu.getItems().
//                get(index).getPrice();
//
//        runTest("/updateitem -i " + index + " -p 1.23 -n \"" + newName + "\"", moneyGoWhere);
//        assert moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1)
//                .getName().equals(currName): "Item name should not change due to duplicate.";
//
//        assertEquals(currName, moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1).getName());
//
//        assert moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1).getPrice() == currPrice:
//                "Item price should not change as name was duplicate.";
//
//        assertEquals(currPrice, moneyGoWhere.menu.getItems().
//                get(moneyGoWhere.menu.getItems().size() - 1).getPrice());
//    }
//
//    @Test
//    public void itemTest9() {
//
//        runTest("listitem", moneyGoWhere);
//
//        runTest("/deleteitem -i " + (moneyGoWhere.menu.getItems().size()-1), moneyGoWhere);
//
//        runTest("listitem", moneyGoWhere);
//    }
//
//}
