package statistic;

import order.Order;
import order.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Statistic {

    Date startDate;
    Date endDate;

    public Statistic(String startDateStr) throws ParseException{
        SimpleDateFormat startDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.SECOND, 1);

        this.startDate = startDateFormat.parse(startDateStr);
        this.endDate = calendar.getTime();
    }
    public Statistic(String startDateStr, String endDateStr) throws ParseException{
        SimpleDateFormat startDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat endDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        this.startDate = startDateFormat.parse(startDateStr);
        this.endDate = endDateFormat.parse(endDateStr + " 23:59:59");
    }

    private boolean checkDateRange(Date orderDate) {
        return startDate.compareTo(orderDate) * orderDate.compareTo(endDate) > 0;
    }

    public double salesReport(Transaction transaction) throws ParseException{
        double totalSales = 0;

        for (Order order : transaction.getOrderList()) {
            Date orderDate = order.getDate();

            System.out.println(orderDate);

            Boolean isWithinDateRange = checkDateRange(orderDate);

            if (isWithinDateRange) {
                totalSales += order.getSubTotal();
            }
        }

        return totalSales;
    }
}
