package pl.bs.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;


public class ConfigurationReader {

    private final Logger logger;
    private final ObjectMapper mapper;

    public ConfigurationReader() {
        this.logger = Logger.getLogger(getClass().getName());
        this.mapper = new ObjectMapper();
    }

    public ConfigurationModel getConfiguration(String path) {
        ConfigurationModel configurationModel = new ConfigurationModel();
        try {
            configurationModel = this.mapper.readValue(this.readFile(path), ConfigurationModel.class);
        }
        catch(IOException e) {
            this.logError(e);
        }
        return configurationModel;
    }

   private String readFile(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e) {
            this.logError(e);
        }
        return contentBuilder.toString();
    }

    private void logError(Exception e){
        logger.log(Level.INFO, e.getMessage());
    }

}
