package com.example.adam.habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * This class is in charge of controlling the "activity_all_habits" layout, which is accessed via
 * the options menu in the Main Activity. It simply shows all of the habits, including inactive ones,
 * allowing the user to view their history, and delete the habit altogether.
 */

public class AllHabitsActivity extends AppCompatActivity
{
    private HabitListController habitListController = HabitListController.getInstance();

    ArrayAdapter<Habit> allHabitsAdapter;

    private Habit contextHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_habits);

        allHabitsAdapter = new ArrayAdapter<Habit>(this, R.layout.list_item, habitListController.getAllHabits());
        ListView allHabitsListView = (ListView)findViewById(R.id.totalHabitList);
        allHabitsListView.setAdapter(allHabitsAdapter);
        registerForContextMenu(allHabitsListView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        contextHabit = (Habit)((ListView)v).getItemAtPosition(acmi.position);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.habit_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.history:
                openHabitHistory();
                return true;
            case R.id.delete:
                Log.i("info", "Habit " + contextHabit.getName() + "selected for delete.");
                habitListController.removeHabit(contextHabit);
                allHabitsAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    private void openHabitHistory()
    {
        Intent intentHistory = new Intent(this, HabitHistoryActivity.class);
        habitListController.setHistoryHabit(contextHabit);
        startActivity(intentHistory);
    }
}
