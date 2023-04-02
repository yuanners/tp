package utility;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.reflect.Type;

import exception.FileIsEmptyException;

/**
 * This class provides functionality for managing the storage and retrieval of local files,
 * whether they are in JSON or CSV format.
 */
public class Store {
    private static final String STORE_DIR_PATH = "./datastore";
    private String storeFilePath;

    /**
     * Constructs a new Store object with the specified file name.
     *
     * @param fileName the name of the file to be stored or retrieved
     */
    public Store(String fileName) {
        this.storeFilePath = STORE_DIR_PATH + '/' + fileName;
    }

    public Store(String dirName, String fileName) {
        this.storeFilePath = dirName + '/' + fileName;
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

        saveAsJson(jsonString, file);
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
     * Loads an object from a file in JSON format.
     *
     * @param type the Type of the object to be loaded
     * @return the loaded object of the specified Type
     * @throws IOException if an I/O error occurs while reading the file
     */
    public <T> T load(Type type) throws IOException, FileIsEmptyException {
        File file = new File(storeFilePath);

        boolean isEmpty = Files.readString(Paths.get(storeFilePath)).trim().isEmpty();
        if (isEmpty) {
            throw new FileIsEmptyException();
        }

        FileReader fr = new FileReader(file);
        Parser parser = new Parser();

        return parser.jsonParse(fr, type);
    }
}
