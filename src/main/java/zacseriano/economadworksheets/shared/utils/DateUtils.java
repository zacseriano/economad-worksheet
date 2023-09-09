package zacseriano.economadworksheets.shared.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static final String DEFAULT_FORMAT = "dd/MM/yyyy";

    public static LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DateUtils.DEFAULT_FORMAT));
    }       
    
}
