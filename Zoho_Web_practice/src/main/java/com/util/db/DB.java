package com.util.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.util.customexception.ExceptionMessages;
import com.util.customexception.TaskException;

public class DB {

    private final Properties properties;
    private static final String CONFIG_PATH = "db.properties";

    // Private constructor to prevent instantiation
    public DB() throws TaskException {
        
    	properties = new Properties();
        System.out.print("Hello");
    	try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            properties.load(fis);
        } 
    	
    	catch (IOException e) {
    		System.out.print("Here is the problem");
            throw new TaskException(ExceptionMessages.DB_CREDENTIALS_NOT_FOUND + CONFIG_PATH, e);
        }
    }}