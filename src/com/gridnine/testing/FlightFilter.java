package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFilter {

    private final int TWO_HOURS_IN_SECOND = 3600 * 2; //один час = 3600 секунд

    public List<Flight> getDepartureBeforeCurrentTime(List<Flight> flights) {
        List<Flight> flightFilterList = new ArrayList<>();
        for (Flight flight : flights) {
           if (flight.getSegments().stream()
                   .filter(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()))
                   .collect(Collectors.toList()).isEmpty()) {
               flightFilterList.add(flight);
           }
        }
       return flightFilterList;
    }

    public List<Flight> getArrivalDateBeforeDepartureDate(List<Flight> flights) {
        List<Flight> flightFilterList = new ArrayList<>();
        for (Flight flight : flights) {
            if ((flight.getSegments().stream()
                    .filter(segment -> segment.getDepartureDate().isAfter(segment.getArrivalDate()))
                    .collect(Collectors.toList()).isEmpty())) {
                flightFilterList.add(flight);
            }
        }
        return flightFilterList;
    }

    public List<Flight> getTotalTimeEarthExceedsTwoHours(List<Flight> flights) {
        List<Flight> flightFilterList = new ArrayList<>();
        if (!flights.isEmpty()) {
            for (Flight flight : flights) {
                if (getSegmentTwoHours(flight.getSegments()) < TWO_HOURS_IN_SECOND) {
                    flightFilterList.add(flight);
                }
            }
        }
        return flightFilterList;
    }

    private int getSegmentTwoHours(List<Segment> segments) {
        int secondTwoHours = 0;
        if (segments.size() > 1) {
            for (int i = 0; i < segments.size() - 1; i++) {
                secondTwoHours = (int) Duration.between(segments.get(i).getArrivalDate(), segments.get(i+1)
                        .getDepartureDate()).getSeconds();
            }
        }
        return secondTwoHours;
    }
}