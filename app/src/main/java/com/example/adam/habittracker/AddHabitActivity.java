package com.example.adam.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;

public class AddHabitActivity extends AppCompatActivity
{
    private HabitListController habitsController = HabitListController.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        Button addButton = (Button) findViewById(R.id.addHabit);
        addButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                ArrayList<Integer> days = new ArrayList<Integer>(0);

                CheckBox sundayCB = (CheckBox)findViewById(R.id.sunday);
                CheckBox mondayCB = (CheckBox)findViewById(R.id.monday);
                CheckBox tuesdayCB = (CheckBox)findViewById(R.id.tuesday);
                CheckBox wednesdayCB = (CheckBox)findViewById(R.id.wednesday);
                CheckBox thrusdayCB = (CheckBox)findViewById(R.id.thursday);
                CheckBox fridayCB = (CheckBox)findViewById(R.id.friday);
                CheckBox saturdayCB = (CheckBox)findViewById(R.id.saturday);

                EditText nameField = (EditText)findViewById(R.id.habitName);

                if(sundayCB.isChecked())
                {
                    days.add(Calendar.SUNDAY);
                }

                if(mondayCB.isChecked())
                {
                    days.add(Calendar.MONDAY);
                }

                if(tuesdayCB.isChecked())
                {
                    days.add(Calendar.TUESDAY);
                }

                if(wednesdayCB.isChecked())
                {
                    days.add(Calendar.WEDNESDAY);
                }

                if(thrusdayCB.isChecked())
                {
                    days.add(Calendar.THURSDAY);
                }

                if(fridayCB.isChecked())
                {
                    days.add(Calendar.FRIDAY);
                }

                if(saturdayCB.isChecked())
                {
                    days.add(Calendar.SATURDAY);
                }

                //Check if any days were selected and a name was inputted.
                if (days.size() > 0 && days.size() < 8 && nameField.getText().toString().length() > 0)
                {
                    Habit newHabit = new Habit(nameField.getText().toString(), Day.getMultipleDays(days));
                    habitsController.addHabit(newHabit);
                    finish();
                }
            }
        });
    }
}
