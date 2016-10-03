package com.example.adam.habittracker;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Adam on 9/27/2016.
 */
public class HabitTest extends ActivityInstrumentationTestCase2<MainActivity>
{
    public HabitTest()
    {
        super(MainActivity.class);
    }

    public void testCreateHabit()
    {
        ArrayList<Integer> d = new ArrayList<Integer>();
        d.add(Calendar.MONDAY);
        d.add(Calendar.TUESDAY);
        Habit h = new Habit("Test", Day.getMultipleDays(d));
    }

    public void testCompleteHabit()
    {
        ArrayList<Integer> d = new ArrayList<Integer>();
        d.add(Calendar.MONDAY);
        d.add(Calendar.TUESDAY);
        Habit h = new Habit("WEEEEEE!!", Day.getMultipleDays(d));

        h.complete();

        assertTrue(h.isComplete());
    }

    public void testUncomplete()
    {
        ArrayList<Integer> d = new ArrayList<Integer>();
        d.add(Calendar.MONDAY);
        d.add(Calendar.TUESDAY);
        Habit h = new Habit("WEEEEEE!!", Day.getMultipleDays(d));

        h.complete();
        assertTrue(h.isComplete());

        h.unComplete();
        assertFalse(h.isComplete());
    }
}
