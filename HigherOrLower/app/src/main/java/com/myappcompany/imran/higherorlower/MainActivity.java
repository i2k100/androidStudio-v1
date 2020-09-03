package com.myappcompany.imran.higherorlower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int randomNumber; //To access in both methods
    public void generateRandomNumber(){
        Random rand = new Random();
        randomNumber = rand.nextInt(20) + 1;
    }

    public void guess(View view) {
        Log.i("Info", "Button pressed");
        Log.i("Random Number", Integer.toString(randomNumber));
        EditText editText = (EditText) findViewById(R.id.editTextNumber);
        Log.i("Entered Value", editText.getText().toString());
        int guessValue = Integer.parseInt(editText.getText().toString());
        String message;
        if (guessValue > randomNumber) {
            message = "Lower!";
        } else if (guessValue < randomNumber){
            message = "Higher!";
        } else {
            message = "You got it!!, Try again!!";
            generateRandomNumber();
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Random rand = new Random();
        //randomNumber = rand.nextInt(20) + 1;
        generateRandomNumber();
    }
}