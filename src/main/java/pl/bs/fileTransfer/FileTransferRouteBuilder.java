package pl.bs.fileTransfer;

import pl.bs.configuration.ConfigurationModel;
import org.apache.camel.builder.RouteBuilder;

class FileTransferRouteBuilder extends RouteBuilder {

    private final ConfigurationModel configurationModel;

    FileTransferRouteBuilder(ConfigurationModel configurationModel) {
        this.configurationModel = configurationModel;
    }

    @Override
    public void configure() {
        from(this.createSourceUrl())
                .process(new FileTransferProcessor())
                .to(this.createDestinationUrl());
    }

    private String createSourceUrl() { //"sftp://paul@localhost:22/www/?password=password123&delay=5s&delete=true"
        return "sftp://" + this.configurationModel.getSourceUsername()
                + "@" + this.configurationModel.getSourceIp()
                + ":22" + this.configurationModel.getSourcePath()
                + "?password=" + this.configurationModel.getSourcePassword()
                + "&delay=" + this.configurationModel.getTransferFrequencyInSeconds()
                + "s&delete=true";
    }

    private String createDestinationUrl() { //"sftp://paul@localhost:22/www2/?password=password123"
        return "sftp://" + this.configurationModel.getDestinationUsername()
                + "@" + this.configurationModel.getDestinationIp()
                + ":22" + this.configurationModel.getDestinationPath()
                + "?password=" + this.configurationModel.getDestinationPassword();
    }

}


//zipping files:

//  .marshal().zipFile()
//  .to("sftp://mark@10.0.2.15:22/www3/?password=mark123&fileName=${file:name.noext}.z");
