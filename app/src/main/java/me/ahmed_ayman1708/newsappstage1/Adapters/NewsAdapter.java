package me.ahmed_ayman1708.newsappstage1.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.ahmed_ayman1708.newsappstage1.Models.NewsModel;
import me.ahmed_ayman1708.newsappstage1.R;

/**
 * Created by ahmed on 2/21/2018.
 */

public class NewsAdapter extends ArrayAdapter<NewsModel> {


    public NewsAdapter(Context context, ArrayList<NewsModel> nList) {
        super(context, 0, nList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        TextView articleName = convertView.findViewById(R.id.tv_articleName);
        TextView articleSection = convertView.findViewById(R.id.tv_section);
        TextView articlePublishDate = convertView.findViewById(R.id.tv_publishDate);
        TextView authorList = convertView.findViewById(R.id.authortv);
        NewsModel news = getItem(position);
        articleName.setText(news.getmWebTitle());
        articleSection.setText(news.getmSectionName());
        articlePublishDate.setText(news.getmWebPublicationDate());
        authorList.setText(news.getmAuthor());


        return convertView;

    }

}
