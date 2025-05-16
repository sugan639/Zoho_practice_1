package com.util.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.util.customexception.ExceptionMessages;
import com.util.customexception.TaskException;
import com.util.db.DbConfig;

public enum ConnectionPool {
    INSTANCE;

    private Connection connection;

    // Private constructor
    private ConnectionPool() {
       
    }

    // Lazy load connection
    public Connection getConnection() throws TaskException {
        if (this.connection != null && !isClosed()) {
            return connection; // Already connected
        }

        else {
        	
        try {
        	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            this.connection = DriverManager.getConnection(
                DbConfig.INSTANCE.getDbUrl(),
                DbConfig.INSTANCE.getDbUser(),
                DbConfig.INSTANCE.getDbPassword()
               
            );
            return connection;
         
        } catch (SQLException e) {
            throw new TaskException(ExceptionMessages.CONNECTION_FAILED, e);
        }
        }
    }

    
    // Helper: check if connection is closed
    private boolean isClosed() {
        try {
            return this.connection == null || this.connection.isClosed();
        } catch (SQLException e) {
            return true; // Assume closed on error
        }
    }
}