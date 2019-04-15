package androidTest;

import android.util.Log;

import org.junit.runner.RunWith;

import builditbigger.EndpointsAsyncTask;


import static java.security.AccessController.getContext;
import static junit.framework.TestCase.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class NonString {

    private static final String LOG_TAG = NonString.class.getSimpleName();

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void test() {
        String result = null;
       EndpointsAsyncTask task=new EndpointsAsyncTask(getContext());
        task.execute();
        try {
            result = task.get();
            Log.d(LOG_TAG, "Retrieved a non-empty string successfully: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(result);
    }

}
