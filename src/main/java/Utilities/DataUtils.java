package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtils {

    public final static String TEST_DATA_PATH = "src/test/resources/TestData/";

    public static String getJsonData(String jsonFilename, String field) {
        try {
            // define object of file reader
            FileReader reader = new FileReader(TEST_DATA_PATH + jsonFilename + ".json");

            // PARSE THE json directly into a jsonElement
            JsonElement jsonElement = JsonParser.parseReader(reader);
            return jsonElement.getAsJsonObject().get(field).getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getPropertyValue(String fileName, String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(TEST_DATA_PATH + fileName + ".properties"));
        return properties.getProperty(key);


    }


}
