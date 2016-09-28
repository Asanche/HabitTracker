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
    private CurrentHabitsController habitsController = CurrentHabitsController.getInstance();

    private Activity mainActivity;

    private ListView completeHabitListView;
    private ListView incompleteHabitListView;

    private ArrayAdapter<Habit> completeAdapter;
    private ArrayAdapter<Habit> incompleteAdapter;

    public MainLayoutController(Activity activity)
    {
        this.mainActivity = activity;
    }

    public void init()
    {
        Log.i("trace", "MainLayoutController init");
        ArrayList<Habit> completeHabitList = new ArrayList<Habit>();
        ArrayList<Habit> incompleteHabitList = new ArrayList<Habit>();

        Calendar currentDate = Calendar.getInstance();
        setDate(currentDate);
        getHabitList(currentDate);
    }

    private void getHabitList(Calendar calendar)
    {
        Log.i("trace", "MainLayoutController getHabitList");

        int[] days = {Calendar.MONDAY, Calendar.TUESDAY};
        Habit newHabit = new Habit("Topple the Russian Oligarchical system", Day.getMultipleDays(days));
        habitsController.addHabit(newHabit);

        incompleteHabitListView = (ListView) mainActivity.findViewById(R.id.incompleteHabitsList);
        completeHabitListView = (ListView) mainActivity.findViewById(R.id.completeHabitsList);

        incompleteAdapter = new ArrayAdapter<Habit>(mainActivity, R.layout.list_item, habitsController.getIncompleteHabits());
        completeAdapter = new ArrayAdapter<Habit>(mainActivity, R.layout.list_item, habitsController.getCompleteHabits());

        setHabitClickEvents(incompleteHabitListView);

        completeHabitListView.setAdapter(completeAdapter);
        incompleteHabitListView.setAdapter(incompleteAdapter);
    }

    private void setHabitClickEvents(final ListView incompleteHabitListView)
    {

        incompleteHabitListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long id)
            {
                Habit selectedHabit = (Habit)adapter.getItemAtPosition(position);
                selectedHabit.complete();

                habitsController.updateCurrentHabits();
                incompleteAdapter.notifyDataSetChanged();
                completeAdapter.notifyDataSetChanged();
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
