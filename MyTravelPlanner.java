import java.time.LocalDate;
import java.time.LocalTime;

public class MyTravelPlanner {

    public String getPlan(LocalDate dateOfPlan, String weatherPrediction,
                          LocalTime firstAppointment, LocalTime lastAppointment) {
        LocalTime latestTrainTime = findLatestTrainTime(dateOfPlan, firstAppointment);
        LocalTime earliestReturnTrainTime = lastAppointment.plusHours(1);

        if (earliestReturnTrainTime.isAfter(LocalTime.of(22, 0)) ||
                latestTrainTime.isBefore(LocalTime.of(6, 0))) {
            return "Please cancel or reschedule your appointments on " + dateOfPlan + ".";
        }

        if (weatherPrediction.equals("Rainy") || weatherPrediction.equals("Snowy")) {
            return "Please drive on " + dateOfPlan + ", and leave the house at " + latestTrainTime.minusHours(1) + ".";
        }

        return "Please take the " + latestTrainTime + " train to go to the city, and " +
                earliestReturnTrainTime + " train to get back home on " + dateOfPlan + ".";
    }

    private LocalTime findLatestTrainTime(LocalDate dateOfPlan, LocalTime firstAppointment) {
        LocalTime lastTrainTime;
        if (dateOfPlan.getDayOfWeek().getValue() >= 6) {
            lastTrainTime = LocalTime.of(22, 0);
        } else {
            lastTrainTime = LocalTime.of(23, 0);
        }

        LocalTime latestTrainTime = firstAppointment.minusHours(1);
        return latestTrainTime.isAfter(lastTrainTime) ? lastTrainTime : latestTrainTime;
    }

 
    public static void main(String[] args) {
        MyTravelPlanner planner = new MyTravelPlanner();

        // Sunny 
        String plan1 = planner.getPlan(LocalDate.of(2024, 3, 17), "Sunny",
                LocalTime.of(8, 0), LocalTime.of(17, 0));
        System.out.println(plan1); // Expected output: Please take the 07:00 train to go to the city, and 18:00 train to get back home on 2024-03-17.

        // Rainy
        String plan2 = planner.getPlan(LocalDate.of(2024, 3, 18), "Rainy",
                LocalTime.of(9, 0), LocalTime.of(16, 0));
        System.out.println(plan2); // Expected output: Please drive on 2024-03-18, and leave the house at 08:00.

        // Train N/A
        String plan3 = planner.getPlan(LocalDate.of(2024, 3, 19), "Sunny",
                LocalTime.of(23, 0), LocalTime.of(23, 30));
        System.out.println(plan3); // Expected output: Please cancel or reschedule your appointments on 2024-03-19.
    }
}
