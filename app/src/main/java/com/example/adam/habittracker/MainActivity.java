package com.example.adam.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{
    private HabitListController habitsController = HabitListController.getInstance();
    private CompletionListController completionListController = CompletionListController.getInstance();

    private ListView completeHabitListView;
    private ListView incompleteHabitListView;

    private ArrayAdapter<Habit> completeAdapter;
    private ArrayAdapter<Habit> incompleteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.i("trace", "MainActivity onCreate");

        setContentView(R.layout.activity_main);

        super.onCreate(savedInstanceState);

        habitsController.updateCurrentHabits();
        init();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        completeAdapter.notifyDataSetChanged();
        incompleteAdapter.notifyDataSetChanged();
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

        ArrayList<Integer> days = new ArrayList<Integer>();
        days.add(Calendar.SATURDAY);
        days.add(Calendar.MONDAY);

        Habit newHabit = new Habit("Topple the Russian Oligarchical system", Day.getMultipleDays(days));
        habitsController.addHabit(newHabit);

        incompleteHabitListView = (ListView)findViewById(R.id.incompleteHabitsList);
        completeHabitListView = (ListView)findViewById(R.id.completeHabitsList);

        incompleteAdapter = new ArrayAdapter<Habit>(this, R.layout.list_item, habitsController.getIncompleteHabits());
        completeAdapter = new ArrayAdapter<Habit>(this, R.layout.list_item, habitsController.getCompleteHabits());

        setHabitClickEvents(incompleteHabitListView);

        completeHabitListView.setAdapter(completeAdapter);
        incompleteHabitListView.setAdapter(incompleteAdapter);
    }

    private void setHabitClickEvents(final ListView incompleteHabitListView)
    {

        incompleteHabitListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?>adapter, View v, int position, long id)
            {
                Habit selectedHabit = (Habit)adapter.getItemAtPosition(position);
                selectedHabit.complete();
                completionListController.newCompletion(selectedHabit);

                habitsController.updateCurrentHabits();
                incompleteAdapter.notifyDataSetChanged();
                completeAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setDate(Calendar calendar)
    {
        Log.i("trace", "MainLayoutController setDate");

        TextView displayedDateElement = (TextView)findViewById(R.id.currentDate);

        Log.i("info", "Main scene date set to" + new SimpleDateFormat("EEEE, d MMMM yyyy").format(calendar.getTime()));

        displayedDateElement.setText(new SimpleDateFormat("EEEE, d MMMM yyyy").format(calendar.getTime()) + "\n");
    }
}
