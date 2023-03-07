package utility;

import java.util.HashMap;
import java.util.Map;

/**
 * Parse command to object
 * Parse local store to object
 */
public class Parser {
    public Parser() {

    }

    public String[] formatInput(String input) {
        String[] inputs = input.contains(" ") ? input.split(" ", 2) : new String[]{input, ""};
        return inputs;
    }

    public Map<String, String> formatArguments(String argString) {
        Map<String, String> argMap = new HashMap<>();
        String[] args = argString.split("-{1,2}");

        for (int i = 1; i < args.length; i++) {
            String[] keyValue = formatInput(args[i]);
            argMap.put(keyValue[0], keyValue[1]);
        }

        return argMap;
    }

    public String extractStringWithinBraces(String input) {
        int startIndex = input.indexOf('{');
        int endIndex = input.indexOf('}');
        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            return input.substring(startIndex + 1, endIndex);
        } else {
            return null;
        }
    }


}
