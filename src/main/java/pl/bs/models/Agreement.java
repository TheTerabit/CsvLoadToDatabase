package pl.bs.models;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Agreement extends Record {

    @CsvBindByPosition(position=0)
    private String externalId;

    @CsvBindByPosition(position=1)
    private String description;

    @CsvBindByPosition(position=2)
    private String state;

    @CsvBindByPosition(position=3)
    private String agreementNumber;


    @Override
    public String getAllAttributes() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("('").append(this.externalId).append("',")
                .append("'").append(this.description).append("',")
                .append("'").append(this.state).append("',")
                .append("'").append(this.agreementNumber).append("')");

        return stringBuilder.toString();
    }

}
