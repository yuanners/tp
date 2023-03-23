package ui;

import org.junit.jupiter.api.Test;

class MenuUiTest {
    @Test
    void printError_Enum() {
        MenuUi ui = new MenuUi();
        ui.printError(Flags.Error.DUPLICATE_ITEM_NAME_ERROR);

    }
}