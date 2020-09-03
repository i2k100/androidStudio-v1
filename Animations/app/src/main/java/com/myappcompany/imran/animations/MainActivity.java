package com.myappcompany.imran.animations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    //boolean bartIsShowing = true;
    public void fade(View view) {
        Log.i("Info", "ImageView");
        ImageView bartImageView = (ImageView) findViewById(R.id.bartImageView);
        //bartImageView.animate().alpha(0).setDuration(2000);
        ImageView homerImageView = (ImageView) findViewById(R.id.homerImageView);
        //homerImageView.animate().alpha(1).setDuration(2000);

        //bartImageView.animate().translationXBy(-1000).setDuration(2000);
        //bartImageView.animate().rotation(1800).alpha(0).setDuration(2000);
        bartImageView.animate().scaleX(0.5f).scaleY(0.5f).setDuration(2000);

        /*
        if (bartIsShowing) {
            bartIsShowing = false;
            bartImageView.animate().alpha(0).setDuration(2000);
            homerImageView.animate().alpha(1).setDuration(2000);
        } else {
            bartIsShowing = true;
            bartImageView.animate().alpha(1).setDuration(2000);
            homerImageView.animate().alpha(0).setDuration(2000);
        }*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView bartImageView = (ImageView) findViewById(R.id.bartImageView);
        bartImageView.setX(-1000);
        bartImageView.animate().translationXBy(1000).rotation(3600).setDuration(2000);
    }
}