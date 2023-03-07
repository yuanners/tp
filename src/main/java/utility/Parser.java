package utility;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;


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

    public String jsonStringify(Object object){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

            return gson.toJson(object);


    }

    public <T> T jsonParse(FileReader fr, Class<T> type) throws JsonParseException{
        Gson gson = new Gson();
        return gson.fromJson(fr, type);
    }
}






