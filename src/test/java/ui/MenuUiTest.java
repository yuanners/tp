package ui;

import org.junit.jupiter.api.Test;

class MenuUiTest {
    @Test
    void printErrorEnum() {
        MenuUi ui = new MenuUi();
        ui.printError(Flags.Error.ITEM_DUPLICATE_NAME_ERROR);
    }
}
