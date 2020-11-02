package it.fdepedis.quakereport.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;

public class NotificationUtils {

    /*
     * This notification ID can be used to access our notification after we've displayed it. This
     * can be handy when we need to cancel the notification, or perhaps update it. This number is
     * arbitrary and can be set to whatever you like. 3004 is in no way significant.
     */
    private static final int QUAKE_REPORT_NOTIFICATION_ID = 3004;

    public static void notifyUserOfNewWeather(Context context) {


       /* Resources resources = context.getResources();
        int largeArtResourceId = SunshineWeatherUtils
                .getLargeArtResourceIdForWeatherCondition(weatherId);

        Bitmap largeIcon = BitmapFactory.decodeResource(
                resources,
                largeArtResourceId);

        String notificationTitle = context.getString(R.string.app_name);

        String notificationText = getNotificationText(context, weatherId, high, low);*/

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
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            Notification.Builder notificationBuilder = new Notification.Builder(context)
                    .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                    .setSmallIcon(smallArtResourceId)
                    .setLargeIcon(largeIcon)
                    .setContentTitle(notificationTitle)
                    .setContentText(notificationText)
                    .setAutoCancel(true);
*/
            /*
             * This Intent will be triggered when the user clicks the notification. In our case,
             * we want to open Sunshine to the DetailActivity to display the newly updated weather.
             */
            /*Intent detailIntentForToday = new Intent(context, DetailActivity.class);
            detailIntentForToday.setData(todaysWeatherUri);

            TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
            taskStackBuilder.addNextIntentWithParentStack(detailIntentForToday);
            PendingIntent resultPendingIntent = taskStackBuilder
                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            notificationBuilder.setContentIntent(resultPendingIntent);

            NotificationManager notificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);*/

            /* WEATHER_NOTIFICATION_ID allows you to update or cancel the notification later on */
            //notificationManager.notify(WEATHER_NOTIFICATION_ID, notificationBuilder.build());

            /*
             * Since we just showed a notification, save the current time. That way, we can check
             * next time the weather is refreshed if we should show another notification.
             */
            //SunshinePreferences.saveLastNotificationTime(context, System.currentTimeMillis());
       // }


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
