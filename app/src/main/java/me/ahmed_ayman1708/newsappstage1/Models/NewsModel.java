package me.ahmed_ayman1708.newsappstage1.Models;

/**
 * Created by ahmed on 2/21/2018.
 */

public class NewsModel {
    private String mSectionName;
    private String mWebPublicationDate;
    private String mWebTitle;
    private String mWebUrl;
    private String mAuthorWebTitle;
    private String mAuthorWebUrl;
    private String mAuthorBylineImageUrl;
    public NewsModel(
            String sectionName,
            String webPublicationDate,
            String webTitle,
            String webUrl,
            String authorWebTitle,
            String authorWebUrl,
            String authorBylineImageUrl){

            mSectionName=sectionName;
            mWebPublicationDate=webPublicationDate;
            mWebTitle=webTitle;
            mWebUrl=webUrl;
            mAuthorWebTitle=authorWebTitle;
            mAuthorWebUrl=authorWebUrl;
            mAuthorBylineImageUrl=authorBylineImageUrl;
            }

    public String getmSectionName() {
        return mSectionName;
    }

    public String getmWebPublicationDate() {
        return mWebPublicationDate;
    }

    public String getmWebTitle() {
        return mWebTitle;
    }

    public String getmWebUrl() {
        return mWebUrl;
    }

    public String getmAuthorWebTitle() {
        return mAuthorWebTitle;
    }

    public String getmAuthorWebUrl() {
        return mAuthorWebUrl;
    }

    public String getmAuthorBylineImageUrl() {
        return mAuthorBylineImageUrl;
    }
}
