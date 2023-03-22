package ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuUiTest {
    @Test
    void printError_Enum() {
        MenuUi ui = new MenuUi();
        ui.printError(UiFlag.ErrorFlag.DUPLICATE_ITEM_NAME_ERROR);

    }
}