package org.baraniecka;

public class CarUsage implements Expense{

    private double distance;

    @Override
    public double sum() {
        ExpenseService service = ExpenseService.getInstance();
        if(distance < 0){
            return 0;
        }
        return service.getDailyMileage() * distance;
    }

    @Override
    public double dailyExpense() {
        BusinessTrip trip = BusinessTrip.getInstance();
        return sum()/trip.getActualDays();
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
