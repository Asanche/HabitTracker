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
    private ArrayList<Habit> habits;

    public Day(int dayOfWeek)
    {
        this.habits = new ArrayList<Habit>();
        if(dayOfWeek > 0 && dayOfWeek < 8)
        {
            this.dayNumber = dayNumber;
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

            this.populateHabits(dayOfWeek);
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    private void populateHabits(int dayofWeek)
    {
        //deserialize habits from GSON to habits ArrayList...
    }

    public void addHabit(Habit habit)
    {
        this.habits.add(habit);
    }

    public ArrayList<Habit> getHabits()
    {
        return habits;
    }
}
