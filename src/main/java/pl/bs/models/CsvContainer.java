package pl.bs.models;

import lombok.Data;

import java.util.List;

@Data
public class CsvContainer {

    private String tableName;
    private List<Record> records;

}
