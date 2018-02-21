package me.ahmed_ayman1708.newsappstage1.Utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by ahmed on 2/21/2018.
 */

public class NetworkUtils {

    private static final String API_KEY = "test";
    private static final String NEWS_LINK = "http://content.guardianapis.com/search";
    private final static String QUERY_PARAM_API_KEY = "api_key";
    private final static String QUERY_PARAM_TOPIC = "q";
    private final static String QUERY_PARAM_TOPIC_SELECTION = "egypt";
    private final static String QUERY_PARAM_TAG = "tag";
    private final static String QUERY_PARAM_SHOW_TAGS = "show-tags";
    private final static String QUERY_PARAM_SHOW_TAGS_SELECTION = "contributor";

    public static URL buildUrl(String field) {
        Uri builtUri = null;
        URL url = null;
        builtUri = Uri.parse(NEWS_LINK).buildUpon()
                .appendQueryParameter(QUERY_PARAM_TOPIC, QUERY_PARAM_TOPIC_SELECTION)
                .appendQueryParameter(QUERY_PARAM_TAG, field)
                .appendQueryParameter(QUERY_PARAM_SHOW_TAGS, QUERY_PARAM_SHOW_TAGS_SELECTION)
                .appendQueryParameter(QUERY_PARAM_API_KEY, API_KEY)
                .build();

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
