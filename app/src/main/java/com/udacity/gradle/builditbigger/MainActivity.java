package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jokeandroidlibrary.JokeDisplayActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));

    }


 //   -javaagent:"C:\\Users\\mohamed\\AppData\\Local\\Google\\Cloud SDK\\google-cloud-sdk\\platform\\google_appengine\\google\\appengine\\tools\\java\\lib\\agent\\appengine-agent.jar" -Xmx512m -Dappengine.fullscan.seconds=5 -Ddatastore.default_high_rep_job_policy_unapplied_job_pct=50


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {

        new EndpointsAsyncTask(new EndpointsAsyncTask.TaskCompleteListener() {
            @Override
            public void onTaskComplete(String result) {
                Intent intent = new Intent(MainActivity.this, JokeDisplayActivity.class);
                intent.putExtra(JokeDisplayActivity.EXTRAS_JOKE, result);
                startActivity(intent);
            }
        }).execute(this);

  }


}