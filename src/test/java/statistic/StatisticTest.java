package statistic;

import app.Command;
import exception.ItemException;
import exception.OrderException;
import item.Menu;
import order.Order;
import order.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.PriorityQueue;


class StatisticTest {

    public Menu menu;
    public Transaction transactions;

    public void createItem(Command c) {
        try {
            menu.addItem(c);
        } catch (ItemException e) {
            System.out.println(e);
        }

    }

    public void createOrder(Command c) {
        try {
            Order order = new Order();
            order.addOrder(c, menu);
            transactions.appendOrder(order);
        } catch (OrderException e) {
            System.out.println(e);
        }
    }

    public void generateTestData() {
        Command c;

        c = new Command("/additem -n \"Chicken rice\" -p 3.00");
        createItem(c);
        c = new Command("/additem -n \"Fish ball noodle\" -p 2.50");
        createItem(c);
        c = new Command("/additem -n \"Mee Rubus\" -p 3.50");
        createItem(c);
        c = new Command("/additem -n \"Fish soup\" -p 5.00");
        createItem(c);
        c = new Command("/additem -n \"Rojak\" -p 2.00");
        createItem(c);
        c = new Command("/additem -n \"Roti Prata\" -p 1.20");
        createItem(c);

        c = new Command("addorder -i 0 -q 2");
        createOrder(c);
        c = new Command("addorder -i 1 -q 3");
        createOrder(c);
        c = new Command("addorder -i 2 -q 30");
        createOrder(c);
        c = new Command("addorder -i 3 -q 4");
        createOrder(c);
        c = new Command("addorder -i 3 -q 10");
        createOrder(c);
        c = new Command("addorder -i 3 -q 10");
        createOrder(c);
        c = new Command("addorder -i 4 -q 4");
        createOrder(c);
        c = new Command("addorder -i 5 -q 4");
        createOrder(c);
    }

    @Test
    void salesReport() {
        this.menu = new Menu();
        this.transactions = new Transaction();


        SalesReport s = new SalesReport("01/03/2023", "19/03/2023");
        double sales = s.totalSales(transactions);

    }

    @Test
    void rankByPopularity() {
        this.menu = new Menu();
        this.transactions = new Transaction();


        RankReport r = new RankReport("19/03/2023", "19/03/2023");
        PriorityQueue<ItemRank> rank = r.rankByPopularity(transactions, menu);

        Chart c = new Chart();
        c.rankByPopularityTable(rank);
    }

    @Test
    void rankBySales() {
        this.menu = new Menu();
        this.transactions = new Transaction();


        RankReport r = new RankReport("01/03/2023", "19/03/2023");
        PriorityQueue<ItemRank> rank = r.rankBySales(transactions, menu);

        Chart c = new Chart();
        c.rankBySalesTable(rank);
    }

    @Test
    void dailySalesReport() {
        this.menu = new Menu();
        this.transactions = new Transaction();

        String strStartDate = "07/03/2023";
        String strEndDate = "15/03/2023";

        SalesReport s = new SalesReport(strStartDate, strEndDate);
        Map<LocalDateTime, Double> dailySalesMap = s.dailySales(transactions);
        System.out.println(dailySalesMap);

        Chart c = new Chart();
        c.dailySalesChart(dailySalesMap, strStartDate, strEndDate);
    }

    @Test
    void annualSalesReport() {
        this.menu = new Menu();
        this.transactions = new Transaction();

        int year = 2023;

        SalesReport s = new SalesReport(year);
        Map<LocalDateTime, Double> monthlySalesMap = s.monthlySales(transactions);
        System.out.println(monthlySalesMap);

        Chart c = new Chart();
        c.monthlySalesChart(monthlySalesMap, year);
    }
}
