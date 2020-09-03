package com.myappcompany.imran.numbershapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    class Number {
        int number;
        public boolean isSquare() {
            double squareRoot = Math.sqrt(number);
            if (squareRoot == Math.floor(squareRoot)) {
                return true;
            } else {
                return false;
            }
        }
        public boolean isTriangular() {
            int x = 1;
            int triangularNumber = 1;
            while (triangularNumber < number) {
                x++;
                triangularNumber = triangularNumber + x;
            }
            if (triangularNumber == number) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void testNumber(View view) {
        Log.i("Info", "Button Pressed");
        EditText editText = (EditText) findViewById(R.id.editTextNumber);
        String message;
        if (editText.getText().toString().isEmpty()){
            message = "Please enter a number";
        } else {
            Number myNumber = new Number();
            myNumber.number = Integer.parseInt(editText.getText().toString());
            message = editText.getText().toString();
            if (myNumber.isSquare() && myNumber.isTriangular()) {
                message += " is Square and Triangular";
            } else if (myNumber.isSquare()) {
                message += " is Square Number but not Triangular";
            } else if (myNumber.isTriangular()) {
                message += " is Triangular Number but not Square";
            } else {
                message += " is neither Sqaure nor Traingular Number";
            }
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}