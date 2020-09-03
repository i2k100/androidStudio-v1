package com.myappcompany.imran.listdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = findViewById(R.id.myListView);

        //final ArrayList<String> myFamily = new ArrayList<String>();
        final ArrayList<String> myFamily = new ArrayList<String>(asList("Mark", "Jane", "Sussy", "Jan"));
        //myFamily.add("Nick");
        //myFamily.add("Sarah");
        //myFamily.add("John");
        //myFamily.add("Fido");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myFamily);
        myListView.setAdapter(arrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //adapterView.setVisibility(View.GONE);
                //Log.i("Person selected", myFamily.get(i));
                Toast.makeText(getApplicationContext(), "Hello " + myFamily.get(i), Toast.LENGTH_LONG).show();
            }
        });
    }
}