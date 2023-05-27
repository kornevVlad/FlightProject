package test;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightFilter;
import com.gridnine.testing.Segment;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class FlightTest {

    List<Flight> flightList = new ArrayList<>();
    FlightFilter flightFilter = new FlightFilter();


    @Test
    @DisplayName("Время вылета до текущего времени")
    public void test1() {
        List<Segment> segments = new ArrayList<>();
        //вылет до текущего времени
        segments.add(new Segment(LocalDateTime.now().minusHours(5), LocalDateTime.now().minusHours(1)));
        flightList.add(new Flight(segments));
        assertEquals(flightList.size(), 1);
        assertEquals(flightFilter.getDepartureBeforeCurrentTime(flightList).size(),0);

        //вылет после текущего времени
        List<Segment> segments1 = new ArrayList<>();
        segments1.add(new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3)));
        flightList.add(new Flight(segments1));
        assertEquals(flightList.size(), 2);
        assertEquals(flightFilter.getDepartureBeforeCurrentTime(flightList).size(),1);
    }

    @Test
    @DisplayName("Сегменты с датой прилёта раньше даты вылета")
    public void test2() {
        List<Segment> segments = new ArrayList<>();
        //прилет раньше вылета
        segments.add(new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().minusHours(2)));
        flightList.add(new Flight(segments));
        assertEquals(flightList.size(), 1);
        assertEquals(flightFilter.getArrivalDateBeforeDepartureDate(flightList).size(),0);
    }

    @Test
    @DisplayName("Общее время, проведённое на земле превышает два часа")
    public void test3() {
        List<Segment> segments = new ArrayList<>();
        //вылет следующего превышает 2 часа после посадки предыдущего
        segments.add(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
        segments.add(new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(5)));
        flightList.add(new Flight(segments));
        assertEquals(flightList.size(), 1);
        assertEquals(flightFilter.getTotalTimeEarthExceedsTwoHours(flightList).size(), 0);
    }
}