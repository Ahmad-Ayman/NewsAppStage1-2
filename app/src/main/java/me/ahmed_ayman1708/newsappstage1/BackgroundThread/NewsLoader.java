package me.ahmed_ayman1708.newsappstage1.BackgroundThread;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import me.ahmed_ayman1708.newsappstage1.Models.NewsModel;
import me.ahmed_ayman1708.newsappstage1.Utils.JsonParser;
import me.ahmed_ayman1708.newsappstage1.Utils.NetworkUtils;

/**
 * Created by ahmed on 2/21/2018.
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsModel>> {
    /**
     * Tag for log messages
     */
    private static final String LOG_TAG = NewsLoader.class.getName();
    /**
     * Query URL
     */
    private String mUrl;

    public NewsLoader(Context context, String mUrl) {
        super(context);
        this.mUrl = mUrl;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsModel> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of earthquakes.
        try {
            URL requestURL = new URL(mUrl);
            String newsAllJson = NetworkUtils.getResponseFromHttpUrl(requestURL);
            List<NewsModel> news = JsonParser.getNewsStringsFromJson(mUrl);
            return news;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
