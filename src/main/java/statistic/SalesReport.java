package statistic;

import app.Command;
import exception.DuplicateArgumentFoundException;
import exception.statistic.ConflictFlagException;
import exception.statistic.MissingYearException;
import exception.statistic.TypeNotFoundException;
import exception.statistic.StartAfterEndDateException;
import order.Order;
import order.Transaction;
import ui.Flags;
import ui.StatisticUi;
import utility.DateUtils;
import utility.Parser;
import validation.statistic.StatisticValidation;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * The SalesReport class represents a sales report statistic.
 * It extends the Statistic class and provides methods to calculate the total sales,
 * daily sales and monthly sales based on a given transaction and date range.
 */
public class SalesReport extends Statistic {

    private Transaction transaction;

    /**
     * Constructs a SalesReport object based on a given command and transaction.
     *
     * @param command     the command to be used for generating the sales report.
     * @param transaction the transaction to be used for generating the sales report.
     */
    public SalesReport(Command command, StatisticValidation sv, Transaction transaction)
            throws StartAfterEndDateException, ConflictFlagException, DuplicateArgumentFoundException {
        super(command);
        StatisticUi ui = new StatisticUi();
        this.transaction = transaction;

        try {
            double totalSales = totalSales();

            switch (command.getArgumentMap().get("sales")) {
            case "daily":
                Map<LocalDateTime, Double> dailySalesMap = dailySales();
                new Chart().dailySalesChart(dailySalesMap, super.getStartDate(), super.getEndDate(), totalSales);
                break;
            case "monthly":
                sv.validateYearExist();
                Map<LocalDateTime, Double> monthlySalesMap = monthlySales();
                new Chart().monthlySalesChart(monthlySalesMap, super.getYear(), totalSales);
                break;
            default:
                throw new TypeNotFoundException();
            }
        } catch (MissingYearException e) {
            ui.printError(Flags.Error.YEAR_NOT_FOUND);
        } catch (NullPointerException e) {
            ui.printError(Flags.Error.TYPE_NOT_SPECIFIED);
        } catch (TypeNotFoundException e) {
            ui.printError(Flags.Error.TYPE_NOT_FOUND);
        }
    }

    /**
     * Calculates the total sales within a given date range based on a given transaction.
     *
     * @return the total sales within the given date range.
     */
    public double totalSales() {
        double totalSales = 0;

        for (Order order : transaction.getOrderList()) {
            LocalDateTime orderDate = order.getDateTime();

            Boolean isWithinRange = DateUtils.isBetween(orderDate, super.getStartDate(), super.getEndDate());
            Boolean isComplete = order.getStatus().equals("COMPLETED");

            if (isWithinRange && isComplete) {
                totalSales += order.getSubTotal();
            }
        }

        Parser parser = new Parser();
        return parser.roundToTwoDecimalPlaces(totalSales);
    }

    /**
     * Calculates the daily sales within a given date range based on a given transaction.
     *
     * @return a Map with LocalDateTime keys representing the start of each day within the date range,
     *         and Double values representing the total sales for each day.
     */
    public Map<LocalDateTime, Double> dailySales() {
        Map<LocalDateTime, Double> dailySalesMap = new HashMap<>();

        for (Order order : transaction.getOrderList()) {
            LocalDateTime orderDate = order.getDateTime();

            Boolean isWithinDateRange = DateUtils.isBetween(orderDate, super.getStartDate(), super.getEndDate());
            Boolean isComplete = order.getStatus().equals("COMPLETED");

            if (isWithinDateRange && isComplete) {
                LocalDateTime startOrderDate = orderDate.toLocalDate().atStartOfDay();
                double currentTotal = dailySalesMap.getOrDefault(startOrderDate, 0.0);
                dailySalesMap.put(startOrderDate, currentTotal + order.getSubTotal());
            }
        }

        return dailySalesMap;
    }

    /**
     * Calculates the monthly sales within a given date range based on a given transaction.
     *
     * @return a Map with LocalDateTime keys representing the start of each month within the date range,
     *         and Double values representing the total sales for each month.
     */
    public Map<LocalDateTime, Double> monthlySales() {
        Map<LocalDateTime, Double> monthlySalesMap = new HashMap<>();

        for (Order order : transaction.getOrderList()) {
            LocalDateTime orderDate = order.getDateTime();

            Boolean isWithinDateRange = DateUtils.isBetween(orderDate, super.getStartDate(), super.getEndDate());
            Boolean isComplete = order.getStatus().equals("COMPLETED");

            if (isWithinDateRange && isComplete) {
                LocalDateTime orderMonth = DateUtils.getStartOfMonth(orderDate);
                double currentTotal = monthlySalesMap.getOrDefault(orderMonth, 0.0);
                monthlySalesMap.put(orderMonth, currentTotal + order.getSubTotal());
            }
        }

        return monthlySalesMap;
    }
}
