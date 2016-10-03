package com.example.adam.habittracker;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Adam on 9/27/2016.
 */
public class DayTest extends ActivityInstrumentationTestCase2<MainActivity>
{
    public DayTest()
    {
        super(MainActivity.class);
    }

    public void testGetMultipleDays()
    {
        ArrayList<Integer> d = new ArrayList<Integer>();
        d.add(Calendar.MONDAY);
        d.add(Calendar.TUESDAY);

        Day m = new Day(Calendar.MONDAY);
        Day t = new Day(Calendar.TUESDAY);

        ArrayList<Day> l = Day.getMultipleDays(d);

        assertTrue(l.size() == 2);
        assertTrue(l.contains(m));
        assertTrue(l.contains(t));
    }
}
