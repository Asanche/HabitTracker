package com.example.adam.habittracker;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Adam on 9/21/2016.
 */
public class Habit
{
    private Date creationDate;
    private String name;
    private ArrayList<Day> days;
    private ArrayList<Completion> history;

    public Habit(String name, ArrayList<Day> days)
    {
        this.days = days;
        this.name = name;
        this.creationDate = new Date();
        history = new ArrayList<Completion>();
    }

    public ArrayList<Completion> getHistory()
    {
        if(history == null)
        {
            history = new ArrayList<Completion>();
        }
        return history;
    }

    public ArrayList<Day> getDays()
    {
        return days;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public String getName()
    {
        return name;
    }

    private Integer getTodaysCompletionCount()
    {
        int count = 0;
        for(Completion e : history)
        {
            Calendar todayCal = Calendar.getInstance();
            todayCal.setTime(new Date());

            Calendar historyCal = Calendar.getInstance();
            historyCal.setTime(e.getDate());

            if(todayCal.get(Calendar.DAY_OF_YEAR) == historyCal.get(Calendar.DAY_OF_YEAR)
                    && todayCal.get(Calendar.YEAR) == historyCal.get(Calendar.YEAR))
            {
                count += 1;
            }
        }

        return count;
    }

    public Integer getCompletionCount()
    {
        return this.history.size();
    }

    public Integer getIncompleteCount()
    {
        Calendar createdCal = Calendar.getInstance();
        createdCal.setTime(this.creationDate);

        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(new Date());

        //Build List of Days from creation to today
        ArrayList<Calendar> daysSinceCreation = daysFromTo(todayCal, createdCal);

        int incompletes = 0;
        for(Calendar day : daysSinceCreation)
        {
            for(Completion e : history)
            {
                Calendar eCal = Calendar.getInstance();
                eCal.setTime(e.getDate());
                if(eCal.get(Calendar.DAY_OF_WEEK) == day.get(Calendar.DAY_OF_WEEK))
                {
                    incompletes += 1;
                }
            }
        }
        return incompletes;
    }

    public void complete()
    {
        Log.i("info", "completing habit " + this.name);
        addToHistory();
    }

    private void addToHistory()
    {
        history.add(new Completion(this.name));
    }

    public void removeFromHistory(Completion completion)
    {
        history.remove(completion);
    }

    public Boolean isComplete()
    {
        return (getTodaysCompletionCount() > 0);
    }

    private ArrayList<Calendar> daysFromTo(Calendar to, Calendar from)
    {
        ArrayList<Calendar> calsList = new ArrayList<Calendar>();
        while(from.getTimeInMillis()< to.getTimeInMillis())
        {
            for(Day day : days)
            {
                if(day.getDayNumber() == from.get(Calendar.DAY_OF_WEEK))
                {
                    calsList.add(from);
                }
            }
            from.set(from.DAY_OF_YEAR, from.get(Calendar.DAY_OF_YEAR) + 1);
        }
        return calsList;
    }

    public String daysToString()
    {
        String daysString = new String();

        for(Day day : days)
        {
            daysString +=(day.getFullName() + ", ");
        }

        return daysString.substring(0, daysString.length() - 2);
    }

    @Override
    public String toString()
    {

        if(isComplete())
        {
            return this.name + " \n Completed " + getTodaysCompletionCount().toString() +  " times today!";
        }
        else
        {
            return this.name + " \n Have not completed today...";
        }
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

        Habit habit = (Habit) o;

        if (creationDate != null ? !creationDate.equals(habit.creationDate) : habit.creationDate != null)
        {
            return false;
        }
        if (name != null ? !name.equals(habit.name) : habit.name != null)
        {
            return false;
        }
        if (days != null ? !days.equals(habit.days) : habit.days != null)
        {
            return false;
        }

        return history != null ? history.equals(habit.history) : habit.history == null;

    }

    @Override
    public int hashCode()
    {
        int result = creationDate != null ? creationDate.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (days != null ? days.hashCode() : 0);
        result = 31 * result + (history != null ? history.hashCode() : 0);
        return result;
    }
}
