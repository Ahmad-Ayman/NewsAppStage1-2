package me.ahmed_ayman1708.newsappstage1.Fragments;


import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import me.ahmed_ayman1708.newsappstage1.Adapters.NewsAdapter;
import me.ahmed_ayman1708.newsappstage1.BackgroundThread.NewsLoader;
import me.ahmed_ayman1708.newsappstage1.Models.NewsModel;
import me.ahmed_ayman1708.newsappstage1.R;
import me.ahmed_ayman1708.newsappstage1.Utils.NetworkUtils;

public class SportFragment extends Fragment implements LoaderCallbacks<List<NewsModel>> {
    
    private static final int SPORTS_LOADER_ID = 1;

    private TextView mErrorMsgDisplay;
    ConnectivityManager cm;

    NetworkInfo activeNetwork;
    boolean isConnected;
    private static final String API_KEY = "test";
    private static final String NEWS_LINK = "http://content.guardianapis.com/search";
    private final static String QUERY_PARAM_API_KEY = "api-key";
    private final static String QUERY_PARAM_TOPIC = "q";
    private final static String QUERY_PARAM_TAG = "tag";
    private final static String QUERY_PARAM_SHOW_TAGS = "show-tags";
    private final static String QUERY_PARAM_SHOW_TAGS_SELECTION = "contributor";

    private NewsAdapter mAdapter;
    LoaderManager loaderManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_sport, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String country = sharedPreferences.getString(getString(R.string.prefKey),getString(R.string.prefDefault));
        getActivity().setTitle(getResources().getString(R.string.titleforsportfrag)+country);

        mErrorMsgDisplay = view.findViewById(R.id.emptyElement);
        ListView newsListView = view.findViewById(R.id.listitem);
        mAdapter = new NewsAdapter(getContext(), new ArrayList<NewsModel>());

        newsListView.setAdapter(mAdapter);
        newsListView.setEmptyView(mErrorMsgDisplay);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                NewsModel currentNew = mAdapter.getItem(pos);
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(currentNew.getmWebUrl()));

                startActivity(i);
            }
        });

        cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            loaderManager = getActivity().getLoaderManager();
            loaderManager.initLoader(SPORTS_LOADER_ID, null, this);
        } else {
            mAdapter.clear();
            mErrorMsgDisplay.setText(getResources().getString(R.string.noInternet));

        }

    }


    @Override
    public Loader<List<NewsModel>> onCreateLoader(int i, Bundle bundle) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String country = sharedPreferences.getString(getString(R.string.prefKey),getString(R.string.prefDefault));

        Uri baseUri = Uri.parse(NEWS_LINK).buildUpon()
                .appendQueryParameter(QUERY_PARAM_TOPIC, country)
                .appendQueryParameter(QUERY_PARAM_TAG, getResources().getString(R.string.SportsQuery))
                .appendQueryParameter(QUERY_PARAM_SHOW_TAGS, QUERY_PARAM_SHOW_TAGS_SELECTION)
                .appendQueryParameter(QUERY_PARAM_API_KEY, API_KEY)
                .build();


        return new NewsLoader(getContext(), baseUri.toString());

    }

    @Override
    public void onLoadFinished(Loader<List<NewsModel>> loader, List<NewsModel> newsModels) {
        mAdapter.clear();
        if (newsModels != null && !newsModels.isEmpty()) {
            mAdapter.addAll(newsModels);
        }
        else{
            mErrorMsgDisplay.setText(getResources().getString(R.string.noData));
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsModel>> loader) {
        mAdapter.clear();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getLoaderManager().restartLoader(SPORTS_LOADER_ID, null, this);
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        getActivity().getLoaderManager().restartLoader(SPORTS_LOADER_ID, null, this);
    }
}
