package statistic;

import app.Command;
import exception.DuplicateArgumentFoundException;
import exception.statistic.ConflictFlagException;
import exception.statistic.StartAfterEndDateException;
import item.Menu;
import order.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RankReportTest {
    public Menu menu;
    public Transaction transactions;

    @Test
    void rankByPopularity() {
        this.menu = new Menu("./datastore-test", "menu-test.json");
        this.transactions = new Transaction("./datastore-test", "orders-test.json");

        try {
            Command c = new Command("/report -r popular -y 2023");
            RankReport rr = new RankReport(c, transactions, menu);
            ItemRank ir = rr.rankByPopularity().poll();

            assertEquals("Teh Tarik", ir.getName());
            assertEquals(26, ir.getValue());
        } catch (StartAfterEndDateException | ConflictFlagException e) {
            System.out.println("ERROR");
        } catch (DuplicateArgumentFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void rankBySales() {
        this.menu = new Menu("./datastore-test", "menu-test.json");
        this.transactions = new Transaction("./datastore-test", "orders-test.json");

        try {
            Command c = new Command("/report -r sales -f 01/03/2023 -t 01/07/2023");
            RankReport rr = new RankReport(c, transactions, menu);
            ItemRank ir = rr.rankBySales().poll();

            assertEquals("Fish Head Curry", ir.getName());
            assertEquals(25.0, ir.getValue());
        } catch (StartAfterEndDateException | ConflictFlagException | DuplicateArgumentFoundException e) {
            System.out.println("ERROR");
        }
    }
}
