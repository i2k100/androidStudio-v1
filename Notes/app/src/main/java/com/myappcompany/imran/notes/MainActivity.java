package com.myappcompany.imran.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> notes = new ArrayList<>(); //Static to access this in second activity
    static ArrayAdapter arrayAdapter;
    SharedPreferences sharedPreferences; //moved here to display notes when user re open the app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //move this line to Oncreate to display notes while re opening
        sharedPreferences = getApplicationContext().getSharedPreferences("com.myappcompany.imran.notes", Context.MODE_PRIVATE);

        ListView listView = findViewById(R.id.listView);

        //get info about shared preference to check if there is somehting stored before add Example note
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes", null);
        if (set == null) {
            notes.add("Example note");
        } else {
            notes = new ArrayList(set);
        }

        //notes.add("Example note");

        //ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes); //moved this up top to access in second activity
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(arrayAdapter);

        //To move to second activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);
                // Add extra to get which particular note was selected
                intent.putExtra("noteId", i);
                startActivity(intent);
            }
        });

        //for long click display the alert
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int iTemToDelete = i; //creating new variable to access in below method to avoid conflict between int i in next method
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Item which they selected is identified by i in this method
                                notes.remove(iTemToDelete);
                                arrayAdapter.notifyDataSetChanged();

                                //update shared preferences
                                //SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.myappcompany.imran.notes", Context.MODE_PRIVATE); //Moving this top
                                //Instead of Object Serializer trying String set
                                HashSet<String> set = new HashSet<>(MainActivity.notes); //Convert list in to set
                                sharedPreferences.edit().putStringSet("notes", set).apply();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });
    }


    //This is for Menu code
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.add_note) {
            Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class); //for new intent
            startActivity(intent);
            return true;
        } else {
            return false;
        }
    }
}