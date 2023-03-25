package statistic;

import app.Command;
import item.Menu;
import order.Transaction;
import utility.DateUtils;

import java.time.LocalDateTime;

public class Statistic {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private int year;

    public Statistic(Command command) {
        command = mapArguments(command);
        //Validate if inputs are correct

        if (command.getArgumentMap().containsKey("year")) {
            setWholeYear(command.getArgumentMap().get("year"));
        } else {
            String strStartDate = command.getArgumentMap().get("from");
            String strEndDate = command.getArgumentMap().get("to");
            setDateRange(strStartDate, strEndDate);
        }
    }

    public void setWholeYear(String strYear) {
        int year = Integer.parseInt(strYear);
        LocalDateTime startOfYear = LocalDateTime.of(year, 1, 1, 0, 0);
        LocalDateTime endOfYear = LocalDateTime.of(year, 12, 31, 23, 59, 59, 999999999);

        this.year = year;
        this.startDate = startOfYear;
        this.endDate = endOfYear;
    }

    public void setDateRange(String strStartDate, String strEndDate) {
        this.startDate = DateUtils.stringToDate(strStartDate);
        this.endDate = DateUtils.getEndOfDay(DateUtils.stringToDate(strEndDate));
    }

    public Command mapArguments(Command command) {
        command.mapArgumentAlias("year", "y");
        command.mapArgumentAlias("from", "f");
        command.mapArgumentAlias("to", "t");
        command.mapArgumentAlias("mode", "m");

        return command;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public int getYear() {
        return year;
    }
}
