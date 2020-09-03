package com.myappcompany.imran.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText editText = findViewById(R.id.editText);
        //Pull out particular note id
        Intent intent = getIntent();
        //final int noteId = intent.getIntExtra("noteId", -1); //-1 is not something which Array will pass // Moving to top to access in else statememt below
        noteId = intent.getIntExtra("noteId", -1); //-1 is not something which Array will pass // Moving to top to access in else statememt below
        if (noteId != -1) {
            editText.setText(MainActivity.notes.get(noteId)); //Pull particular note using noteId
        } else { //if its a new note, default will be -1 which is invalid, so this code will work
            MainActivity.notes.add(""); //Add empty note
            noteId = MainActivity.notes.size() -1; // -1 to set the correct index on Array based on noteId
        }

        //To determine changes in editText
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //to update notes in Array
                MainActivity.notes.set(noteId, String.valueOf(charSequence));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                //update shared preferences
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.myappcompany.imran.notes", Context.MODE_PRIVATE);
                //Instead of Object Serializer trying String set
                HashSet<String> set = new HashSet<>(MainActivity.notes); //Convert list in to set
                sharedPreferences.edit().putStringSet("notes", set).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}