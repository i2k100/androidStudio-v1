/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {

  public void signUpClicked(View view) {
    EditText usernameEditText = findViewById(R.id.usernameEditText);
    EditText passwordEditText = findViewById(R.id.passwordEditText);

    if (usernameEditText.getText().toString() == "" || passwordEditText.getText().toString() == "") {
      Toast.makeText(this, "Username and Password are required", Toast.LENGTH_SHORT).show();
    } else {
      ParseUser user = new ParseUser();
      user.setUsername(usernameEditText.getText().toString());
      user.setPassword(usernameEditText.getText().toString());

      user.signUpInBackground(new SignUpCallback() {
        @Override
        public void done(ParseException e) {
          if (e == null) {
            Log.i("Sign up", "Success");
          } else {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
          }
        }
      });
    }
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ParseAnalytics.trackAppOpenedInBackground(getIntent());

    /*
    //log in
    ParseUser.logInInBackground("nick", "myPass", new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException e) {
        if (user != null) {
          Log.i("Success", "We logged in");
        } else {
          e.printStackTrace();
        }
      }
    });

    */
    /*

    ParseUser.logOut();

    if (ParseUser.getCurrentUser() != null) {
      Log.i("Signed in", ParseUser.getCurrentUser().getUsername());
    } else {
      Log.i("No luck", "Not signed in");
    }
    */
    /* Sing up
    ParseUser user = new ParseUser();
    user.setUsername("nick");
    user.setPassword("myPass");

    user.signUpInBackground(new SignUpCallback() {
      @Override
      public void done(ParseException e) {
        if ( e == null) {
          Log.i("Sign up OK!", "We did it");
        } else {
          e.printStackTrace();
        }
      }
    });
    */

    //to pull multiple records

    //ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

    //query.whereEqualTo("username", "Sean");
    //query.setLimit(5);
    /*
    query.whereGreaterThan("score", 0);

    query.findInBackground(new FindCallback<ParseObject>() {
      @Override
      public void done(List<ParseObject> objects, ParseException e) {
        if (e == null) {
          if (objects != null) {
          //if (objects.size() > 0) {
            for (ParseObject object : objects) {

              object.put("score", object.getInt("score") + 20);
              object.saveInBackground();

              //Log.i("username", object.getString("username"));
              //Log.i("score", Integer.toString(object.getInt("score")));
            }
          }
        } else {
          e.printStackTrace();
        }
      }
    });

    */

    /*
    ParseObject score = new ParseObject("Score");
    score.put("username", "Sean");
    score.put("score", 65);
    score.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {
        if (e == null) {
          //ok
          Log.i("Success", "We saved the score");
        } else {
          //somehting wrong
          e.printStackTrace();
        }
      }
    });
    */
    //Create a tweet class, username tweet, save it to parse,query it, update the tweet
    /*
    ParseObject tweet = new ParseObject("Tweet");
    tweet.put("username", "i2k100");
    tweet.put("tweet", "I like cheese");
    tweet.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {
        if (e == null) {
          Log.i("Success", "We have saved the tweet");
        } else {
         e.printStackTrace();
        }
      }
    });

    ParseQuery<ParseObject> query = ParseQuery.getQuery("Tweet");
    query.getInBackground("lN0CpZNlO5", new GetCallback<ParseObject>() {
      @Override
      public void done(ParseObject object, ParseException e) {
        if (e == null && object != null) {
          //ok
          //object.getString("username");
          object.put("tweet", "Cheese is ok..");
          object.put("username", "i2k100");
          object.saveInBackground();
          Log.i("username",object.getString("username"));
          Log.i("tweet",object.getString("tweet"));
        } else {
          e.printStackTrace();
        }
      }
    });
    */

    /*
    ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
    query.getInBackground("lGW0XSuvCy", new GetCallback<ParseObject>() {
      @Override
      public void done(ParseObject object, ParseException e) {
        if (e == null && object != null) {
          //ok
          //object.getString("username");
          object.put("score", 75);
          object.saveInBackground();
          Log.i("username",object.getString("username"));
          Log.i("scroe",Integer.toString(object.getInt("score")));
        } else {
          e.printStackTrace();
        }
      }
    });
    */

    
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}