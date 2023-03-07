package app;

import utility.Parser;

import java.util.Map;

public class Command {
    private String userInput;
    private String command;
    private String argumentString;
    private Map<String, String> argumentMap;

    public Command(String userInput) {
        Parser parser = new Parser();
        String[] userInputs = parser.formatInput(userInput);

        this.userInput = userInput;
        this.command = userInputs[0];
        this.argumentString = userInputs[1];

        this.argumentMap = parser.formatArguments(argumentString);
    }

    public void duplicateArgument(String longVersion, String shortVersion) {
        if (argumentMap.containsKey(shortVersion)) {
            argumentMap.put(longVersion, argumentMap.get(shortVersion));
        } else {
            argumentMap.put(shortVersion, argumentMap.get(longVersion));
        }
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
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

    public void setArgumentMap(Map<String, String> argumentMap) {
        this.argumentMap = argumentMap;
    }
}
