package org.baraniecka;

import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Setter
public class Reimbursement {
    private List<EmployeeExpense> expenses;

    public List<EmployeeExpense> filterByType(String type) {
        if (expenses.isEmpty()) {
            return Collections.emptyList();
        }
        return expenses.stream()
                .filter(e -> e.getType().equals(type))
                .collect(Collectors.toList());
    }

    public double sumByType(String type) {
        return filterByType(type)
                .stream()
                .map(e -> e.getValue())
                .reduce(Double::sum).orElse(0.0);
    }


    public double getTotalReimbursement(List<EmployeeExpense> expenses) {
        double mileageCost = CarUsage.getINSTANCE().calculateCost();
        return expenses
                .stream().map(e -> e.getValue()).reduce(Double::sum).orElse(0.0);
    }
}
