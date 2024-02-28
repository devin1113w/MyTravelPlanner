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

        return "Please take the " + latestTrainTime + " train to go to the city (one hour before the first appointment), and " +
                earliestReturnTrainTime + " train (one hour after the last appointment time) to get back home on " + dateOfPlan + ".";
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
}

