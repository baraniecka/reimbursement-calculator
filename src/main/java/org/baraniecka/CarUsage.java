package org.baraniecka;

public class CarUsage {

    private static CarUsage INSTANCE;
    private double distance;

    private CarUsage() {
    }

    public static CarUsage getINSTANCE() {
        if (INSTANCE == null) {
            return new CarUsage();
        }
        return INSTANCE;
    }

    public double calculateCost() {
        ExpenseService service = ExpenseService.getInstance();
        if (distance < 0) {
            return 0;
        }
        return service.getDailyMileage() * distance;
    }

    public double setDistance(double distance) {
        this.distance = distance;
        return distance;
    }
}
