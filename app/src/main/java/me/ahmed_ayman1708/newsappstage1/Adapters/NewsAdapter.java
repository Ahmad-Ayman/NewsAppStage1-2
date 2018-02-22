package me.ahmed_ayman1708.newsappstage1.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import me.ahmed_ayman1708.newsappstage1.Activities.AuthorDetailsActivity;
import me.ahmed_ayman1708.newsappstage1.Interfaces.ItemClickListener;
import me.ahmed_ayman1708.newsappstage1.Models.NewsModel;
import me.ahmed_ayman1708.newsappstage1.R;

/**
 * Created by ahmed on 2/21/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsAdapterViewHolder> {
    private Context mContext;
    String sectionName;
    String webPublicationDate;
    String webTitle;
    String webUrl;
    String authorWebTitle;
    String authorWebUrl;
    String authorBylineImageUrl;
    ArrayList<NewsModel> newsList;

    public NewsAdapter(Context context,ArrayList<NewsModel> nList){
        mContext=context;
        newsList=nList;
    }

    @Override
    public NewsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, null);
        NewsAdapterViewHolder rcv = new NewsAdapterViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(NewsAdapterViewHolder holder, final int position) {
        sectionName=newsList.get(position).getmSectionName();
        webPublicationDate=newsList.get(position).getmWebPublicationDate();
        webTitle=newsList.get(position).getmWebTitle();
        webUrl=newsList.get(position).getmWebUrl();
        holder.articleName.setText(webTitle);
        holder.articleSection.setText(sectionName);
        holder.articlePublishDate.setText(webPublicationDate);
        holder.authorBtn.setText("Authors");
        holder.authorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authorWebTitle=newsList.get(position).getmAuthorWebTitle();
                authorWebUrl=newsList.get(position).getmAuthorWebUrl();
                authorBylineImageUrl=newsList.get(position).getmAuthorBylineImageUrl();
                Intent i = new Intent(mContext,AuthorDetailsActivity.class);
                i.putExtra("AUTHOR_TITLE_KEY",authorWebTitle);
                i.putExtra("AUTHOR_LINK_KEY",authorWebUrl);
                i.putExtra("AUTHOR_IMAGE_KEY",authorBylineImageUrl);
                mContext.startActivity(i);
            }
        });
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                webUrl=newsList.get(pos).getmWebUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
                Intent chooser = Intent.createChooser(intent, "Open With");
                if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                    mContext.startActivity(chooser);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //////////////////////////////////////// View Holder Class //////////////////////////
    public class NewsAdapterViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        public TextView articleName;
        public TextView articleSection;
        public TextView articlePublishDate;
        public Button authorBtn;
        private ItemClickListener itemClickListener;
        public NewsAdapterViewHolder(View view) {
            super(view);
            articleName = view.findViewById(R.id.tv_articleName);
            articleSection = view.findViewById(R.id.tv_section);
            articlePublishDate=view.findViewById(R.id.tv_publishDate);
            authorBtn=view.findViewById(R.id.authorButton);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }
        public void setItemClickListener(ItemClickListener ic){
            this.itemClickListener=ic;
        }
    }

    //////////////////////////////////////////////////


}
