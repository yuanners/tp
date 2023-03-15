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
 * This class provides functionality for managing the storage and retrieval of local files,
 * whether they are in JSON or CSV format.
 */
public class Store {
    private static final String STORE_DIR_PATH = "./datastore";
    private String storeFilePath;
    private String fileExtension;

    /**
     * Constructs a new Store object with the specified file name.
     *
     * @param fileName the name of the file to be stored or retrieved
     */
    public Store(String fileName) {
        this.storeFilePath = STORE_DIR_PATH + '/' + fileName;

        int index = fileName.lastIndexOf('.');
        this.fileExtension = index > 0 ? fileName.substring(index + 1) : "";
    }

    /**
     * Saves the specified object to a file.
     *
     * @param object the object to be saved
     * @throws IOException if an I/O error occurs while saving the object to the file
     */
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

    /**
     * Saves the specified JSON string to a file in JSON format.
     *
     * @param jsonString the JSON string to be saved
     * @param file       the file to which the JSON string should be saved
     * @throws IOException if an I/O error occurs while saving the JSON string to the file
     */
    private void saveAsJson(String jsonString, File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(jsonString);

        fw.flush();
        fw.close();
    }


    /**
     * Saves the specified JSON string to a file in CSV format.
     *
     * @param jsonString the JSON string to be saved
     * @param file       the file to which the CSV data should be saved
     * @throws IOException if an I/O error occurs while saving the CSV data to the file
     */
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

    /**
     * Writes the headers to a CSV file if header is incorrect or does not exist
     *
     * @param append  a boolean flag indicating whether to append to an existing file
     * @param headers an array of header strings to be written to the file
     * @param file    the file to which the headers should be written
     * @param cw      a CSVWriter object used to write to the file
     * @throws IOException if an I/O error occurs while writing the headers to the file
     */
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

    /**
     * Loads an object from a file in JSON format.
     *
     * @param type the Type of the object to be loaded
     * @return the loaded object of the specified Type
     * @throws IOException if an I/O error occurs while reading the file
     */
    public <T> T load(Type type) throws IOException {
        File file = new File(storeFilePath);
        FileReader fr = new FileReader(file);
        Parser parser = new Parser();

        return parser.jsonParse(fr, type);
    }
}
