package it.fdepedis.quakereport.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import it.fdepedis.quakereport.R;
import it.fdepedis.quakereport.sync.QuakeReportSyncTask;

public class NotificationUtils {

    private static final String LOG_TAG = NotificationUtils.class.getSimpleName();

    /*
     * This notification ID can be used to access our notification after we've displayed it. This
     * can be handy when we need to cancel the notification, or perhaps update it. This number is
     * arbitrary and can be set to whatever you like. 3004 is in no way significant.
     */
    private static final int QUAKE_REPORT_NOTIFICATION_ID = 3004;

    public static void notifyUserOfNewQuakeReport(Context context, double magnitude, String place, long time, String url) {

        Resources resources = context.getResources();

        /*GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        int magnitudeColor = Utils.getMagnitudeColor(getContext(), currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);*/

        /*int largeArtResourceId = SunshineWeatherUtils
                .getLargeArtResourceIdForWeatherCondition(weatherId);

        Bitmap largeIcon = BitmapFactory.decodeResource(
                resources,
                largeArtResourceId);*/

        String notificationTitle = context.getString(R.string.app_name);

        String notificationText = place;

        /* getSmallArtResourceIdForWeatherCondition returns the proper art to show given an ID */
        /*int smallArtResourceId = SunshineWeatherUtils
                .getSmallArtResourceIdForWeatherCondition(weatherId);*/

        /*
         * NotificationCompat Builder is a very convenient way to build backward-compatible
         * notifications. In order to use it, we provide a context and specify a color for the
         * notification, a couple of different icons, the title for the notification, and
         * finally the text of the notification, which in our case in a summary of today's
         * forecast.
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            Log.e(LOG_TAG, "notifications???");

            CharSequence name = context.getString(R.string.app_name);
            String description = context.getString(R.string.app_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(String.valueOf(QUAKE_REPORT_NOTIFICATION_ID), name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            Uri earthquakeUri = Uri.parse(url);
            Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
            //context.startActivity(websiteIntent);

            // Create an explicit intent for an Activity in your app
            //Intent intent = new Intent(this, AlertDetails.class);
            websiteIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, websiteIntent, 0);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, String.valueOf(QUAKE_REPORT_NOTIFICATION_ID))
                    .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                    //.setSmallIcon(smallArtResourceId)
                    //.setLargeIcon(largeIcon)
                    .setContentTitle(notificationTitle)
                    .setContentText(notificationText)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            /*
             * This Intent will be triggered when the user clicks the notification. In our case,
             * we want to open Sunshine to the DetailActivity to display the newly updated weather.
             */
            /*Intent detailIntentForToday = new Intent(context, DetailActivity.class);
            detailIntentForToday.setData(todaysWeatherUri);*/


            /*
            TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
            taskStackBuilder.addNextIntentWithParentStack(detailIntentForToday);
            PendingIntent resultPendingIntent = taskStackBuilder
                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            notificationBuilder.setContentIntent(resultPendingIntent);
            */

            /*NotificationManager notificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);*/

            NotificationManagerCompat notification = NotificationManagerCompat.from(context);

            /* WEATHER_NOTIFICATION_ID allows you to update or cancel the notification later on */
            notification.notify(QUAKE_REPORT_NOTIFICATION_ID, notificationBuilder.build());

            /*
             * Since we just showed a notification, save the current time. That way, we can check
             * next time the weather is refreshed if we should show another notification.
             */
            //SunshinePreferences.saveLastNotificationTime(context, System.currentTimeMillis());
        }


        /* Always close your cursor when you're done with it to avoid wasting resources. */
        //todayWeatherCursor.close();
    }

    //private static String getNotificationText(Context context, int weatherId, double high, double low) {

        /*String shortDescription = SunshineWeatherUtils
                .getStringForWeatherCondition(context, weatherId);

        String notificationFormat = context.getString(R.string.format_notification);*/

        /* Using String's format method, we create the forecast summary */
        /*String notificationText = String.format(notificationFormat,
                shortDescription,
                SunshineWeatherUtils.formatTemperature(context, high),
                SunshineWeatherUtils.formatTemperature(context, low));

        return notificationText;*/
    //}
}
