package com.myappcompany.imran.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.myappcompany.imran.sharedpreferences", Context.MODE_PRIVATE); //How to do you want to save --> PRIVATE
        //sharedPreferences.edit().putString("username", "nick").apply(); //Thing which you are trying to save is username whoes value is nick. Apply saves it -- Commneted to show info is saved
        //String username = sharedPreferences.getString("username", ""); //s1 set to default in case value is not present
        //Log.i("This is the username", username);
        /* Commnetd to show saved Array
        ArrayList<String> friends = new ArrayList<>();
        friends.add("Fido");
        friends.add("Sarah");
        friends.add("Sean");
        friends.add("Jane");

        //Object Serializer class used which was created by Rob
        try {
            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(friends)).apply();
            Log.i("friends", ObjectSerializer.serialize(friends));
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        ArrayList<String> newFriends = new ArrayList<>();
        try {
            newFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("new Friends", newFriends.toString());
    }
}