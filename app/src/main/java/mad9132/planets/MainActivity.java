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

        /**
         * Invoked on the UI thread before the task is executed.
         * This step is normally used to setup the task.
         *
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * Invoked on the background thread immediately after onPreExecute() finishes executing.
         * This step is used to peform background computation that can take a long time.
         * The parameters of the asynchronous task are passed to this step.
         * The result of the computation must be returned by this step and will be passed back to
         * the last step.
         * This step can also use publishProgress(Progress) to publish one or more units of progress.
         * These values are published on the UI thread, in the onProgressUpdate(Progress) step.
         * @param strings
         * @return Void
         */
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

        /**
         * Invoked on the UI thread after a call to publishProgress(Progress).
         * The timing of the execution is undefined. This method is used to display any form of
         * progress in the UI while the background computation is still executing.
         * @param values
         */
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            output.append(values[0] + "\n");
        }

        /**
         * Invoked on the UI thread after the background computation finishes.
         * The result of the background computation is passed to this step as a parameter.
         * @param aVoid
         */
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

    }   // end of class MyAsyncTask
}   // end of class MainActivity
