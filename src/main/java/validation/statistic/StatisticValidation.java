package validation.statistic;

import app.Command;
import exception.statistic.ConflictFlagException;
import exception.statistic.MissingRequiredFlagException;
import exception.statistic.MissingYearException;
import validation.Validation;

public class StatisticValidation extends Validation {
    private Command command;

    public StatisticValidation(Command command) {
        this.command = command;
    }

    public void validateRequiredFlag() throws MissingRequiredFlagException {
        if (!command.getArgumentMap().containsKey("sales") && !command.getArgumentMap().containsKey("rank")) {
            throw new MissingRequiredFlagException();
        }

        boolean yearExist = command.getArgumentMap().containsKey("year");
        boolean fromExist = command.getArgumentMap().containsKey("from");
        boolean toExist = command.getArgumentMap().containsKey("to");

        if (!yearExist && (!fromExist || !toExist)) {
            throw new MissingRequiredFlagException();
        }

    }

    public void validateConflictFlag() throws ConflictFlagException {
        boolean yearExist = command.getArgumentMap().containsKey("year");
        boolean fromExist = command.getArgumentMap().containsKey("from");
        boolean toExist = command.getArgumentMap().containsKey("to");
        boolean salesExist = command.getArgumentMap().containsKey("sales");
        boolean rankExist = command.getArgumentMap().containsKey("rank");

        if (yearExist && (fromExist || toExist)) {
            throw new ConflictFlagException();
        }

        if(salesExist && rankExist){
            throw new ConflictFlagException();
        }
    }

    public void validateYearExist() throws MissingYearException {
        if (!command.getArgumentMap().containsKey("year")) {
            throw new MissingYearException();
        }
    }
}
