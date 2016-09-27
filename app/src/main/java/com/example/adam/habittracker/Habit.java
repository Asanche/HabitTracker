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
    private ArrayList<habitHistoryElement> history;

    public Habit(String name)
    {
        this.name = name;
        this.creationDate = new Date();
    }


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

    public ArrayList<habitHistoryElement> getHistory()
    {
        return history;
    }

    public void setHistory(ArrayList<habitHistoryElement> history)
    {
        this.history = history;
    }

    @Override
    public String toString()
    {
        String completedString = "Completed: ";
        return this.name + " | " + completedString;
    }

}
