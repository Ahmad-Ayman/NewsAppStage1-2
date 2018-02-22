package me.ahmed_ayman1708.newsappstage1.Models;

/**
 * Created by ahmed on 2/21/2018.
 */

public class NewsModel {
    private String mSectionName;
    private String mWebPublicationDate;
    private String mWebTitle;
    private String mWebUrl;
    private String mAuthor;

    public NewsModel(
            String sectionName,
            String webPublicationDate,
            String webTitle,
            String webUrl,
            String author) {

        mSectionName = sectionName;
        mWebPublicationDate = webPublicationDate;
        mWebTitle = webTitle;
        mWebUrl = webUrl;
        mAuthor = author;

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

    public String getmAuthor() {
        return mAuthor;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + mWebTitle + '\'' +
                ", author='" + mAuthor + '\'' +
                ", url='" + mWebUrl + '\'' +
                ", date='" + mWebPublicationDate + '\'' +
                ", section='" + mSectionName + '\'' +
                '}';
    }
}
