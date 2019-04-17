package com.example.jokeandroidlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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


//        joke = getIntent().getStringExtra("result");
//        textViewJoke = (TextView) findViewById(R.id.tv);
//        textViewJoke.setText(joke);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Library");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView tvJoke = (TextView) findViewById(R.id.tv);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRAS_JOKE)){
            String jokeToDisplay = intent.getStringExtra(EXTRAS_JOKE);
            tvJoke.setText(jokeToDisplay);
        }



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
