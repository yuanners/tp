package statistic;

import order.Order;
import order.Transaction;
import utility.DateUtils;
import utility.Parser;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class SalesReport extends Statistic {
    public SalesReport(int year) {
        super(year);
    }

    public SalesReport(String strStartDate, String strEndDate) {
        super(strStartDate, strEndDate);
    }

    public double totalSales(Transaction transaction) {
        double totalSales = 0;

        for (Order order : transaction.getOrderList()) {
            LocalDateTime orderDate = order.getDateTime();

            Boolean isWithinRange = DateUtils.isBetween(orderDate, startDate, endDate);

            if (isWithinRange) {
                totalSales += order.getSubTotal();
            }
        }

        Parser parser = new Parser();
        return parser.roundToTwoDecimalPlaces(totalSales);
    }

    public Map<LocalDateTime, Double> dailySales(Transaction transaction) {
        Map<LocalDateTime, Double> dailySalesMap = new HashMap<>();

        for (Order order : transaction.getOrderList()) {
            LocalDateTime orderDate = order.getDateTime();

            Boolean isWithinDateRange = DateUtils.isBetween(orderDate, startDate, endDate);

            if (isWithinDateRange) {
                LocalDateTime startOrderDate = orderDate.toLocalDate().atStartOfDay();
                double currentTotal = dailySalesMap.getOrDefault(startOrderDate, 0.0);
                dailySalesMap.put(startOrderDate, currentTotal + order.getSubTotal());
            }
        }

        return dailySalesMap;
    }

    public Map<LocalDateTime, Double> monthlySales(Transaction transaction) {
        Map<LocalDateTime, Double> monthlySalesMap = new HashMap<>();

        for (Order order : transaction.getOrderList()) {
            LocalDateTime orderDate = order.getDateTime();

            Boolean isWithinDateRange = DateUtils.isBetween(orderDate, startDate, endDate);

            if (isWithinDateRange) {
                LocalDateTime orderMonth = DateUtils.getStartOfMonth(orderDate);
                double currentTotal = monthlySalesMap.getOrDefault(orderMonth, 0.0);
                monthlySalesMap.put(orderMonth, currentTotal + order.getSubTotal());
            }
        }

        return monthlySalesMap;
    }
}
