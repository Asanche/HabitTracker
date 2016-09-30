package com.example.adam.habittracker;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Adam on 9/21/2016.
 */
public class HabitHistoryElement implements Serializable
{
    private Date date;
    private Boolean complete;
    private String name;

    public HabitHistoryElement(Boolean completed, String name)
    {
        this.complete = completed;
        this.name = name;
        this.date = new Date();
    }

    public String toString()
    {
        String completedString = (complete) ? " completed " : " incomplete ";
        return this.name + completedString + "on " + new SimpleDateFormat("EEEE, d MMMM, yyyy, hh:mm aa").format(date);
    }

    public Date getDate()
    {
        return date;
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

        HabitHistoryElement that = (HabitHistoryElement) o;

        if (date != null ? !date.equals(that.date) : that.date != null)
        {
            return false;
        }
        if (complete != null ? !complete.equals(that.complete) : that.complete != null)
        {
            return false;
        }
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode()
    {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (complete != null ? complete.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
