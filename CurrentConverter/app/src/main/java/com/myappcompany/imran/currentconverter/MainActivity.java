package com.myappcompany.imran.currentconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convertCurrency(View view) {

        Log.i("Info", "Button Pressed!!");
        EditText editText = (EditText) findViewById(R.id.editTextNumberDecimal);
        String amountInDollars = editText.getText().toString();
        double amountInDollarsDouble = Double.parseDouble(amountInDollars);
        double amountinRupeesDouble = amountInDollarsDouble * 74.25;
        //String amountInRupees = Double.toString(amountinRupeesDouble);
        String amountInRupees = String.format("%.2f", amountinRupeesDouble);

        Log.i("Info", editText.getText().toString());
        Log.i("Amount in Rupees", amountInRupees);

        Toast.makeText(this, "$" + amountInDollars + " is Rs." + amountInRupees, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}