package mo.dnnewsapp;

/**
 * Created by Mo on 06/11/2016.
 * Custom view adapter to show news title and publish date
 */

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;


public class CustomFeedAdapter extends BaseAdapter{

    private List<RssFeedItem> RssFeeds = Collections.emptyList();
    private final Context context;

    public CustomFeedAdapter(Context context) {
        this.context = context;
    }

    public void updateRssFeed(List<RssFeedItem> rssFeed) {
       ThreadPreconditions.checkOnMainThread();
        this.RssFeeds = rssFeed;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return RssFeeds.size();
    }

    @Override
    public RssFeedItem getItem(int position) {
        return RssFeeds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.custom_rssfeed, parent, false);
        }
        TextView tvTitle = (TextView) convertView.findViewById(R.id.feedTitle);
        TextView tvDate = (TextView) convertView.findViewById(R.id.feedPublishDate);
        RssFeedItem rssFeedItem = getItem(position);
        tvTitle.setText(rssFeedItem.getTitle());
        tvDate.setText(rssFeedItem.getPublishDate());
        return convertView;

    }

//    @Override
//    public void onClick(View v) {
//        Log.i("CustomFeedAdapter", "onClick ");
//        int position = (Integer) v.getTag();
//        RssFeedItem object = (RssFeedItem) getItem(position);
//        Intent intent = new Intent(this.context, FeedDetailsActivity.class);
//        intent.putExtra(Constants.KEY_RSSFEEDITEM_OBJECT, object);
//        Log.i("CustomFeedAdapter", "onClick "+ object.getTitle());
//        v.getContext().startActivity(intent);
//    }
}
