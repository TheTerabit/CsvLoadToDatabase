package pl.bs.fileTransfer;

import pl.bs.configuration.ConfigurationReader;
import pl.bs.configuration.ConfigurationModel;

public class FileTransferFacade {

    private final FileTransferContext fileTransferContext;
    private final ConfigurationReader configurationReader;

    public FileTransferFacade() {
        this.configurationReader = new ConfigurationReader();
        this.fileTransferContext = new FileTransferContext();
    }

    public void start(String path) {
        ConfigurationModel configurationModel = this.configurationReader.getConfiguration(path);
        this.fileTransferContext.startContext(configurationModel);
    }



}
