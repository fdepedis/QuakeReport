package it.fdepedis.quakereport.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import it.fdepedis.quakereport.R;

public class QuakeReportPreferences {

    private static final String LOG_TAG = QuakeReportPreferences.class.getSimpleName();

    public static boolean areNotificationsEnabled(Context context) {

        String displayNotificationsKey = context.getString(R.string.pref_enable_notifications_key);

        boolean shouldDisplayNotificationsByDefault = context
                .getResources()
                .getBoolean(R.bool.show_notifications_by_default);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        boolean shouldDisplayNotifications = sp
                .getBoolean(displayNotificationsKey, shouldDisplayNotificationsByDefault);

        return shouldDisplayNotifications;
    }
}
