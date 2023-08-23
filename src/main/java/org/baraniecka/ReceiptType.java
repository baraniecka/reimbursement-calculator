package org.baraniecka;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@JsonDeserialize
public class ReceiptType {

    private String receiptType;
    private double limit;

    public ReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

}
