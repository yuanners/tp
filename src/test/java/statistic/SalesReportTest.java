package statistic;

import app.Command;
import exception.statistic.ConflictFlagException;
import exception.statistic.StartAfterEndDateException;
import item.Menu;
import order.Transaction;
import org.junit.jupiter.api.Test;
import validation.statistic.StatisticValidation;

import static org.junit.jupiter.api.Assertions.*;

class SalesReportTest {

    public Menu menu;
    public Transaction transactions;

    @Test
    void dailySales() {
        this.menu = new Menu("./datastore-test", "menu-test.json");
        this.transactions = new Transaction("./datastore-test", "orders-test.json");

        try {
            Command c = new Command("/report -s daily -f 01/03/2023 -t 01/07/2023");
            StatisticValidation sv = new StatisticValidation(c);
            SalesReport sr = new SalesReport(c, sv, transactions);

            assertEquals(101.4, sr.totalSales());
        } catch (StartAfterEndDateException | ConflictFlagException e) {
            assertTrue(false);
        }
    }

    @Test
    void monthlySales() {
        this.menu = new Menu("./datastore-test", "menu-test.json");
        this.transactions = new Transaction("./datastore-test", "orders-test.json");

        try {
            Command c = new Command("/report -s monthly -y 2023");
            StatisticValidation sv = new StatisticValidation(c);
            SalesReport sr = new SalesReport(c, sv, transactions);

            assertEquals(286.5, sr.totalSales());
        } catch (StartAfterEndDateException | ConflictFlagException e) {
            System.out.println("ERROR");
        }
    }
}