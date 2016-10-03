package com.example.adam.habittracker;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * This is a giant singleton that is in charge of controlling access to the main Habit Lists present
 * in the app, being the currently active habits for that day in the Main Activity, and all of the
 * habits, active or not, that is present in the AllHabits Activity.
 * The class is only instantiable as an instance that it holds as a private variable. Follows the
 * singleton design pattern.
 * Created by Adam on 9/27/2016.
 */
public class HabitListController
{
    private static final String HABITFILE = "habits.sav";
    private static HabitListController instance = new HabitListController();

    private Activity mainActivity;

    private ArrayList<Habit> allHabits;
    private ArrayList<Habit> currentHabits;

    private Habit historyHabit;

    private HabitListController()
    {
        allHabits = new ArrayList<Habit>();
        currentHabits = new ArrayList<Habit>();
    }

    public static HabitListController getInstance()
    {
        return instance;
    }

    public Habit getHistoryHabit()
    {
        return historyHabit;
    }

    public void setHistoryHabit(Habit historyHabit)
    {
        this.historyHabit = historyHabit;
    }

    public ArrayList<Habit> getAllHabits()
    {
        return allHabits;
    }

    public ArrayList<Habit> getCurrentHabits()
    {
        return currentHabits;
    }

    public void setMainActivity(Activity activity)
    {
        this.mainActivity = activity;
    }

    //Called after changing the habit list.
    public  void updateHabits()
    {
        Log.i("trace", "HabitListController updateHabits");
        if(allHabits.size() == 0)
        {
            deserializeHabits();
        }

        serializeHabits();
    }

    public void addHabit(Habit habit)
    {
        Log.i("trace", "HabitListController addHabit");
        Day day = new Day(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));

        Log.i("info", "Check if new habit is valid for day " + day.getFullName());

        if(!allHabits.contains(habit))
        {
            Log.i("info", "Adding habit " + habit.toString() + " to allHabits");
            allHabits.add(habit);
        }

        if(!currentHabits.contains(habit) && habit.getDays().contains(day))
        {
            currentHabits.add(habit);
            updateHabits();
        }
    }

    public void removeHabit(Habit habit)
    {
        if(allHabits.contains(habit))
        {
            allHabits.remove(habit);
        }
        if(currentHabits.contains(habit))
        {
            currentHabits.remove(habit);
        }
        //need to serialize, as if we delete the last habit, we will redeserialize the list due
        //to allhabits being empty, as per our logic check
        //TODO: Check whether to reserialize a different way
        serializeHabits();
        updateHabits();
    }

    private  void deserializeHabits()
    {
        Log.i("trace", "HabitListController deserializeHabits");
        try
        {
            FileInputStream fis = mainActivity.openFileInput(HABITFILE);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();

            allHabits = gson.fromJson(in,listType);

            for(Habit habit : allHabits)
            {
                if(habit.getDays().contains(new Day(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))))
                {
                    currentHabits.add(habit);
                }
            }

            fis.close();
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            allHabits = new ArrayList<Habit>();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    private void serializeHabits()
    {
        Log.i("trace", "HabitListController serializeHabits");
        if(mainActivity == null)
        {
            return;
        }

        try
        {
            FileOutputStream fos = mainActivity.openFileOutput(HABITFILE, 0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(allHabits, out);
            out.flush();

            fos.close();
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
