package com.myappcompany.imran.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public void login(View view){

        EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        EditText passwordEditTest = (EditText) findViewById(R.id.passwordEditTest);

        Log.i( "Info", "Button Pressed!!");
        Log.i( "Username", usernameEditText.getText().toString());
        Log.i( "Password", passwordEditTest.getText().toString());
        Toast.makeText(this, "Hi There", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}