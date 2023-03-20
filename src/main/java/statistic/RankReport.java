package statistic;

import item.Item;
import item.Menu;
import order.Order;
import order.OrderEntry;
import order.Transaction;
import utility.Parser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class RankReport extends Statistic {
    public RankReport(int year) {
        super(year);
    }
    public RankReport(String strStartDate, String strEndDate) {
        super(strStartDate, strEndDate);
    }

    public PriorityQueue<ItemRank> rankByPopularity(Transaction transaction, Menu menu) {
        Map<String, Integer> itemCountMap = new HashMap<>();
        PriorityQueue<ItemRank> rank = new PriorityQueue<>(Comparator.comparingDouble(ItemRank::getValue).reversed());

        for (Item item : menu.getItems()) {
            itemCountMap.put(item.getName(), 0);
        }

        for (Order order : transaction.getOrderList()) {
            for (OrderEntry orderEntry : order.getOrderEntries()) {
                Item item = orderEntry.getItem();
                int currentCount = itemCountMap.getOrDefault(item.getName(), 0);
                int quantityCount = orderEntry.getQuantity();

                itemCountMap.put(item.getName(), currentCount + quantityCount);
            }
        }

        for (Map.Entry<String, Integer> entry : itemCountMap.entrySet()) {
            rank.add(new ItemRank(entry.getKey(), entry.getValue()));
        }

        return rank;
    }


    public PriorityQueue<ItemRank> rankBySales(Transaction transaction, Menu menu) {
        Map<String, Double> itemSalesMap = new HashMap<>();
        PriorityQueue<ItemRank> rank = new PriorityQueue<>(Comparator.comparingDouble(ItemRank::getValue).reversed());
        Parser parser = new Parser();

        for (Item item : menu.getItems()) {
            itemSalesMap.put(item.getName(), 0.0);
        }

        for (Order order : transaction.getOrderList()) {
            for (OrderEntry orderEntry : order.getOrderEntries()) {
                Item item = orderEntry.getItem();
                double currentSales = itemSalesMap.getOrDefault(item.getName(), 0.0);
                double totalSales = orderEntry.getQuantity() * orderEntry.getItem().getPrice();

                itemSalesMap.put(item.getName(), currentSales + totalSales);
            }
        }

        for (Map.Entry<String, Double> entry : itemSalesMap.entrySet()) {
            rank.add(new ItemRank(entry.getKey(), parser.roundToTwoDecimalPlaces(entry.getValue())));
        }

        return rank;

    }
}
