package app;

import exception.DuplicateArgumentFoundException;
import utility.Parser;

import java.util.Map;

/**
 * The Command class represents a user command that has been entered by the user.
 * It parses the command and its arguments, and stores them for later use.
 */
public class Command {
    private String userInput;
    private String command;
    private String argumentString;
    private Map<String, String> argumentMap;

    /**
     * Constructs a Command object by parsing the user input string. It uses the Parser class to split the input
     * into command and arguments, and then formats the arguments into a map.
     *
     * @param userInput the input string entered by the user
     */
    public Command(String userInput) throws DuplicateArgumentFoundException {
        assert !userInput.isBlank() : "Command is empty";

        Parser parser = new Parser();
        String[] userInputs = parser.formatInput(userInput);

        this.userInput = userInput;
        this.command = userInputs[0];
        this.argumentString = userInputs[1];

        this.argumentMap = parser.formatArguments(argumentString);
    }

    /**
     * Maps the long and short aliases for an argument. If the short alias already exists, its value is assigned to
     * the long alias. If the long alias already exists, its value is assigned to the short alias.
     *
     * @param longAlias  the long alias of the argument
     * @param shortAlias the short alias of the argument
     */
    public void mapArgumentAlias(String longAlias, String shortAlias) throws DuplicateArgumentFoundException {

        if (argumentMap.containsKey(shortAlias) && argumentMap.containsKey(longAlias)) {
            String longAliasValue = argumentMap.get(longAlias) != null ? argumentMap.get(longAlias) : "";
            String shortAliasValue = argumentMap.get(shortAlias) != null ? argumentMap.get(shortAlias) : "";

            if (!shortAliasValue.equals(longAliasValue)) {
                throw new DuplicateArgumentFoundException();
            }
        }

        if (argumentMap.containsKey(shortAlias)) {
            argumentMap.put(longAlias, argumentMap.get(shortAlias));
        }

        if (argumentMap.containsKey(longAlias)) {
            argumentMap.put(shortAlias, argumentMap.get(longAlias));
        }
    }

    public String getUserInput() {
        return userInput;
    }


    public String getCommand() {
        return command;
    }

    public String getArgumentString() {
        return argumentString;
    }

    public void setArgumentString(String argumentString) {
        this.argumentString = argumentString;
    }

    public Map<String, String> getArgumentMap() {
        return argumentMap;
    }

}
