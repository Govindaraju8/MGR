
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateInformationExample {
    public static void main(String[] args) {
        // Example date in string format
        String dateString = "2023-07-11";

        // Parse the string into a LocalDate object
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);

        // Get the day of the month
        int dayOfMonth = date.getDayOfMonth();
        System.out.println("Day of the month: " + dayOfMonth);

        // Get the day of the week
        String dayOfWeek = date.getDayOfWeek().toString();
        System.out.println("Day of the week: " + dayOfWeek);

        // Get the month
        String month = date.getMonth().toString();
        System.out.println("Month: " + month);
    }
}
