package org.baraniecka;

import java.util.List;
import java.util.stream.Collectors;

public class Reimbursement implements Expense{
    private List<EmployeeExpense> expenses;


    public List<EmployeeExpense> filterByType(String type){
        return expenses.stream()
                .filter(e -> e.getType().equals(type))
                .collect(Collectors.toList());
    }

    public double sumByType(String type){
        return filterByType(type)
                .stream()
                .map(e -> e.getValue())
                .reduce(Double::sum).orElse(0.0);
    }


    @Override
    public double sum() {
        return 0;
    }

    @Override
    public double dailyExpense() {
        BusinessTrip trip = BusinessTrip.getInstance();
        return sum()/trip.getActualDays();
    }
}
