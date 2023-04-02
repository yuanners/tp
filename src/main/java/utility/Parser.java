package utility;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import exception.DuplicateArgumentFoundException;


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
    public Map<String, String> formatArguments(String argString) throws DuplicateArgumentFoundException {


        String regex = "(\"[^\"]*\")|(\\[[^\\]]*\\])|(\\S+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(argString);

        ArrayList<String> words = new ArrayList<>();

        while (matcher.find()) {
            words.add(matcher.group());
        }

        String regex2 = "^(-{1,2}(?!\\d)\\D*)$";
        Pattern pattern2 = Pattern.compile(regex2);

        Map<String, String> argMap = new HashMap<>();

        String flag = null;
        for (int i = 0; i < words.size(); i++) {
            boolean isFlag = pattern2.matcher(words.get(i)).matches();
            if (isFlag) {

                flag = words.get(i).replaceFirst("^-+", "");

                if (argMap.containsKey(flag)) {
                    throw new DuplicateArgumentFoundException();
                }

                if (i + 1 < words.size() && !pattern2.matcher(words.get(i + 1)).matches()) {
                    argMap.put(flag, words.get(i + 1).replaceAll("^\"|\"$", ""));
                    i++;
                } else {
                    argMap.put(flag, null);
                }
            }
        }

        return argMap;
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

    public double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}






