package statistic;

import app.Command;
import item.Item;
import item.Menu;
import order.Order;
import order.OrderEntry;
import order.Transaction;
import ui.StatisticUi;
import utility.DateUtils;
import utility.Parser;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * This class generates a rank report which includes two priority queues sorted by popularity and sales respectively.
 * It extends the Statistic class and uses its start and end date parameters to generate the report for a specific date range.
 */
public class RankReport extends Statistic {
    /**
     * Constructor for the RankReport class. It generates two priority queues sorted by popularity and sales respectively.
     * It also prints out the result tables using the StatisticUi class.
     *
     * @param command     a Command object containing the start and end date range for the report
     * @param transaction a Transaction object containing the list of orders to generate the report
     * @param menu        a Menu object containing the list of items to be included in the report
     */
    public RankReport(Command command, Transaction transaction, Menu menu) {
        super(command);

        StatisticUi ui = new StatisticUi();

        PriorityQueue<ItemRank> popularityRank = rankByPopularity(transaction, menu);
        PriorityQueue<ItemRank> salesRank = rankBySales(transaction, menu);

        ui.printPopularityRankingTable(popularityRank, super.getStartDate(), super.getEndDate());
        ui.printSalesRankingTable(salesRank, super.getStartDate(), super.getEndDate());


    }

    /**
     * Generates a priority queue of ItemRank objects sorted by popularity.
     * The popularity of an item is determined by the number of times it has been ordered within the date range.
     *
     * @param transaction a Transaction object containing the list of orders to generate the report
     * @param menu        a Menu object containing the list of items to be included in the report
     * @return a priority queue of ItemRank objects sorted by popularity
     */
    public PriorityQueue<ItemRank> rankByPopularity(Transaction transaction, Menu menu) {
        Map<String, Integer> itemCountMap = new HashMap<>();
        PriorityQueue<ItemRank> rank = new PriorityQueue<>(Comparator.comparingDouble(ItemRank::getValue).reversed());

        for (Item item : menu.getItems()) {
            itemCountMap.put(item.getName(), 0);
        }

        for (Order order : transaction.getOrderList()) {

            LocalDateTime orderDate = order.getDateTime();
            Boolean isWithinDateRange = DateUtils.isBetween(orderDate, super.getStartDate(), super.getEndDate());
            Boolean isComplete = order.getStatus().equals("COMPLETED");

            if (isWithinDateRange && isComplete) {
                for (OrderEntry orderEntry : order.getOrderEntries()) {
                    Item item = orderEntry.getItem();
                    int currentCount = itemCountMap.getOrDefault(item.getName(), 0);
                    int quantityCount = orderEntry.getQuantity();

                    itemCountMap.put(item.getName(), currentCount + quantityCount);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : itemCountMap.entrySet()) {
            rank.add(new ItemRank(entry.getKey(), entry.getValue()));
        }

        return rank;
    }

    /**
     * Calculates the ranking of menu items based on total sales during a given date range.
     *
     * @param transaction the transaction to be analyzed
     * @param menu        the menu containing the items to be ranked
     * @return a priority queue of ItemRank objects representing the ranking of items by sales
     */
    public PriorityQueue<ItemRank> rankBySales(Transaction transaction, Menu menu) {
        Map<String, Double> itemSalesMap = new HashMap<>();
        PriorityQueue<ItemRank> rank = new PriorityQueue<>(Comparator.comparingDouble(ItemRank::getValue).reversed());
        Parser parser = new Parser();

        for (Item item : menu.getItems()) {
            itemSalesMap.put(item.getName(), 0.0);
        }

        for (Order order : transaction.getOrderList()) {
            for (OrderEntry orderEntry : order.getOrderEntries()) {
                LocalDateTime orderDate = order.getDateTime();
                Boolean isWithinDateRange = DateUtils.isBetween(orderDate, super.getStartDate(), super.getEndDate());
                Boolean isComplete = order.getStatus().equals("COMPLETED");

                if (isWithinDateRange && isComplete) {
                    Item item = orderEntry.getItem();
                    double currentSales = itemSalesMap.getOrDefault(item.getName(), 0.0);
                    double totalSales = orderEntry.getQuantity() * orderEntry.getItem().getPrice();

                    itemSalesMap.put(item.getName(), currentSales + totalSales);
                }
            }
        }

        for (Map.Entry<String, Double> entry : itemSalesMap.entrySet()) {
            rank.add(new ItemRank(entry.getKey(), parser.roundToTwoDecimalPlaces(entry.getValue())));
        }

        return rank;

    }
}
