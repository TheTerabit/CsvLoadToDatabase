package pl.bs.models;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account extends Record {

    @CsvBindByName(column = "ID")
    private String id;

    @CsvBindByName(column = "NAME")
    private String name;

    @CsvBindByName(column = "ACCOUNT_STATUS")
    private String accountStatus;

    @Override
    public String getAllAttributes() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("('").append(this.id).append("',")
                .append("'").append(this.name).append("',")
                .append("'").append(this.accountStatus).append("',");

        return stringBuilder.toString();
    }

}
