package com.example.adam.habittracker;

import android.app.Activity;
import android.text.Layout;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Adam on 9/26/2016.
 */
public class MainLayoutController
{
    private Activity mainActivity;
    private Day currentDay = new Day(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
    private ListView habitListView;
    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    private ArrayAdapter<Habit> adapter;

    public MainLayoutController(Activity activity)
    {
        this.mainActivity = activity;
    }

    public void init()
    {
        Calendar currentDate = Calendar.getInstance();
        setDate(currentDate);
        getHabitList(currentDate);
    }

    private void getHabitList(Calendar calendar)
    {
        currentDay.addHabit(new Habit("Clean my immense China collection"));
        calendar.get(Calendar.DAY_OF_WEEK);
        //ListView incompleteHabits = (ListView) mainActivity.findViewById(R.id.incompleteHabitsList);
        ListView habitListView = (ListView) mainActivity.findViewById(R.id.completeHabitsList);

        habitList = currentDay.getHabits();
        adapter = new ArrayAdapter<Habit>(mainActivity, R.layout.list_item, habitList);
        Log.d("DEBUG", new Boolean(habitListView == null).toString());
        habitListView.setAdapter(adapter);
    }

    private void setDate(Calendar calendar)
    {
        TextView displayedDateElement = (TextView)mainActivity.findViewById(R.id.currentDate);

        Log.d("WEEEEE", new SimpleDateFormat("EEEE, d MMMM yyyy").format(calendar.getTime()));

        displayedDateElement.setText(new SimpleDateFormat("EEEE, d MMMM yyyy").format(calendar.getTime()));
    }
}
