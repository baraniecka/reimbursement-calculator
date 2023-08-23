package org.baraniecka;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExpenseService {
    private static ExpenseService INSTANCE;
    private List<ReceiptType> expenseTypes;
    private double dailyAllowance;
    private double dailyMileage;
    private double totalLimit;
    private double distanceLimit;

    private ExpenseService() {
        expenseTypes = new ArrayList<>();
        expenseTypes.add(new ReceiptType("taxi"));
        expenseTypes.add(new ReceiptType("plane ticket"));
        expenseTypes.add(new ReceiptType("train ticket"));
        expenseTypes.add(new ReceiptType("hotel"));
        expenseTypes.add(new ReceiptType("laundry"));
        dailyAllowance = 15.0;
        dailyMileage = 0.3;
    }

    public static ExpenseService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ExpenseService();
        }
        return INSTANCE;
    }

    public double getDailyAllowance() {
        return dailyAllowance;
    }

    public double getDailyMileage() {
        return dailyMileage;
    }


    public List<ReceiptType> getExpenseTypes() {
        return expenseTypes;
    }

    public String addExpenseType(String type) {
        Optional<String> isTypeExists = expenseTypes
                .stream()
                .map(t -> t.getReceiptType())
                .filter(t -> t.equalsIgnoreCase(type))
                .findFirst();
        if (isTypeExists.isEmpty()) {
            this.expenseTypes.add(new ReceiptType(type.toLowerCase()));
            return type;
        } else return "already exists";
    }

    public boolean addLimitForReceiptType(ReceiptType newLimit) {
        Optional<ReceiptType> optionalReceipt = expenseTypes
                .stream()
                .filter(t -> t.getReceiptType().equals(newLimit.getReceiptType()))
                .findFirst();

        if (optionalReceipt.isPresent() && newLimit.getLimit() > 0) {
            expenseTypes
                    .stream()
                    .filter(t -> t.getReceiptType().equals(newLimit.getReceiptType()))
                    .findFirst()
                    .get()
                    .setLimit(newLimit.getLimit());
            return true;
        }
        return false;
    }

    public double setDailyAllowance(double limit) {

        if (limit < 0) {
            this.dailyAllowance = 0.0;
        } else this.dailyAllowance = limit;
        return dailyAllowance;
    }

    public double setDailyMileage(double limit) {
        if (limit < 0) {
            this.dailyMileage = 0.0;
        } else this.dailyMileage = limit;
        return dailyMileage;
    }

    public double setDistanceLimit(double limit) {
        if (limit < 0) {
            this.distanceLimit = 0;
        } else this.distanceLimit = limit;
        return distanceLimit;
    }

    public double getDistanceLimit() {
        return distanceLimit;
    }

    public double setTotalLimit(double limit) {
        if (limit < 0) {
            this.totalLimit = 0;
        } else this.totalLimit = limit;
        return totalLimit;
    }

    public double getTotalLimit() {
        return totalLimit;
    }

    private boolean isOverDailyAllowance(double amount) {
        return amount > dailyAllowance;
    }

    private boolean isOverMileage(double amount) {
        return amount > dailyMileage;
    }

}
