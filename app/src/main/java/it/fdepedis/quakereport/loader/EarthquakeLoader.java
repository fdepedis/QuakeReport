package it.fdepedis.quakereport.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

import it.fdepedis.quakereport.utils.QueryUtils;
import it.fdepedis.quakereport.model.Earthquake;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private static final String LOG_TAG = EarthquakeLoader.class.getName();
    private String mUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {

        Log.d(LOG_TAG, "Log - in onStartLoading() method");

        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {

        Log.d(LOG_TAG, "Log - in loadInBackground() method");

        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }
}
