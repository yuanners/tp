package statistic;

import app.Command;
import exception.DuplicateArgumentFoundException;
import exception.statistic.StartAfterEndDateException;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertThrows;


class StatisticTest {

    @Test
    void setWholeYear() throws DuplicateArgumentFoundException {
        Command c1 = new Command("/report -s monthly -y ABC");
        assertThrows(NumberFormatException.class, () -> new Statistic(c1));
    }

    @Test
    void setDateRange() throws DuplicateArgumentFoundException {
        Command c1 = new Command("/report -s daily -f 01/02/2023 -t 01/02/2022");
        assertThrows(StartAfterEndDateException.class, () -> new Statistic(c1));

        Command c2 = new Command("/report -s daily -f ABC -t DEF");
        assertThrows(DateTimeParseException.class, () -> new Statistic(c2));
    }
}
