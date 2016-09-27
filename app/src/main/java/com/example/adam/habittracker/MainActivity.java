package com.example.adam.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{

    private MainLayoutController mainLayoutController = new MainLayoutController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.i("trace", "MainActivity onCreate");

        setContentView(R.layout.activity_main);

        super.onCreate(savedInstanceState);

        mainLayoutController.init();
    }
}
