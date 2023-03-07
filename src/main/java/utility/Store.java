package utility;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        FileWriter fw = new FileWriter(file);
        Parser parser = new Parser();

        String jsonString = parser.jsonStringify(object);
        fw.write(jsonString);

        fw.flush();
        fw.close();
    }

    public <T> T load(Type type) throws IOException {
        File file = new File(storeFilePath);
        FileReader fr = new FileReader(file);
        Parser parser = new Parser();

        return parser.jsonParse(fr, type);
    }
}
