package org.baraniecka;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BusinessTrip {

    private static BusinessTrip INSTANCE;
    private LocalDate startDate;
    private int duration;
    private int excluded;

    private BusinessTrip() {
    }

    public static BusinessTrip getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BusinessTrip();
        }
        return INSTANCE;
    }

    public int getActualDays() {
        return duration - excluded;
    }

    public void exclude() {
        excluded++;
    }

    public void include() {
        excluded--;
    }

    public List<LocalDate> createTripDates() {
        List<LocalDate> tripDates = new ArrayList<>();

        for (int i = 0; i < duration; i++) {
            tripDates.add(startDate.plusDays(i));
        }
        return tripDates;
    }

    public List<LocalDate> getTripDates() {

        return createTripDates();
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public void setDuration(int duration) {
        this.duration = duration;
    }

}
