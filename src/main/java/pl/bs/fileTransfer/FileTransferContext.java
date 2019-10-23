package pl.bs.fileTransfer;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import pl.bs.configuration.ConfigurationModel;

import java.util.logging.Level;
import java.util.logging.Logger;

class FileTransferContext {

    private final Logger logger;
    private final CamelContext context;

    FileTransferContext() {
        this.context = new DefaultCamelContext();
        this.logger = Logger.getLogger(getClass().getName());
    }

    void startContext(ConfigurationModel configurationModel) {
        this.createContext(configurationModel);
        this.runContext();
    }

    private void createContext(ConfigurationModel configurationModel) {
        try {
            this.context.addRoutes(new FileTransferRouteBuilder(configurationModel));
        }
        catch(Exception e) {
            logError(e);
        }
    }

    private void runContext() {
        try {
            this.context.start();
            this.infiniteLoop();
        }
        catch(Exception e) {
            logError(e);
        }
    }

    private void infiniteLoop() {
        while (true)
            ;
    }

    private void logError(Exception e){
        logger.log(Level.INFO, e.getMessage());
    }

}
