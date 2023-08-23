package org.baraniecka;

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
