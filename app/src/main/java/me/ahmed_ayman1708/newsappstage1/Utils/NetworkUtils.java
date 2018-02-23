package me.ahmed_ayman1708.newsappstage1.Utils;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import me.ahmed_ayman1708.newsappstage1.Models.NewsModel;

/**
 * Created by ahmed on 2/21/2018.
 */

public class NetworkUtils {
    public static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    final static String ERROR_PARSING = "Error parsing JSON response";
    final static String PROBLEM = "Problem building the URL ";
    final static String PROBLEM_HTTP = "Problem making the HTTP request.";
    final static String ERRO_CODE = "Error response code: ";

    private NetworkUtils() {
    }

    public static List<NewsModel> extractFeatureFromJson(String NewsJsonStr) {
        if (TextUtils.isEmpty(NewsJsonStr)) {
            return null;
        }
        List<NewsModel> NewsListingArray = new ArrayList<>();
        final String rootJsonObj = "response";
        final String results = "results";
        final String sectionName = "sectionName";
        final String webPublicationDate = "webPublicationDate";
        final String title = "webTitle";
        final String topicUrl = "webUrl";
        final String tag = "tags";
        try {
            // root Json Obj
            JSONObject newsJson = new JSONObject(NewsJsonStr);
            // response json object
            JSONObject response = newsJson.getJSONObject(rootJsonObj);
            // results json object
            JSONArray resultArray = response.getJSONArray(results);
            // loop through the array elements
            for (int i = 0; i < resultArray.length(); i++) {
                // Data to be collected
                String sectionNameFromArray;
                String webPublicationDateFromArray;
                String titleFromArray;
                String topicUrlFromArray;

                String authorFullName;
                // Get the JSON object representing the results at each index
                JSONObject newsList = resultArray.getJSONObject(i);
                sectionNameFromArray = newsList.getString(sectionName);
                webPublicationDateFromArray = newsList.getString(webPublicationDate);
                webPublicationDateFromArray = formatDate(webPublicationDateFromArray);
                titleFromArray = newsList.getString(title);
                topicUrlFromArray = newsList.getString(topicUrl);
                JSONArray tagresultArray = newsList.getJSONArray(tag);
                String author = "";
                if (tagresultArray.length() == 0) {
                    author = "Not Applicable";
                } else {
                    // loop through the array elements of TAGS Json Object
                    for (int j = 0; j < tagresultArray.length(); j++) {
                        // Data to be collected
                        JSONObject tagsList = tagresultArray.getJSONObject(j);
                        author += tagsList.getString(title) + ". ";
                    }
                }
                NewsListingArray.add(new NewsModel(sectionNameFromArray,
                        webPublicationDateFromArray,
                        titleFromArray, topicUrlFromArray, author));
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, ERROR_PARSING, e);
        }
        return NewsListingArray;


    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, PROBLEM, e);
        }
        return url;
    }


    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, ERRO_CODE + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, PROBLEM, e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {

                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static List<NewsModel> fetchNewsData(String requestUrl) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Create URL object
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, PROBLEM_HTTP, e);
        }

        List<NewsModel> newsModel = extractFeatureFromJson(jsonResponse);
        return newsModel;
    }

    private static String formatDate(String rawDate) {
        String jsonDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat jsonFormatter = new SimpleDateFormat(jsonDatePattern, Locale.US);
        try {
            Date parsedJsonDate = jsonFormatter.parse(rawDate);
            String finalDatePattern = "MMM d, yyy";
            SimpleDateFormat finalDateFormatter = new SimpleDateFormat(finalDatePattern, Locale.US);
            return finalDateFormatter.format(parsedJsonDate);
        } catch (ParseException e) {
            Log.e(LOG_TAG, ERROR_PARSING, e);
            return "";
        }
    }

}
