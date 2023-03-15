package utility;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;


public class Parser {

    /**
     * Formats the user input into an array of two strings.
     * inputs[0] = first word in the string
     * inputs[1] = remaining word(s) in the string
     *
     * @param input the user input string
     * @return a string array containing the command and argument string
     */
    public String[] formatInput(String input) {
        String[] inputs = input.contains(" ") ? input.split(" ", 2) : new String[]{input, ""};
        return inputs;
    }

    /**
     * Formats the argument string into a map of key-value pairs.
     * Example: -x some_value --another_key another_value
     * produce the map ==> {k: some_value, another_key: another_value}
     *
     * @param argString the argument string
     * @return a map of argument key-value pairs
     */

    public Map<String, String> formatArguments(String argString) {


        String regex =
                "(?:^|\\s)(?:--|-)(\\w+)(?:\\s+(-?[\\d.]\\w+)|\\s+'([^']*)'|\\s+" +
                        "\"([^\"]*)\"|\\s*([^\\s-][^\\s]*)|\\s*(?=--|-|$))?";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(argString);

        Map<String, String> argMap = new HashMap<>();

        while (matcher.find()) {
            String flag = matcher.group(1);
            String value = "";
            if (matcher.group(2) != null) {
                value = matcher.group(2);
            } else if (matcher.group(3) != null) {
                value = matcher.group(3);
            } else if (matcher.group(4) != null) {
                value = matcher.group(4);
            } else if (matcher.group(5) != null) {
                value = matcher.group(5);
            }
            argMap.put(flag, value);
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

    /**
     * Converts an object to its corresponding JSON string.
     *
     * @param object the object to be converted
     * @return the JSON string representation of the object
     */
    public String jsonStringify(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);


    }

    /**
     * Parses a JSON file into an object of the specified type.
     *
     * @param fr   the FileReader object representing the JSON file
     * @param type the Type object representing the type of the object to be parsed
     * @return an object of the specified type
     * @throws JsonParseException if the JSON is not formatted correctly
     */
    public <T> T jsonParse(FileReader fr, Type type) throws JsonParseException {
        Gson gson = new Gson();
        return gson.fromJson(fr, type);
    }

}






