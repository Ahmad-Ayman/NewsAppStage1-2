package me.ahmed_ayman1708.newsappstage1.BackgroundThread;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

import me.ahmed_ayman1708.newsappstage1.Models.NewsModel;
import me.ahmed_ayman1708.newsappstage1.R;
import me.ahmed_ayman1708.newsappstage1.Utils.NetworkUtils;

/**
 * Created by ahmed on 2/21/2018.
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsModel>> {

    private static final String LOG_TAG = NewsLoader.class.getName();

    private String mUrl;

    public NewsLoader(Context context, String mUrl) {
        super(context);
        this.mUrl = mUrl;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<NewsModel> loadInBackground() {
        if (mUrl == null) {
            Log.i(LOG_TAG, getContext().getResources().getString(R.string.logmsgLoader));
            return null;
        }


        List<NewsModel> newsAllJson = null;
        newsAllJson = NetworkUtils.fetchNewsData(mUrl);
        return newsAllJson;
    }

}
