package com.myappcompany.imran.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    ArrayList<String> actorsName = new ArrayList<String>();
    ArrayList<String> imagesURL = new ArrayList<String>();
    ImageView actorImage;
    Button button0;
    Button button1;
    Button button2;
    Button button3;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int chosenActor;

    public void clickButton(View view) {
        Button buttonPressed = (Button) view;
        if (buttonPressed.getText().toString().equals(actorsName.get(chosenActor))) {
            Toast.makeText(this, "Correct!!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong :(", Toast.LENGTH_SHORT).show();
        }
        try {
            quiz();
        } catch (Exception e) {
            Log.i("Catch of Click", String.valueOf(e));
        }
    }

    public void quiz() {

        Random rand = new Random();
        chosenActor = rand.nextInt(actorsName.size());
        DownloadImage task = new DownloadImage();
        Bitmap myImage;
        try {
            myImage = task.execute(imagesURL.get(chosenActor)).get();
            actorImage.setImageBitmap(myImage);

        } catch (Exception e) {
            e.printStackTrace();
        }

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for (int i = 0; i < 4; i++ ) {
            if (i == locationOfCorrectAnswer) {
                answers.add(chosenActor);
            } else {
                int wrongAnswer = rand.nextInt(actorsName.size());
                while (wrongAnswer == chosenActor) {
                    wrongAnswer = rand.nextInt(actorsName.size());
                }
                answers.add(wrongAnswer);
            }
        }
        String choice1 = actorsName.get(answers.get(0));
        String choice2 = actorsName.get(answers.get(1));
        String choice3 = actorsName.get(answers.get(2));
        String choice4 = actorsName.get(answers.get(3));

        button0.setText(choice1);
        button1.setText(choice2);
        button2.setText(choice3);
        button3.setText(choice4);

    }

    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }


        }
    }




    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(reader);

                String inputLine;
                StringBuffer html = new StringBuffer();
                while ((inputLine = bufferedReader.readLine()) != null) {
                    html.append(inputLine);
                }
                bufferedReader.close();

                result = html.toString();
                return result;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        String result = null;
        try {
            result = task.execute("https://www.imdb.com/list/ls052283250/").get();
            Log.i("Content of URL", result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Pattern p = Pattern.compile("\"src=\"(.*?)\"width=");
        Matcher m = p.matcher(result);
        while (m.find()) {
            imagesURL.add(m.group(1));
        }

        p = Pattern.compile("<img alt=\"(.*?)\"height=");
        m = p.matcher(result);
        while (m.find()) {
            actorsName.add(m.group(1));
        }
        Log.i("Total images URLs", String.valueOf(imagesURL.size()));
        Log.i("Total actors names", String.valueOf(actorsName.size()));
        for (int i = 0; i < imagesURL.size(); i++) {
            Log.i("ImageList", imagesURL.get(i));
        }
        for (int i = 0; i < actorsName.size(); i++) {
            Log.i("actorsList", actorsName.get(i));
        }

        actorImage = (ImageView) findViewById(R.id.imageView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        quiz();
    }
}