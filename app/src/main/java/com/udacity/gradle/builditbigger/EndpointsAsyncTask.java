package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;


import java.io.IOException;

import com.udacity.gradle.*;




public  class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context context;

    public EndpointsAsyncTask() {

    }

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0];

        try {
            return myApiService.getRandomJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    private TaskCompleteListener mTaskCompleteListener;

    public interface TaskCompleteListener {
        void onTaskComplete(String result);
    }

    public EndpointsAsyncTask(TaskCompleteListener listener) {
        mTaskCompleteListener = listener;
    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null) {
            mTaskCompleteListener.onTaskComplete(result);
            Log.v("EndpointsAsyncTask","rrrrrrrrrrrrr"+result);
        }
    }
}