package com.parse.starter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        setTitle("Your Feed");

        final ListView listView = findViewById(R.id.listView);
        final List<Map<String, String>> tweetData = new ArrayList<>();

        SimpleAdapter simpleAdapter;

        //Creating Map manaully
//        for (int i = 1; i <= 5; i++) {
////            Map<String, String> tweetInfo = new HashMap<>();
////            tweetInfo.put("content", "Tweet Content" + Integer.toString(i));
////            tweetInfo.put("username", "User" + Integer.toString(i));
////            tweetData.add(tweetInfo);
////        }

        ParseQuery<ParseObject> queryA = ParseQuery.getQuery("Tweet");
        queryA.whereContainedIn("username", ParseUser.getCurrentUser().getList("isFollowing"));

        ParseQuery<ParseObject> queryB = ParseQuery.getQuery("Tweet");
        queryB.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());

        List<ParseQuery<ParseObject>> list = new ArrayList<ParseQuery<ParseObject>>();
        list.add(queryA);
        list.add(queryB);

        ParseQuery<ParseObject> query = ParseQuery.or(list);
        query.orderByDescending("createdAt");
        query.setLimit(20);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject tweet : objects) {
                        Map<String, String> tweetInfo = new HashMap<>();
                        tweetInfo.put("content", tweet.getString("tweet"));
                        tweetInfo.put("username", tweet.getString("username"));
                        tweetData.add(tweetInfo);
                    }
                    //Different adapter for Maps
                    SimpleAdapter simpleAdapter = new
                            SimpleAdapter(FeedActivity.this, tweetData, android.R.layout.simple_list_item_2,
                            new String[] {"content", "username"},   //fetching from above array
                            new int[] {android.R.id.text1, android.R.id.text2}); //setting on list view for sub items

                    listView.setAdapter(simpleAdapter);
                }
            }
        });

//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tweet");
//        query.whereContainedIn("username", ParseUser.getCurrentUser().getList("isFollowing"));
//        //query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
//        query.orderByDescending("createdAt");
//        query.setLimit(20);
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//                if (e == null) {
//                    for (ParseObject tweet : objects) {
//                        Map<String, String> tweetInfo = new HashMap<>();
//                        tweetInfo.put("content", tweet.getString("tweet"));
//                        tweetInfo.put("username", tweet.getString("username"));
//                        tweetData.add(tweetInfo);
//                    }
//                    //Different adapter for Maps
//                    SimpleAdapter simpleAdapter = new
//                            SimpleAdapter(FeedActivity.this, tweetData, android.R.layout.simple_list_item_2,
//                            new String[] {"content", "username"},   //fetching from above array
//                            new int[] {android.R.id.text1, android.R.id.text2}); //setting on list view for sub items
//
//                    listView.setAdapter(simpleAdapter);
//                }
//            }
//        });

//        ParseQuery<ParseObject> queryFeed = ParseQuery.getQuery("Tweet");
//        queryFeed.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
//        queryFeed.orderByDescending("createdAt");
//        queryFeed.setLimit(20);
//        queryFeed.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//                if (e == null) {
//                    for (ParseObject tweet : objects) {
//                        Map<String, String> tweetInfo = new HashMap<>();
//                        tweetInfo.put("content", tweet.getString("tweet"));
//                        tweetInfo.put("username", tweet.getString("username"));
//                        tweetData.add(tweetInfo);
//                    }
//                    //Different adapter for Maps
//                    SimpleAdapter simpleAdapter2 = new
//                            SimpleAdapter(FeedActivity.this, tweetData, android.R.layout.simple_list_item_2,
//                            new String[] {"content", "username"},   //fetching from above array
//                            new int[] {android.R.id.text1, android.R.id.text2}); //setting on list view for sub items
//
//                    listView.setAdapter(simpleAdapter2);
//                }
//            }
//        });

//        //Different adapter for Maps
//        SimpleAdapter simpleAdapter = new
//                SimpleAdapter(this, tweetData, android.R.layout.simple_list_item_2,
//                new String[] {"content", "username"},   //fetching from above array
//                new int[] {android.R.id.text1, android.R.id.text2}); //setting on list view for sub items
//
//        listView.setAdapter(simpleAdapter);
    }
}