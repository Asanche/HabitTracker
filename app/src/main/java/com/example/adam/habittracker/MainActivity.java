package com.example.adam.habittracker;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

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
}
