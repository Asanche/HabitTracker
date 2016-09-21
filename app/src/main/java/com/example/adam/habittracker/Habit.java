package com.example.adam.habittracker;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Adam on 9/21/2016.
 */
public class Habit
{
    private Date creationDate;
    private String name;
    private ArrayList<Day> occuranceDays;
    private ArrayList<habitHistoryElement> history;

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<Day> getOccuranceDays()
    {
        return occuranceDays;
    }

    public void setOccuranceDays(ArrayList<Day> occuranceDays)
    {
        this.occuranceDays = occuranceDays;
    }

    public ArrayList<habitHistoryElement> getHistory()
    {
        return history;
    }

    public void setHistory(ArrayList<habitHistoryElement> history)
    {
        this.history = history;
    }
}
