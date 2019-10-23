package pl.bs.fileTransfer;

import pl.bs.database.DatabaseLoader;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import pl.bs.models.CsvContainer;


class FileTransferProcessor implements Processor {

    private final ModelCreator modelCreator = new ModelCreator();
    private final DatabaseLoader databaseLoader = new DatabaseLoader();

    @Override
    public void process(Exchange exchange) {
        CsvContainer csvContainer = this.modelCreator.parseExchange(exchange);
        this.databaseLoader.load(csvContainer);
    }

}
