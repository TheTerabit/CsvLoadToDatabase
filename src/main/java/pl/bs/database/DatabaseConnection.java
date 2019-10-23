package pl.bs.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

class DatabaseConnection {

    private Connection connection;
    private final Logger logger;

    DatabaseConnection() {
        this.logger = Logger.getLogger(getClass().getName());
    }

    Connection getMySqlConnection() {
        try {
            this.connection = DriverManager.getConnection("jdbc:oracle:thin@//127.0.0.1:3306/sys", "root", "root");
        }
        catch(SQLException e){
            this.logError(e);
        }
        return connection;
    }

    private void logError(Exception e){
        this.logger.log(Level.INFO, e.getMessage());
    }
}
