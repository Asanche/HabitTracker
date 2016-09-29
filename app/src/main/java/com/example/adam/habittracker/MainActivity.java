package com.example.adam.habittracker;

import android.app.Activity;
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
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{
    private HabitListController habitsController = HabitListController.getInstance();

    private ListView completeHabitListView;
    private ListView incompleteHabitListView;

    private ArrayAdapter<Habit> completeAdapter;
    private ArrayAdapter<Habit> incompleteAdapter;

    private Habit contextHabit;
    private Activity mainActivity = this;

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
                Log.i("info", "Habit " + contextHabit.getName() + "selected for uncomplete.");
                if(contextHabit.isComplete())
                {
                    contextHabit.unComplete();
                    habitsController.updateHabits();
                    incompleteAdapter.notifyDataSetChanged();
                    completeAdapter.notifyDataSetChanged();
                }
                return true;
            default:
                return super.onContextItemSelected(item);
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

        incompleteHabitListView = (ListView)findViewById(R.id.incompleteHabitsList);
        completeHabitListView = (ListView)findViewById(R.id.completeHabitsList);

        incompleteAdapter = new ArrayAdapter<Habit>(this, R.layout.list_item, habitsController.getIncompleteHabits());
        completeAdapter = new ArrayAdapter<Habit>(this, R.layout.list_item, habitsController.getCompleteHabits());

        setHabitClickEvents(incompleteHabitListView, completeHabitListView);

        completeHabitListView.setAdapter(completeAdapter);
        incompleteHabitListView.setAdapter(incompleteAdapter);
    }

    private void setHabitClickEvents(final ListView incompleteHabitListView, final ListView completeHabitListView)
    {
        incompleteHabitListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?>adapter, View v, int position, long id)
            {
                Habit selectedHabit = (Habit)adapter.getItemAtPosition(position);
                selectedHabit.complete();

                habitsController.updateHabits();
                incompleteAdapter.notifyDataSetChanged();
                completeAdapter.notifyDataSetChanged();
            }
        });
        registerForContextMenu(completeHabitListView);

        incompleteHabitListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?>adapter, View v, int position, long id)
            {
                Habit selectedHabit = (Habit)adapter.getItemAtPosition(position);
                selectedHabit.unComplete();

                openHabitHistory();
                return true;
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

    public void openHabitHistory()
    {
        Intent intentHistory = new Intent(this, HabitHistoryActivity.class);
        intentHistory.putExtra("Habit", contextHabit);
        startActivity(intentHistory);
    }
}
