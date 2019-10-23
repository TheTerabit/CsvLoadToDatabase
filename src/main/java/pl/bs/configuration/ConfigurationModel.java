package pl.bs.configuration;

import lombok.Data;

@Data
public class ConfigurationModel {

    private String sourceIp;
    private String sourcePath;
    private String sourceUsername;
    private String sourcePassword;

    private String destinationIp;
    private String destinationPath;
    private String destinationUsername;
    private String destinationPassword;

    public Long transferFrequencyInSeconds;

}
