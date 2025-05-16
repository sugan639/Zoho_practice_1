

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.util.customexception.ExceptionMessages;
import com.util.customexception.TaskException;

public class DB {

    private static Properties properties;
    private static final String CONFIG_PATH = "db.properties";

    DB db = new DB();
    // Private constructor to prevent instantiation
    public static void main() throws TaskException {
        
    	properties = new Properties();
        System.out.print("Working");
    	try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            properties.load(fis);
        } 
    	
    	catch (IOException e) {
            throw new TaskException(ExceptionMessages.DB_CREDENTIALS_NOT_FOUND + CONFIG_PATH, e);
        }
    }}