package pl.bs.fileTransfer;

import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.camel.Exchange;
import pl.bs.models.Account;
import pl.bs.models.Agreement;
import pl.bs.models.CsvContainer;
import pl.bs.models.Record;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class ModelCreator {

    private Class modelClass;
    private String tableName;
    private final Logger logger;

    ModelCreator() {
        this.logger = Logger.getLogger(getClass().getName());
    }

    CsvContainer parseExchange(Exchange exchange) {
        String csv = exchange.getIn().getBody(String.class);
        String fileName = exchange.getIn().toString();
        return this.getModelFromCsv(csv, fileName);
    }

    private CsvContainer getModelFromCsv(String csv, String fileName) {
        this.setAccurateModel(fileName);
        return this.createModel(csv);
    }

    private void setAccurateModel(String fileName) {
        try {
            pickAccurateModel(fileName);
        }
        catch(NoMatchingModelException e) {
            this.logger.log(Level.INFO, e.getMessage());
        }
    }

    private void pickAccurateModel(String fileName) throws NoMatchingModelException {
        if(fileName.contains("account")) {
            modelClass = Account.class;
            tableName = "accounts";
        }
        else if (fileName.contains("agreement")) {
            modelClass = Agreement.class;
            tableName = "agreements";
        }
        else {
            tableName = "";
            throw new NoMatchingModelException();
        }
    }



    private CsvContainer createModel(String csv) {
        CsvContainer csvContainer = new CsvContainer();
        csvContainer.setRecords(this.parseCsvToList(csv));
        csvContainer.setTableName(this.tableName);
        return csvContainer;
    }

    private List<Record> parseCsvToList(String csv){
        Reader csvReader = new StringReader(csv);
        List<Record> records = new CsvToBeanBuilder(csvReader)
                .withSeparator('|')
                .withType(this.modelClass).build().parse();
        return records;
    }

}
