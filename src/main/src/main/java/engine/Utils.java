package engine;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Jimmy Deng, June 07, 2019
 * Utils class provides a method to retrieve the two shader files under resources folder
 */
public class Utils {


    public static String loadResource(String fileName) throws Exception {
        String result;
        try (InputStream in = Class.forName(Utils.class.getName()).getResourceAsStream(fileName);
             Scanner scanner = new Scanner(in, "UTF-8")) {
            result = scanner.useDelimiter("\\A").next();
        }
        return result;
    }

}