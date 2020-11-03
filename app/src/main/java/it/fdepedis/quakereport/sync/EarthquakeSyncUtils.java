/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package it.fdepedis.quakereport.sync;

import android.content.Context;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class EarthquakeSyncUtils {

    private static final String LOG_TAG = EarthquakeSyncUtils.class.getSimpleName();

    private static final int SYNC_INTERVAL_MINUTES = new Time(System.currentTimeMillis()).getMinutes();
    private static final int SYNC_INTERVAL_SECONDS = (int) TimeUnit.HOURS.toSeconds(SYNC_INTERVAL_MINUTES);
    private static final int SYNC_FLEXTIME_SECONDS = SYNC_INTERVAL_SECONDS + 5;

    private static boolean sInitialized;

    private static final String QUAKE_REPORT_SYNC_TAG = "quakereport-sync";

    static void scheduleFirebaseJobDispatcherSync(final Context context) {

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        //Log.e(LOG_TAG, "SYNC_INTERVAL_MINUTES: " + SYNC_INTERVAL_MINUTES );
        //Log.e(LOG_TAG, "SYNC_INTERVAL_SECONDS: " + SYNC_INTERVAL_SECONDS );
        //Log.e(LOG_TAG, "SYNC_FLEXTIME_SECONDS: " + SYNC_FLEXTIME_SECONDS );

        Job syncQuakeReportJob = dispatcher.newJobBuilder()
                .setService(EarthquakeFirebaseJobService.class)
                .setTag(QUAKE_REPORT_SYNC_TAG)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(
                        SYNC_INTERVAL_MINUTES,
                        SYNC_INTERVAL_MINUTES + 1))
                .setReplaceCurrent(true)
                .build();

        dispatcher.schedule(syncQuakeReportJob);

        //Log.e(LOG_TAG, "Scheduled");
    }

    synchronized public static void initialize(final Context context) {

        if (sInitialized) return;

        sInitialized = true;

        scheduleFirebaseJobDispatcherSync(context);

        /*Thread checkForEmpty = new Thread(new Runnable() {
            @Override
            public void run() {

                Uri forecastQueryUri = WeatherContract.WeatherEntry.CONTENT_URI;

                String[] projectionColumns = {WeatherContract.WeatherEntry._ID};
                String selectionStatement = WeatherContract.WeatherEntry
                        .getSqlSelectForTodayOnwards();

                Cursor cursor = context.getContentResolver().query(
                        forecastQueryUri,
                        projectionColumns,
                        selectionStatement,
                        null,
                        null);

                if (null == cursor || cursor.getCount() == 0) {
                    startImmediateSync(context);
                }

                cursor.close();
            }
        });

        checkForEmpty.start();*/
    }

   /* public static void startImmediateSync(final Context context) {
        Intent intentToSyncImmediately = new Intent(context, SunshineSyncIntentService.class);
        context.startService(intentToSyncImmediately);
    }*/
}