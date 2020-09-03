package com.myappcompany.imran.timestables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView timesTablesListView;
    public void generateTimesTable(int timesTablesNumber) {
        ArrayList<String> timesTablesContent = new ArrayList<String>();
        //generating tables till factors of 100
        for (int j = 1; j <= 100; j++) {
            timesTablesContent.add(Integer.toString(j * timesTablesNumber));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timesTablesContent);
        timesTablesListView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar timesTablesSeekBar = (SeekBar) findViewById(R.id.timesTablesSeekBar);
        timesTablesListView = findViewById(R.id.timesTablesListView);
        int max = 20;
        int startingPosition = 10;
        timesTablesSeekBar.setMax(max);
        timesTablesSeekBar.setProgress(startingPosition); //Current position

        //Display default table
        generateTimesTable(startingPosition);

        timesTablesSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int min = 1;
                int timesTablesNumber;
                if (i < min){
                    timesTablesNumber = min;
                    timesTablesSeekBar.setProgress(min);
                } else {
                    timesTablesNumber = i;
                }
                //Log.i("Seekbar value ", Integer.toString(timesTablesNumber));
                generateTimesTable(timesTablesNumber);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}