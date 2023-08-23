package org.baraniecka;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeExpense {

    private ReceiptType type;
    private double value;

    public String getType() {
        return type.getReceiptType();
    }

    public double getValue() {
        return value;
    }

}
