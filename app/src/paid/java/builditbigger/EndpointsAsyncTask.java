package builditbigger;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.jokeandroidlibrary.JokeDisplayActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.security.AccessControlContext;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    InterstitialAd interstitialAd;


    public  EndpointsAsyncTask(Context context){
        this.context=context;
    }

    public EndpointsAsyncTask(AccessControlContext context) {
    }


    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    //http://10.0.2.2:8080
                    //https://myApplicationId.appspot.com
                    //
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


        try {
            return myApiService.sayHi().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    protected void onPostExecute(final String result) {
        interstitialAd=new InterstitialAd(context);
        interstitialAd.setAdUnitId("");
        interstitialAd.setAdListener(new AdListener()
        {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                interstitialAd.show();
                Intent intent=new Intent(context, JokeDisplayActivity.class);
                Log.v("EndpointsAsyncTask","ddddddddddd"+result);
                intent.putExtra("result",result);
                context.startActivity(intent);

            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Intent intent=new Intent(context, JokeDisplayActivity.class);
                intent.putExtra("result",result);
                context.startActivity(intent);
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Intent intent=new Intent(context, JokeDisplayActivity.class);
                intent.putExtra("result",result);
                context.startActivity(intent);

            }
        });
        AdRequest adRequest=new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        interstitialAd.loadAd(adRequest);

       // Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}