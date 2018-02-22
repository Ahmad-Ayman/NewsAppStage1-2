package me.ahmed_ayman1708.newsappstage1.Utils;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.ahmed_ayman1708.newsappstage1.Models.NewsModel;

/**
 * Created by ahmed on 2/21/2018.
 */

public class JsonParser {

    public static ArrayList<NewsModel> getNewsStringsFromJson( String NewsJsonStr)
            throws JSONException {
        ArrayList<NewsModel> NewsListingArray = new ArrayList<>();
        final String rootJsonObj="response";
        final String results="results";
        final String sectionName="sectionName";
        final String webPublicationDate="webPublicationDate";
        final String title="webTitle";
        final String topicUrl="webUrl";
        final String tag="tags";
        final String tagAuthorName="webTitle";
        final String tagAuthorLink="webUrl";
        final String tagAuthorPic="bylineImageUrl";

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
            String authorName = null;
            String authorURL=null;
            String authorImage=null;
            // Get the JSON object representing the results at each index
            JSONObject newsList = resultArray.getJSONObject(i);
            sectionNameFromArray = newsList.getString(sectionName);
            webPublicationDateFromArray=newsList.getString(webPublicationDate);
            titleFromArray=newsList.getString(title);
            topicUrlFromArray=newsList.getString(topicUrl);
            JSONArray tagresultArray = newsList.getJSONArray(tag);
            // loop through the array elements of TAGS Json Object
            for(int j =0;j<tagresultArray.length();j++){
                // Data to be collected

                JSONObject tagsList = resultArray.getJSONObject(j);
                authorName=tagsList.getString(tagAuthorName);
                authorURL=tagsList.getString(tagAuthorLink);
                authorImage=tagsList.getString(tagAuthorPic);
            }

            NewsListingArray.add(new NewsModel(sectionNameFromArray,
                    webPublicationDateFromArray,
                    titleFromArray,topicUrlFromArray,authorName,authorURL,authorImage));

        }
        return NewsListingArray;


    }

}
