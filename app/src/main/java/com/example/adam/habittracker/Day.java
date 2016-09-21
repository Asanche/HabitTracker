package com.example.adam.habittracker;

/**
 * Created by Adam on 9/21/2016.
 */
public class Day
{
    public Day(int dayNumber)
    {
        if(dayNumber > 0 && dayNumber < 8)
        {
            this.dayNumber = dayNumber;
            switch (dayNumber)
            {
                case 1:
                    fullName = "Sunday";
                    break;
                case 2:
                    fullName = "Monday";
                    break;
                case 3:
                    fullName = "Tuesday";
                    break;
                case 4:
                    fullName = "Wednesday";
                    break;
                case 5:
                    fullName = "Thursday";
                    break;
                case 6:
                    fullName = "Friday";
                    break;
                case 7:
                    fullName = "Saturday";
                    break;
            }
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    private int dayNumber;
    private String fullName;
}
