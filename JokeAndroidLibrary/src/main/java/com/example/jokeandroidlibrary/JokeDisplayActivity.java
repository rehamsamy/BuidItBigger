package com.example.jokeandroidlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {
   // public final static String INTENT_JOKE = "INTENT_JOKE";
    String joke;
    TextView textViewJoke;
    public static final String EXTRAS_JOKE = "joke";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);


        joke = getIntent().getStringExtra("result");
        textViewJoke = (TextView) findViewById(R.id.tv);
        textViewJoke.setText(joke);

    }
}
