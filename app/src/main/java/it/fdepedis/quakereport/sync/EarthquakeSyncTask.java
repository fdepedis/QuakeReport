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
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

import it.fdepedis.quakereport.settings.EarthquakePreferences;
import it.fdepedis.quakereport.utils.NotificationUtils;
import it.fdepedis.quakereport.utils.QueryUtils;
import it.fdepedis.quakereport.utils.Utils;

public class EarthquakeSyncTask {

    private static final String LOG_TAG = EarthquakeSyncTask.class.getSimpleName();

    synchronized public static void checkQuakeReport(Context context) {

        try {
            //Log.e(LOG_TAG, "syncQuakeReport: in execution");

            boolean notificationsEnabled = EarthquakePreferences.isNotificationsEnabled(context);
            Log.e(LOG_TAG, "notificationsEnabled: " + notificationsEnabled);

            if (notificationsEnabled) {

                URL quakeReportRequestUrl = Utils.getNotificationURLByTime(context);

                String queryJSONResponse = QueryUtils.makeHttpRequest(quakeReportRequestUrl);

                JSONObject baseJsonResponse = new JSONObject(queryJSONResponse);
                JSONArray earthquakeArray = baseJsonResponse.getJSONArray("features");

                JSONObject currentEarthquake = earthquakeArray.getJSONObject(0);
                JSONObject properties = currentEarthquake.getJSONObject("properties");
                Log.e(LOG_TAG, "properties: " + properties);

                double currMagNotification = properties.getDouble("mag");
                Log.e(LOG_TAG, "currMagNotification: " + currMagNotification);

                String currPlace = properties.getString("place");
                Log.e(LOG_TAG, "currPlace: " + currPlace);

                long currTime = properties.getLong("time");
                Log.e(LOG_TAG, "currTime: " + currTime);

                String url = properties.getString("url");
                Log.e(LOG_TAG, "url: " + url);

                String minMagnitude = EarthquakePreferences.getMinMagnitudePreferences(context);

                // se viene restituito un valore di magnitudo maggiore o uguale
                // a quello settato nelle preferences invia una notifica
                // se abilitata dalle preferences
                if (currMagNotification >= Double.parseDouble(minMagnitude)) {
                    Log.e(LOG_TAG, "ATTENZIONE: fai partire notifica ==> currMagNotification: " + currMagNotification + " >= " + "minMagnitude: " + minMagnitude);

                    NotificationUtils.notifyUserOfNewQuakeReport(context, currMagNotification, currPlace, currTime, url);

                /*
                long timeSinceLastNotification = SunshinePreferences
                        .getEllapsedTimeSinceLastNotification(context);

                boolean oneDayPassedSinceLastNotification = false;

                if (timeSinceLastNotification >= DateUtils.DAY_IN_MILLIS) {
                    oneDayPassedSinceLastNotification = true;
                }
                */
                }
            }
        } catch (Exception e) {
            /* Server probably invalid */
            e.printStackTrace();
        }
    }
}