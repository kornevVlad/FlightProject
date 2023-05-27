package com.gridnine.testing;

public class Main {
    public static void main(String[] args) {

        FlightFilter flightFilter = new FlightFilter();

        System.out.println("Вылет до текущего момента времени");
        flightFilter.getDepartureBeforeCurrentTime(FlightBuilder.createFlights()).forEach(System.out::println);
        System.out.println("----------");

        System.out.println("Сегменты с датой прилёта раньше даты вылета");
        flightFilter.getArrivalDateBeforeDepartureDate(FlightBuilder.createFlights()).forEach(System.out::println);
        System.out.println("----------");

        System.out.println("Общее время, проведённое на земле превышает два часа");
        flightFilter.getTotalTimeEarthExceedsTwoHours(FlightBuilder.createFlights()).forEach(System.out::println);
        System.out.println("----------");
    }
}