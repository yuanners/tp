package utility;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.reflect.Type;

import com.opencsv.CSVWriter;

/**
 * Manage the storing and retrival of local files be it JSON or csv.
 */
public class Store {
    private static final String STORE_DIR_PATH = "./store";
    private String storeFilePath;
    private String fileExtension;

    public Store(String fileName) {
        this.storeFilePath = STORE_DIR_PATH + '/' + fileName;

        int index = fileName.lastIndexOf('.');
        this.fileExtension = index > 0 ? fileName.substring(index + 1) : "";
    }

    public void save(Object object) throws IOException {
        Files.createDirectories(Paths.get(STORE_DIR_PATH));
        File file = new File(storeFilePath);
        Parser parser = new Parser();
        String jsonString = parser.jsonStringify(object);

        switch (fileExtension) {
        case "json":
            saveAsJson(jsonString, file);
            break;
        case "csv":
            saveAsCsv(jsonString, file);
            break;
        default:
            //A method that provide a generic save capability
        }


    }

    private void saveAsJson(String jsonString, File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(jsonString);

        fw.flush();
        fw.close();
    }

    private void saveAsCsv(String jsonString, File file) throws IOException {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(new StringReader(jsonString));

        JsonArray dataArray = jsonElement.getAsJsonArray();
        JsonObject firstObject = dataArray.get(0).getAsJsonObject();
        String[] headers = firstObject.keySet().toArray(new String[0]);

        boolean append = file.exists();

        FileWriter fw = new FileWriter(file, append);
        CSVWriter cw = new CSVWriter(fw);

        writeCsvHeader(append, headers, file, cw);

        for (JsonElement element : dataArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            String[] data = new String[headers.length];
            for (int i = 0; i < headers.length; i++) {
                data[i] = jsonObject.get(headers[i]).getAsString();
            }
            cw.writeNext(data);
        }

        cw.close();
        fw.flush();
        fw.close();
    }

    private void writeCsvHeader(boolean append, String[] headers, File file, CSVWriter cw) throws IOException {

        if (!append) {
            cw.writeNext(headers);
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String headerLine = reader.readLine();

            if (!headerLine.equals(String.join(",", headers))) {
                cw.writeNext(headers);
            }

            reader.close();
        }

    }

    public <T> T load(Type type) throws IOException {
        File file = new File(storeFilePath);
        FileReader fr = new FileReader(file);
        Parser parser = new Parser();

        return parser.jsonParse(fr, type);
    }
}
