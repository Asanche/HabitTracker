package com.example.adam.habittracker;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Adam on 9/21/2016.
 */
public class Day
{
    private int dayNumber;
    private String fullName;

    public Day(int dayOfWeek)
    {
        if(dayOfWeek > 0 && dayOfWeek < 8)
        {
            this.dayNumber = dayOfWeek;
            switch (dayOfWeek)
            {
                case Calendar.SUNDAY:
                    fullName = "Sunday";
                    break;
                case Calendar.MONDAY:
                    fullName = "Monday";
                    break;
                case Calendar.TUESDAY:
                    fullName = "Tuesday";
                    break;
                case Calendar.WEDNESDAY:
                    fullName = "Wednesday";
                    break;
                case Calendar.THURSDAY:
                    fullName = "Thursday";
                    break;
                case Calendar.FRIDAY:
                    fullName = "Friday";
                    break;
                case Calendar.SATURDAY:
                    fullName = "Saturday";
                    break;
            }
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    public static ArrayList<Day> getMultipleDays(ArrayList<Integer> daysOfWeek)
    {
        ArrayList<Day> days = new ArrayList<Day>();
        for(int day : daysOfWeek)
        {
            days.add(new Day(day));
        }

        return days;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Day day = (Day) o;

        if (dayNumber != day.dayNumber)
        {
            return false;
        }
        return fullName != null ? fullName.equals(day.fullName) : day.fullName == null;

    }

    @Override
    public int hashCode()
    {
        int result = dayNumber;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        return result;
    }
}
