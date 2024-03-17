package main.datastructures;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EpochToDateTime {

    public static String convert(long epochTime) {
        // Convert milliseconds to seconds (assuming epochTime is in milliseconds)
        long epochTimeSeconds = epochTime / 1000;

        // Define the date format pattern
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        // Create a Date object from the epoch time
        Date date = new Date(epochTimeSeconds * 1000); // Convert back to milliseconds for Date constructor

        // Format the date in the desired format
        return dateFormat.format(date);
    }

    public static void main(String[] args) {
        long epochTime = 1709893366904L;
        long publishDateTime = 20240308042226L;
        String formattedDateTime = convert(epochTime);
        System.out.println(formattedDateTime);
        System.out.println(Long.parseLong(formattedDateTime) - publishDateTime);
    }
}