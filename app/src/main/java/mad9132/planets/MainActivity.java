package mad9132.planets;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Manage requests with class AsyncTask.
 *
 * @author Gerald.Hurdle@AlgonquinCollege.com
 * @author David Gassner (original)
 *
 * Reference: Chapter 2. Manage Multithreading in Android
 *            "Android App Development: RESTful Web Services" with David Gassner
 */
public class MainActivity extends Activity {

    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView) findViewById(R.id.output);
    }

    public void runClickHandler(View view) {
//        output.append("Button clicked\n");
        MyAsyncTask task = new MyAsyncTask();
        task.execute("String 1", "String 2", "String 3");
    }

    public void clearClickHandler(View view) {
        output.setText("");
    }

    private class MyAsyncTask extends AsyncTask<String, String, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... strings) {
            for (String string: strings) {
                publishProgress(string);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            output.append(values[0] + "\n");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

    }   // end of class MyAsyncTask
}   // end of class MainActivity
