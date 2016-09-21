package com.example.adam.habittracker;

import java.util.Date;

/**
 * Created by Adam on 9/21/2016.
 */
public class habitHistoryElement
{
    public habitHistoryElement(Boolean completed)
    {
        this.completed = completed;
        this.date = new Date();
    }

    private Date date;
    private Boolean completed;
}
