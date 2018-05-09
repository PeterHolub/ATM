package atm.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static jdk.nashorn.internal.objects.NativeString.concat;

public class Date {

    public static String getDate(){
        String timeFormat = "HH:mm";
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern(timeFormat)));

        return String.valueOf(localDate) + concat(" ") + (localTime);
    }
}
