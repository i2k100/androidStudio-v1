package com.myappcompany.imran.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");
        myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Nick', 28)"); //Single quotes in SQL for string or character
        myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Rick', 33)");

        Cursor c = myDatabase.rawQuery("SELECT * FROM users", null); // To retrieve from SQL table
        //To access name and age you need indexes
        int nameIndex = c.getColumnIndex("name");
        int ageIndex = c.getColumnIndex("age");

        c.moveToFirst(); //move our cursor to starting position
        //while ( c != null) { //Old version
        while (!c.isAfterLast()) {
            Log.i("name", c.getString(nameIndex));
            Log.i("age", c.getString(ageIndex));
            c.moveToNext();
        }

        */
        /*
        try {
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Events", MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS events (name VARCHAR, year INT(4))");
            sqLiteDatabase.execSQL("INSERT INTO events (name, year) VALUES ('Millenium', 2000)");
            sqLiteDatabase.execSQL("INSERT INTO events (name, year) VALUES ('Nick started teachnig', 2014)");
            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM events", null);
            int nameIndex = c.getColumnIndex("name");
            int yearIndex = c.getColumnIndex("year");
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Log.i("Events name", c.getString(nameIndex));
                Log.i("Events year", Integer.toString(c.getInt(yearIndex)));
                c.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         */

        try {
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS newUsers (name VARCHAR, age INT(3), id INTEGER PRIMARY KEY)"); //set primary key

            //sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Nick', 23)");
            //sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Nick', 43)");
            //sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Dave', 14)");

            //Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE age < 18", null);
            //Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE name = 'Nick' AND age = 43", null);
            //Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE name LIKE '%a%' LIMIT 1", null);

            //sqLiteDatabase.execSQL("DELETE FROM users WHERE name = 'Dave'");
            sqLiteDatabase.execSQL("DELETE FROM newUsers WHERE id = 2");

            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM newUsers", null);
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Log.i("Users name", c.getString(nameIndex));
                Log.i("Users age", Integer.toString(c.getInt(ageIndex)));
                Log.i("Users age", Integer.toString(c.getInt(idIndex)));
                c.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}