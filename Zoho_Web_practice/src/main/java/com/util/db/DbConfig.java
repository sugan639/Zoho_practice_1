package com.util.db;


import com.util.customexception.TaskException;

public enum DbConfig {
    INSTANCE;

//    private final Properties properties;

//    // Constructor loads properties file
//    DbConfig() {
//        Properties tempProps = new Properties();
//        String configPath = "db1.properties";
//
//        try (FileInputStream fis = new FileInputStream(configPath)) {
//            tempProps.load(fis);
//        } catch (IOException e) {
//        	System.out.println(e);
//            throw new RuntimeException(ExceptionMessages.DB_CREDENTIALS_NOT_FOUND + configPath, e);
//        }
//
//        this.properties = tempProps;
//    }

    // Getters
    public String getDbUrl() throws TaskException {
        return "jdbc:mysql://localhost:3306/webpracDB";
    }

    public String getDbUser() throws TaskException {
        return "root";
    }

    public String getDbPassword() throws TaskException {
        return "sugankarthick";
    }

    // Helper method to fetch property value
//    private String get(String key) throws TaskException {
//        String value = properties.getProperty(key);
//        if (value == null || value.trim().isEmpty()) {
//            throw new TaskException(ExceptionMessages.DB_KEY_MISSING + ": " + key);
//        }
//        return value;
//    }
}