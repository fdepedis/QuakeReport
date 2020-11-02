package it.fdepedis.quakereport.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

import it.fdepedis.quakereport.R;
import it.fdepedis.quakereport.activity.EarthquakeActivity;

import static java.lang.String.valueOf;

public class Utils {

    private static final String LOG_TAG = Utils.class.getName();

    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query";
    //"http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    public static String refreshData(Context context){
        //new EarthquakeLoader(context, uriBuilder.toString());

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        //String minMagnitude = sharedPrefs.getString(valueOf(R.string.settings_min_magnitude_key), valueOf(R.string.settings_min_magnitude_default));

        String minMagnitude = sharedPrefs.getString(
                context.getString(R.string.settings_min_magnitude_key),
                context.getString(R.string.settings_min_magnitude_default));

        String orderBy = sharedPrefs.getString(
                context.getString(R.string.settings_order_by_key),
                context.getString(R.string.settings_order_by_default));

        String numItems = sharedPrefs.getString(
                context.getString(R.string.settings_num_item_key),
                context.getString(R.string.settings_num_item_default));

        Log.d(LOG_TAG, "minMagnitude: " + minMagnitude );
        Log.d(LOG_TAG, "orderBy: " + orderBy );
        Log.d(LOG_TAG, "numItems: " + numItems );

        Uri baseUri = Uri.parse(USGS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("format", "geojson");
        uriBuilder.appendQueryParameter("limit", numItems);
        uriBuilder.appendQueryParameter("minmag", minMagnitude);
        uriBuilder.appendQueryParameter("orderby", orderBy);

        //Log.e(LOG_TAG, "uriBuilder: " + uriBuilder.toString() );

        return uriBuilder.toString();
    }

    public static URL getURLByTime(Context context) {
        Uri quakeReportQueryUriByTime = Uri.parse(USGS_REQUEST_URL);

        Uri.Builder uriBuilder = quakeReportQueryUriByTime.buildUpon();
        uriBuilder.appendQueryParameter("format", "geojson");
        uriBuilder.appendQueryParameter("limit", "1");
        uriBuilder.appendQueryParameter("minmag", "5");
        uriBuilder.appendQueryParameter("orderby", "time");

        try{
            URL quakeReportQueryUrlByTime = new URL(uriBuilder.toString());
            Log.e(LOG_TAG, "quakeReportQueryUrlByTime: " + quakeReportQueryUrlByTime );
            return quakeReportQueryUrlByTime;
        } catch (MalformedURLException e){
            e.printStackTrace();
            return null;
        }


    }
    
}
