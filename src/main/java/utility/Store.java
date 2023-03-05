package utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Manage the storing and retrival of local files be it JSON or csv.
 */
public class Store {
    private final static String STORE_DIR_PATH = "./store";
    private String storeFilePath;

    public Store(String fileName) {
        this.storeFilePath = STORE_DIR_PATH + '/' + fileName;
    }

    public void save() throws IOException {
        Files.createDirectories(Paths.get(STORE_DIR_PATH));
        File file = new File(storeFilePath);
        FileWriter fw = new FileWriter(file);

        //Perform parser and save to file in a consistent format

        fw.flush();
        fw.close();
    }

    /**
     * Method return type need to be changed.
     * Void is the just a temporary solution.
     *
     * @throws IOException
     */
    public void load() throws IOException {
        File file = new File(storeFilePath);
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\n");

        //Perform parser and load file to the corresponding object

        sc.close();
    }

}
