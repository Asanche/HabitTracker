package com.example.adam.habittracker;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Adam on 9/28/2016.
 */
public class HabitListControllerTest extends ActivityInstrumentationTestCase2<MainActivity>
{
    public HabitListControllerTest()
    {
        super(MainActivity.class);
    }

    public void testAddHabits()
    {
        HabitListController hlc = HabitListController.getInstance();

        ArrayList<Integer> d = new ArrayList<Integer>();

        d.add(Calendar.SUNDAY);
        d.add(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
        Habit h = new Habit("AHHHHHHH!", Day.getMultipleDays(d));

        hlc.addHabit(h);

        hlc.updateHabits();

        assertTrue(hlc.getCurrentHabits().size() == 1);
        assertTrue(hlc.getCurrentHabits().contains(h));

        h.complete();
        hlc.updateHabits();

        assertTrue(hlc.getCurrentHabits().size() == 1);
    }

    public void testAddSecondHabit()
    {
        HabitListController hlc = HabitListController.getInstance();

        ArrayList<Integer> d = new ArrayList<Integer>();
        d.add(Calendar.SUNDAY);
        d.add(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));

        Habit h = new Habit("Woah!", Day.getMultipleDays(d));

        hlc.addHabit(h);
        hlc.updateHabits();


        assertTrue(hlc.getCurrentHabits().size() == 2);
        assertTrue(hlc.getCurrentHabits().contains(h));

        h.complete();
        hlc.updateHabits();

        assertTrue(hlc.getCurrentHabits().size() == 2);
    }

    public void testUncompleteUpdate()
    {
        HabitListController hlc = HabitListController.getInstance();

        ArrayList<Integer> d = new ArrayList<Integer>();

        d.add(Calendar.SUNDAY);
        d.add(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
        Habit h = new Habit("EEEEE!!", Day.getMultipleDays(d));

        hlc.addHabit(h);

        hlc.updateHabits();

        assertTrue(hlc.getCurrentHabits().size() == 3);
        assertTrue(hlc.getCurrentHabits().contains(h));

        h.complete();
        hlc.updateHabits();

        assertTrue(hlc.getCurrentHabits().size() == 3);

        Habit h0 = new Habit("OHHHH OHHHH!", Day.getMultipleDays(d));
        hlc.addHabit(h0);

        h0.complete();
        hlc.updateHabits();

        assertTrue(hlc.getCurrentHabits().size() == 4);

        hlc.updateHabits();

        assertTrue(hlc.getCurrentHabits().size() == 4);
    }

    public void testWrongDay()
    {
        HabitListController hlc = HabitListController.getInstance();
        assertTrue(hlc.getCurrentHabits().size() == 4);
        ArrayList<Integer> d = new ArrayList<Integer>();
        if(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)
        {
            d.add(Calendar.THURSDAY);
        }
        else
        {
            d.add(Calendar.MONDAY);
        }

        Habit h = new Habit("Testing The Test Test Test Test!", Day.getMultipleDays(d));

        hlc.addHabit(h);
        hlc.updateHabits();

        assertTrue(hlc.getCurrentHabits().size() == 4);
        assertFalse(hlc.getCurrentHabits().contains(h));
    }

}