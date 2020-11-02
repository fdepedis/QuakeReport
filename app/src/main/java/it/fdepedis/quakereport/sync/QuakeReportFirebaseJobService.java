package it.fdepedis.quakereport.sync;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;


public class QuakeReportFirebaseJobService extends JobService {

    private static final String LOG_TAG = QuakeReportFirebaseJobService.class.getSimpleName();

    private AsyncTask<Void, Void, Void> mFetchQuakeReportTask;

    @SuppressLint("StaticFieldLeak")
    @Override
    public boolean onStartJob(final JobParameters jobParameters) {

        mFetchQuakeReportTask = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                Log.e(LOG_TAG, "doInBackground: FirebaseJobService in execution");
                Context context = getApplicationContext();
                QuakeReportSyncTask.checkQuakeReport(context);

                jobFinished(jobParameters, false);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                jobFinished(jobParameters, false);
                Log.e(LOG_TAG, "onPostExecute: FirebaseJobService in execution");
            }
        };

        mFetchQuakeReportTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        if (mFetchQuakeReportTask != null) {
            Log.e(LOG_TAG, "onStopJob: FirebaseJobService finish");
            //Toast.makeText(context, "syncQuakeReport in execution", Toast.LENGTH_LONG).show();
            mFetchQuakeReportTask.cancel(true);
        }
        return true;
    }
}
