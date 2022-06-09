package org.learn_school.test;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Calculation {
    public static String[] getPeriods(LocalTime[] startTimes, int[] durations, int consultationTime, LocalTime beginWorkingTime, LocalTime endWorkingTime) {
        List<LocalTime> periods = new ArrayList<>();
//        LocalTime availableTime = LocalTime.of(endWorkingTime.getHour() - beginWorkingTime.getHour(), endWorkingTime.getMinute() - beginWorkingTime.getMinute());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < startTimes.length; i++) {
            LocalTime endTime = LocalTime.of(startTimes[i].getHour() + durations[i], startTimes[i].getMinute());
            periods.add(startTimes[i]);
            periods.add(endTime);
        }
        for(int i = 0; i < periods.size() - 2; i++) {
            int availablePeriod = periods.get(i + 2).getHour() - periods.get(i+1).getHour();
            if(availablePeriod > consultationTime) {
                list.add(periods.get(i + 1).getHour() + ":" + periods.get(i + 1).getMinute() + "-" + periods.get(i + 2).getHour() + ":" + periods.get(i + 2).getMinute());
            }
        }
        int availablePeriod = endWorkingTime.getHour() - periods.get(periods.size() - 1).getHour();
        if (availablePeriod > consultationTime) {
            list.add(periods.get(periods.size() - 1).getHour() + ":" + periods.get(periods.size() - 1).getMinute() + "-" + endWorkingTime.getHour() + ":" + endWorkingTime.getMinute());
        }
        String[] availablePeriods = new String[list.size()];
        for(int i = 0; i < list.size(); i++) {
            availablePeriods[i] = list.get(i);
        }
        return availablePeriods;
    }

//    public static void main(String[] args) {
//        LocalTime[] startTimes = new LocalTime[2];
//        LocalTime localTime1 = LocalTime.of(9, 0);
//        startTimes[0] = localTime1;
//        localTime1 = LocalTime.of(10, 0);
//        startTimes[1] = localTime1;
//
//    }
}
