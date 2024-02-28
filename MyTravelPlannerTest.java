import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;

public class MyTravelPlannerTest {

    @Test
    public void testGetPlan_RainyWeekday() {
        MyTravelPlanner planner = new MyTravelPlanner();
        LocalDate date = LocalDate.of(2023, 9, 25);
        String weather = "Rainy";
        LocalTime firstAppointment = LocalTime.of(9, 30);
        LocalTime lastAppointment = LocalTime.of(16, 0);
        String expected = "Please cancel or reschedule your appointments on 2023-09-25.";
        assertEquals(expected, planner.getPlan(date, weather, firstAppointment, lastAppointment));
    }

    @Test
    public void testGetPlan_RainyWeekend() {
        MyTravelPlanner planner = new MyTravelPlanner();
        LocalDate date = LocalDate.of(2023, 9, 29);
        String weather = "Rainy";
        LocalTime firstAppointment = LocalTime.of(13, 0);
        LocalTime lastAppointment = LocalTime.of(23, 59);
        String expected = "Please drive on 2023-09-29, and leave the house at 12:00.";
        assertEquals(expected, planner.getPlan(date, weather, firstAppointment, lastAppointment));
    }
}
