package com.example.adam.habittracker;

import android.app.Activity;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    private ListView incompleteHabitListView;
    private ArrayList<Habit> incompleteHabitList = new ArrayList<Habit>();
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
        Log.i("trace", "MainLayoutController getHabitList");
        currentDay.addHabit(new Habit("Clean my immense China collection"));
        calendar.get(Calendar.DAY_OF_WEEK);
        //ListView incompleteHabits = (ListView) mainActivity.findViewById(R.id.incompleteHabitsList);
        incompleteHabitListView = (ListView) mainActivity.findViewById(R.id.incompleteHabitsList);

        incompleteHabitList = currentDay.getHabits();
        adapter = new ArrayAdapter<Habit>(mainActivity, R.layout.list_item, incompleteHabitList);

        setHabitClickEvents(incompleteHabitListView);


        incompleteHabitListView.setAdapter(adapter);
    }

    private void setHabitClickEvents(ListView habitListView)
    {

        habitListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long id)
            {
                Log.d("DEBUG", adapter.getItemAtPosition(position).toString());
            }
        });
    }



    private void setDate(Calendar calendar)
    {
        Log.i("trace", "MainLayoutController setDate");

        TextView displayedDateElement = (TextView)mainActivity.findViewById(R.id.currentDate);

        Log.i("info", "Main scene date set to" + new SimpleDateFormat("EEEE, d MMMM yyyy").format(calendar.getTime()));

        displayedDateElement.setText(new SimpleDateFormat("EEEE, d MMMM yyyy").format(calendar.getTime()) + "\n");
    }
}
