package statistic;

import org.apache.commons.lang3.StringUtils;
import utility.DateUtils;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.PriorityQueue;

public class Chart {
    public void dailySalesChart(Map<LocalDateTime, Double> dailySalesMap, String strStartDate, String strEndDate) {
        LocalDateTime indexDate = DateUtils.stringToDate(strStartDate);
        LocalDateTime endDate = DateUtils.stringToDate(strEndDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat df = new DecimalFormat("#0.00");

        double totalValue = 0;

        for (double value : dailySalesMap.values()) {
            totalValue += value;
        }

        while (!indexDate.isAfter(endDate)) {
            double value = dailySalesMap.getOrDefault(indexDate, 0.0);

            double ratio = value / totalValue;
            int numOfBars = (int) (ratio * 100);

            String formattedDate = indexDate.format(formatter);

            System.out.print(formattedDate + "\t");
            System.out.print(StringUtils.repeat("|", numOfBars));

            System.out.println(" $" + df.format(value));

            indexDate = indexDate.plusDays(1);
        }
    }

    public void monthlySalesChart(Map<LocalDateTime, Double> dailySalesMap, int year) {
        LocalDateTime indexDate = LocalDateTime.of(year, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(year, 12, 31, 23, 59, 59, 999999999);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
        DecimalFormat df = new DecimalFormat("#0.00");

        double totalValue = 0;

        for (double value : dailySalesMap.values()) {
            totalValue += value;
        }

        while (!indexDate.isAfter(endDate)) {
            double value = dailySalesMap.getOrDefault(indexDate, 0.0);

            double ratio = value / totalValue;
            int numOfBars = (int) (ratio * 100);

            String formattedDate = indexDate.format(formatter);

            System.out.print(formattedDate + "\t");
            System.out.print(StringUtils.repeat("|", numOfBars));
            System.out.println(" $" + df.format(value));

            indexDate = indexDate.plusMonths(1);
        }
    }

    public void rankByPopularityTable(PriorityQueue<ItemRank> rank) {
        System.out.printf("| %-5s | %-25s | %-5s |\n", "Rank", "Name", "Count");
        System.out.println("| " + "-".repeat(5) + " | " + "-".repeat(25) + " | " + "-".repeat(5) + " |");

        int index = 1;

        while (!rank.isEmpty()) {
            ItemRank element = rank.poll();
            System.out.printf("| %-5d | %-25s | %-5d |\n", index, element.getName(), (int) element.getValue());
            index++;
        }
    }

    public void rankBySalesTable(PriorityQueue<ItemRank> rank) {
        System.out.printf("| %-5s | %-25s | %-10s |\n", "Rank", "Name", "Sales($)");
        System.out.println("| " + "-".repeat(5) + " | " + "-".repeat(25) + " | " + "-".repeat(10) + " |");

        int index = 1;

        while (!rank.isEmpty()) {
            ItemRank element = rank.poll();
            System.out.printf("| %-5d | %-25s | %-10.2f |\n", index, element.getName(), element.getValue());
            index++;
        }
    }
}
