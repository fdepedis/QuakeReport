package it.fdepedis.quakereport.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import it.fdepedis.quakereport.R;

public class EarthquakePreferences {

    private static final String LOG_TAG = EarthquakePreferences.class.getSimpleName();

    public static boolean isNotificationsEnabled(Context context) {

        String displayNotificationsKey = context.getString(R.string.pref_enable_notifications_key);

        boolean shouldDisplayNotificationsByDefault = context
                .getResources()
                .getBoolean(R.bool.show_notifications_by_default);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        boolean shouldDisplayNotifications = sp
                .getBoolean(displayNotificationsKey, shouldDisplayNotificationsByDefault);

        return shouldDisplayNotifications;
    }

    public static String getMinMagnitudePreferences(Context context) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        String minMagnitude = sharedPrefs.getString(
                context.getString(R.string.settings_min_magnitude_key),
                context.getString(R.string.settings_min_magnitude_default));

        return minMagnitude;
    }

}
