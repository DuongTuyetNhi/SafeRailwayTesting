package base;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String getDateAdd(int number){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        return dateTimeFormatter.format((LocalDate.now()).plusDays(number));
    }
}
