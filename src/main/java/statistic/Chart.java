package statistic;

import ui.StatisticUi;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * This class represents a chart used to display daily or monthly sales data
 */
public class Chart {

    /**
     * This method generates a daily sales chart based on the given daily sales data,
     *      index date, end date, and total sales.
     *
     * @param dailySalesMap a map containing the daily sales data
     * @param indexDate     the start date for the chart
     * @param endDate       the end date for the chart
     * @param totalSales    the total sales for the given time period
     */
    public void dailySalesChart(Map<LocalDateTime,
            Double> dailySalesMap, LocalDateTime indexDate, LocalDateTime endDate, double totalSales) {

        StatisticUi ui = new StatisticUi();

        double totalValue = 0;

        for (double value : dailySalesMap.values()) {
            totalValue += value;
        }

        ui.printDailyBarChartHeader(indexDate, endDate, totalSales);

        while (!indexDate.isAfter(endDate)) {
            double value = dailySalesMap.getOrDefault(indexDate, 0.0);

            double ratio = value / totalValue;
            int numOfBars = (int) (ratio * 100);

            ui.printDailyBarChart(indexDate, numOfBars, value);

            indexDate = indexDate.plusDays(1);
        }

        ui.printBarChartFooter();
    }

    /**
     * This method generates a monthly sales chart based on the given daily sales data,
     * year, and total sales.
     *
     * @param dailySalesMap a map containing the daily sales data
     * @param year          the year for the chart
     * @param totalSales    the total sales for the given year
     */
    public void monthlySalesChart(Map<LocalDateTime, Double> dailySalesMap, int year, double totalSales) {
        LocalDateTime indexDate = LocalDateTime.of(year, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(year, 12, 31, 23, 59, 59, 999999999);

        StatisticUi ui = new StatisticUi();

        double totalValue = 0;

        for (double value : dailySalesMap.values()) {
            totalValue += value;
        }

        ui.printMonthlyChartHeader(year, totalSales);

        while (!indexDate.isAfter(endDate)) {
            double value = dailySalesMap.getOrDefault(indexDate, 0.0);

            double ratio = value / totalValue;
            int numOfBars = (int) (ratio * 100);

            ui.printMonthlyBarChart(indexDate, numOfBars, value);

            indexDate = indexDate.plusMonths(1);
        }

        ui.printBarChartFooter();
    }
}
