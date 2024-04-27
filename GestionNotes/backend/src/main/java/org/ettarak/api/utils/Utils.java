package org.ettarak.api.utils;

import java.time.format.DateTimeFormatter;

public class Utils {
    public  static DateTimeFormatter dateTimeFormatter(){
        return  DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss");
    }
}
