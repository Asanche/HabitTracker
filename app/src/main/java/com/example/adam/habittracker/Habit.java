package com.example.adam.habittracker;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Adam on 9/21/2016.
 */
public class Habit implements Serializable
{
    private Date creationDate;
    private String name;
    private ArrayList<Day> days;
    private ArrayList<HabitHistoryElement> history;
    private Boolean complete = false;

    public Habit(String name, ArrayList<Day> days)
    {
        this.days = days;
        this.name = name;
        this.creationDate = new Date();
        history = new ArrayList<HabitHistoryElement>();
    }

    public void complete()
    {
        Log.i("info", "completing habit " + this.name);
        this.complete = true;
        addToHistory();
    }

    private void addToHistory()
    {
        history.add(new HabitHistoryElement(this.complete, this.name));
    }

    public void removeFromHistory(HabitHistoryElement historyElement)
    {
        history.remove(historyElement);
        if (history.size() == 0)
        {
            this.complete = false;
        }
    }

    public void removeTodayFromHistory()
    {
        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(new Date());

        for(HabitHistoryElement e : history)
        {
            Calendar histCal = Calendar.getInstance();
            todayCal.setTime(e.getDate());
            if(histCal.DATE == todayCal.DATE)
            {
                history.remove(e);
            }
        }
    }

    public Boolean isComplete()
    {
        return this.complete;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<HabitHistoryElement> getHistory()
    {
        if(history == null)
        {
            history = new ArrayList<HabitHistoryElement>();
        }
        return history;
    }

    public ArrayList<Day> getDays()
    {
        return days;
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

    public Integer getCompletions()
    {
        return this.history.size();
    }

    public Integer getIncompletes()
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
            for(HabitHistoryElement e : history)
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

    @Override
    public String toString()
    {
        if(complete)
        {
            return this.name + " \n Completed " + getTodaysCompletionCount().toString() +  " times today!";
        }
        else
        {
            return this.name + " \n Have not completed today...";
        }
    }

    private Integer getTodaysCompletionCount()
    {
        int count = 0;
        for(HabitHistoryElement e : history)
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

    //TODO: Handle Multiples.
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
        if (history != null ? !history.equals(habit.history) : habit.history != null)
        {
            return false;
        }
        return complete != null ? complete.equals(habit.complete) : habit.complete == null;

    }

    @Override
    public int hashCode()
    {
        int result = creationDate != null ? creationDate.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (days != null ? days.hashCode() : 0);
        result = 31 * result + (history != null ? history.hashCode() : 0);
        result = 31 * result + (complete != null ? complete.hashCode() : 0);
        return result;
    }
}
