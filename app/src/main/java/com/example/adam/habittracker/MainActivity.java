package com.example.adam.habittracker;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{
    private HabitListController habitsController = HabitListController.getInstance();

    private ListView habitListView;

    private ArrayAdapter<Habit> habitListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.i("trace", "MainActivity onCreate");

        setContentView(R.layout.activity_main);

        super.onCreate(savedInstanceState);

        habitsController.setMainActivity(this);
        habitsController.updateHabits();
        init();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        upDateHabitList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.all:
                Intent intentAll = new Intent(this, AllHabitsActivity.class);
                startActivity(intentAll);
                return true;
            case R.id.add:
                Intent intentAdd = new Intent(this, AddHabitActivity.class);
                startActivity(intentAdd);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void init()
    {
        Log.i("trace", "MainLayoutController init");

        Calendar currentDate = Calendar.getInstance();
        setDate(currentDate);
        getHabitList(currentDate);
    }

    private void getHabitList(Calendar calendar)
    {
        Log.i("trace", "MainLayoutController getHabitList");

        habitListView = (ListView)findViewById(R.id.canCompleteHabitsList);

        habitListAdapter = new ArrayAdapter<Habit>(this, R.layout.list_item, habitsController.getCurrentHabits());

        setHabitClickEvents(habitListView);

        habitListView.setAdapter(habitListAdapter);
    }

    private void setHabitClickEvents(final ListView habitListView)
    {
        habitListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?>adapter, View v, int position, long id)
            {
                Habit selectedHabit = (Habit)adapter.getItemAtPosition(position);
                selectedHabit.complete();

                upDateHabitList();
            }
        });

        habitListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?>adapter, View v, int position, long id)
            {
                Habit selectedHabit = (Habit)adapter.getItemAtPosition(position);

                openHabitHistory(selectedHabit);
                return true;
            }
        });
    }

    private void setDate(Calendar calendar)
    {
        Log.i("trace", "MainLayoutController setDate");

        TextView displayedDateElement = (TextView)findViewById(R.id.currentDate);

        Log.i("info", "Main scene date set to" + new SimpleDateFormat("EEEE, d MMMM, yyyy").format(calendar.getTime()));

        displayedDateElement.setText(new SimpleDateFormat("EEEE, d MMMM, yyyy").format(calendar.getTime()) + "\n");
    }

    private void upDateHabitList()
    {
        habitsController.updateHabits();
        habitListAdapter.notifyDataSetChanged();
    }


    public void openHabitHistory(Habit habit)
    {
        Intent intentHistory = new Intent(this, HabitHistoryActivity.class);
        habitsController.setHistoryHabit(habit);
        startActivity(intentHistory);
    }
}
