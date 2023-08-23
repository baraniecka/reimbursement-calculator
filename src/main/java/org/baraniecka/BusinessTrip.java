package org.baraniecka;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BusinessTrip {

    private static BusinessTrip INSTANCE;
    private LocalDate startDate;
    private int duration;
    private List<LocalDate> excluded;

    private BusinessTrip() {}

    public static BusinessTrip getInstance(){
        if(INSTANCE == null){
            INSTANCE = new BusinessTrip();
        }
        return INSTANCE;
    }

    public int getActualDays(){
        if (excluded.isEmpty()){
            return duration;
        }
        return duration - excluded.size();
    }

    public void exclude(LocalDate date){
        excluded.add(date);
    }

    public void include(LocalDate date){
        excluded.remove(date);
    }

    public List<LocalDate> createTripDates(){
        List<LocalDate> tripDates = new ArrayList<>();

        for(int i = 0; i < duration; i++){
            tripDates.add(startDate.plusDays(i));
        }
        return tripDates;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


}
