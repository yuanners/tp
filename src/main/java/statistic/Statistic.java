package statistic;

import item.Item;
import item.Menu;
import order.Order;
import order.OrderEntry;
import order.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Statistic {

    Date startDate;
    Date endDate;

    public Statistic(String startDateStr) throws ParseException {
        SimpleDateFormat startDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.SECOND, 1);

        this.startDate = startDateFormat.parse(startDateStr);
        this.endDate = calendar.getTime();
    }

    public Statistic(String startDateStr, String endDateStr) throws ParseException {
        SimpleDateFormat startDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat endDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        this.startDate = startDateFormat.parse(startDateStr);
        this.endDate = endDateFormat.parse(endDateStr + " 23:59:59");
    }

    private boolean checkDateRange(Date orderDate) {
        return startDate.compareTo(orderDate) * orderDate.compareTo(endDate) > 0;
    }

    public static double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public double salesReport(Transaction transaction) {
        double totalSales = 0;


        for (Order order : transaction.getOrderList()) {
            Date orderDate = order.getDate();

            Boolean isWithinDateRange = checkDateRange(orderDate);

            if (isWithinDateRange) {
                totalSales += order.getSubTotal();
            }
        }

        return roundToTwoDecimalPlaces(totalSales);
    }

    public PriorityQueue<ItemRank> rankByPopularity(Transaction transaction, Menu menu) {
        Map<String, Integer> itemCountMap = new HashMap<>();
        PriorityQueue<ItemRank> rank = new PriorityQueue<>((a, b) -> b.getQuantity() - a.getQuantity());

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
        PriorityQueue<ItemRank> rank = new PriorityQueue<>(Comparator.comparingDouble(ItemRank::getSales).reversed());

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
            rank.add(new ItemRank(entry.getKey(), roundToTwoDecimalPlaces(entry.getValue())));
        }

        return rank;

    }
}
