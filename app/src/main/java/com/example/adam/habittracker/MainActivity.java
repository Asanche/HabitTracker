package com.example.adam.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;

public class MainActivity extends AppCompatActivity
{
    private CurrentHabitsController habitsController = CurrentHabitsController.getInstance();

    private MainLayoutController mainLayoutController = new MainLayoutController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.i("trace", "MainActivity onCreate");

        setContentView(R.layout.activity_main);

        super.onCreate(savedInstanceState);

        habitsController.init();
        mainLayoutController.init();
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
}
