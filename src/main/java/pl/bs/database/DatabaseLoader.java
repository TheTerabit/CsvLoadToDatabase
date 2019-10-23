package pl.bs.database;

import pl.bs.models.CsvContainer;
import pl.bs.models.Record;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseLoader {

    private final Connection connection;
    private final StringBuilder stringBuilder;
    private final Logger logger;

    public DatabaseLoader() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        this.connection = databaseConnection.getMySqlConnection();
        this.stringBuilder = new StringBuilder();
        this.logger = Logger.getLogger(getClass().getName());
    }

    public void load(CsvContainer csvContainer) {
        try {
            Statement stmt = this.connection.createStatement();
            stmt.execute(this.createQuery(csvContainer));
        }
        catch (SQLException e){
            this.logError(e);
        }
    }

    private String createQuery(CsvContainer csvContainer){
        this.stringBuilder.setLength(0);
        this.stringBuilder.append("insert all ");
        this.appendValues(csvContainer);
        stringBuilder.append("select * from dual");
        return stringBuilder.toString();
    }

    private void appendValues(CsvContainer csvContainer) {
        for(Record record : csvContainer.getRecords()){
            this.appendRecord(csvContainer.getTableName(), record.getAllAttributes());
        }
    }

    private void appendRecord(String tableName, String values) {
        stringBuilder.append("into ")
                .append(tableName)
                .append(" values ")
                .append(values)
                .append(' ');
    }

    private void logError(Exception e) {
        logger.log(Level.INFO, e.getMessage());
    }
}
