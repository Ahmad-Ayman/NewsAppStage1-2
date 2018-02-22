package me.ahmed_ayman1708.newsappstage1.Fragments;


import android.app.LoaderManager;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import me.ahmed_ayman1708.newsappstage1.BackgroundThread.NewsLoader;
import me.ahmed_ayman1708.newsappstage1.Models.NewsModel;
import me.ahmed_ayman1708.newsappstage1.R;
import me.ahmed_ayman1708.newsappstage1.Utils.NetworkUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class SportFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<NewsModel>> {


    public SportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sport, container, false);
    }

    @Override
    public Loader<List<NewsModel>> onCreateLoader(int i, Bundle bundle) {
        URL sportsUri = NetworkUtils.buildUrl("sport/sport");
        return new NewsLoader(getContext(),sportsUri.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<NewsModel>> loader, List<NewsModel> newsModels) {

    }

    @Override
    public void onLoaderReset(Loader<List<NewsModel>> loader) {

    }
}
