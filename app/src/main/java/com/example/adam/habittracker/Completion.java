package com.example.adam.habittracker;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A completion exists to hold information about the completion of a habit. I decided to us this, as
 * opposed to just holding a list of millis to represent time, as it simplifies representing them
 * as a string in the ListView due to the presence of the toString() override.
 * Created by Adam on 9/21/2016.
 */
public class Completion
{
    private Date date;
    private String name;

    public Completion(String name)
    {
        this.name = name;
        this.date = new Date();
    }

    public String toString()
    {
        return this.name + " completed on " + new SimpleDateFormat("EEEE, d MMMM, yyyy, hh:mm aa").format(date);
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

        Completion that = (Completion) o;

        if (date != null ? !date.equals(that.date) : that.date != null)
        {
            return false;
        }
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode()
    {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
