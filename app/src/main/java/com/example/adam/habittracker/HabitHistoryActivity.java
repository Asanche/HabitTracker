package com.example.adam.habittracker;

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
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class is responsible for listing the history of a habit, links up with the android API to
 * display the "activity_habit_history" layout correctly.
 */

public class HabitHistoryActivity extends AppCompatActivity
{
    private HabitListController habitListController = HabitListController.getInstance();

    private Habit habit;
    private ListView historyListView;
    private ArrayAdapter<Completion> historyAdapter;
    private Completion contextCompletion;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_history);
        habit = habitListController.getHistoryHabit();

        getHistory();
        setStrings();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        contextCompletion = (Completion)((ListView)v).getItemAtPosition(acmi.position);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.history_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.deleteHistory:
                Log.i("info", "History for " + habit.getName() + " on " + contextCompletion.getDate().toString() + " selected for uncomplete.");

                Calendar contextCal = Calendar.getInstance();
                contextCal.setTime(contextCompletion.getDate());

                Calendar todayCal = Calendar.getInstance();
                todayCal.setTime(new Date());

                Log.i("info", habit.isComplete().toString());

                habit.removeFromHistory(contextCompletion);
                historyAdapter.notifyDataSetChanged();
                setStrings();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void getHistory()
    {
        Log.i("trace", "MainLayoutController getHabitList");

        historyListView = (ListView)findViewById(R.id.historyList);

        registerForContextMenu(historyListView);

        historyAdapter = new ArrayAdapter<Completion>(this, R.layout.list_item, habit.getHistory());

        historyListView.setAdapter(historyAdapter);
    }


    private void setStrings()
    {
        Log.i("trace", "MainLayoutController setStrings");

        TextView title = (TextView)findViewById(R.id.currentHabit);
        TextView creationDate = (TextView)findViewById(R.id.creationDate);
        TextView days = (TextView)findViewById(R.id.days);
        TextView completions = (TextView)findViewById(R.id.completes);
        TextView incompletes = (TextView)findViewById(R.id.incompletes);

        title.setText("History for " + habit.getName());
        creationDate.setText("Creation Date: " + new SimpleDateFormat("EEEE, d MMMM, yyyy").format( habit.getCreationDate()));
        days.setText("Days Active: " + habit.daysToString());
        completions.setText("Completions: " + habit.getCompletionCount().toString());
        incompletes.setText("Days Not Completed: " + habit.getIncompleteCount().toString());
    }
}
