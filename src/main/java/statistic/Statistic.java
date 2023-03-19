package statistic;

import utility.DateUtils;

import java.time.LocalDateTime;

public class Statistic {

    LocalDateTime startDate;
    LocalDateTime endDate;

    public Statistic(int year) {
        LocalDateTime startOfYear = LocalDateTime.of(year, 1, 1, 0, 0);
        LocalDateTime endOfYear = LocalDateTime.of(year, 12, 31, 23, 59, 59, 999999999);

        this.startDate = startOfYear;
        this.endDate = endOfYear;
    }

    public Statistic(String strStartDate) {
        LocalDateTime currentDate = LocalDateTime.now().plusSeconds(1);

        this.startDate = DateUtils.stringToDate(strStartDate);
        ;
        this.endDate = currentDate;
    }

    public Statistic(String strStartDate, String strEndDate) {

        this.startDate = DateUtils.stringToDate(strStartDate);
        this.endDate = DateUtils.getEndOfDay(DateUtils.stringToDate(strEndDate));
    }
}
