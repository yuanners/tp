package statistic;

import app.Command;
import exception.DuplicateArgumentFoundException;
import exception.statistic.ConflictFlagException;
import exception.statistic.MissingRequiredFlagException;
import exception.statistic.StartAfterEndDateException;
import item.Menu;
import order.Transaction;
import ui.Flags;
import ui.StatisticUi;
import utility.DateUtils;
import validation.statistic.StatisticValidation;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Statistic {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private int year;

    public Statistic(Command command) throws StartAfterEndDateException,
            ConflictFlagException, DuplicateArgumentFoundException {
        command = mapArguments(command);

        StatisticValidation sv = new StatisticValidation(command);
        sv.validateConflictFlag();

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

    public void setDateRange(String strStartDate, String strEndDate) throws StartAfterEndDateException {
        this.startDate = DateUtils.stringToDate(strStartDate);
        this.endDate = DateUtils.getEndOfDay(DateUtils.stringToDate(strEndDate));
        if (!endDate.isAfter(startDate)) {
            throw new StartAfterEndDateException();
        }
    }

    public static Command mapArguments(Command command) throws DuplicateArgumentFoundException {
        command.mapArgumentAlias("sales", "s");
        command.mapArgumentAlias("rank", "r");
        command.mapArgumentAlias("year", "y");
        command.mapArgumentAlias("from", "f");
        command.mapArgumentAlias("to", "t");

        return command;
    }

    public static void handleStatisticRoute(Command command, Transaction transactions, Menu menu)
            throws DuplicateArgumentFoundException {

        command = mapArguments(command);
        StatisticValidation sv = new StatisticValidation(command);
        StatisticUi ui = new StatisticUi();

        try {
            sv.validateRequiredFlag();

            if (command.getArgumentMap().containsKey("rank")) {
                new RankReport(command, transactions, menu);
            }
            if (command.getArgumentMap().containsKey("sales")) {
                new SalesReport(command, sv, transactions);
            }

        } catch (NumberFormatException e) {
            ui.printError(Flags.Error.INVALID_YEAR_FORMAT);
        } catch (DateTimeParseException e) {
            ui.printError(Flags.Error.INVALID_DATE_FORMAT);
        } catch (StartAfterEndDateException e) {
            ui.printError(Flags.Error.DATE_RANGE_INVALID);
        } catch (ConflictFlagException e) {
            ui.printError(Flags.Error.CONFLICT_FLAG);
        } catch (MissingRequiredFlagException e) {
            ui.printError(Flags.Error.REQUIRED_FLAG_MISSING);
        }
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
