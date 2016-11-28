package mo.dnnewsapp;

/**
 * Created by Mo on 06/11/2016.
 * This class define every DN news Rss tags
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.InputStream;
import java.net.URL;
import java.util.List;


public class NewsMainActivity extends AppCompatActivity{
    private List<RssFeedItem> mNewsArrayList;
    private ListView lvDNFeeds;
    private static CustomFeedAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_main);
        lvDNFeeds = (ListView) findViewById(R.id.lvDNFeeds);


        lvDNFeeds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Log.i("item details ", " value: " +position);
                RssFeedItem object = (RssFeedItem) mNewsArrayList.get(position);
                Intent intent = new Intent(view.getContext(), FeedDetailsActivity.class);
                intent.putExtra(Constants.KEY_RSSFEEDITEM_OBJECT, object);
                Log.i("startActivity", "before  ******");
                view.getContext().startActivity(intent);
                Log.i("startActivity", "After ******");
//                Intent detailsIntent = new Intent(view.getContext(), FeedDetailsActivity.class);
//                detailsIntent.putExtra(Constants.KEY_ARTICLE_DETAILS, articlesArrayList.get(position).getArticleDetails());
//                detailsIntent.putExtra(Constants.KEY_ARTICLE_DATE, articlesArrayList.get(position).getaDate());
//                detailsIntent.putExtra(Constants.KEY_ARTICLE_LINK, articlesArrayList.get(position).getArticleUrlLink());
//                detailsIntent.putExtra(Constants.KEY_ARTICLE_TITLE, articlesArrayList.get(position).getArticleTitle());
//                detailsIntent.putExtra(Constants.KEY_ARTICLE_ID, articlesArrayList.get(position).getGuid());
//                startActivity(detailsIntent);
            }
        });
        if (isNetworkAvailable()) {
        // use AsyncTask service to fetch all available Rss feeds from DN Daily Swedish news paper
        // on the background
        new AsyncTask<Void, Void, Void>() {
            ProgressDialog pd;
            @Override
            protected void onPreExecute() {
                pd = ProgressDialog.show(NewsMainActivity.this, "DN Rss Feeds",
                        "Loading RSS Feeds from DN.se news paper!", true, false);
            }

            @Override
            protected  Void doInBackground(Void... params) {
                try {
                    InputStream inStream = new URL("http://www.dn.se/nyheter/m/rss/").openStream();
                    mNewsArrayList = XmlSAXParser.parse(inStream);
                }catch(Exception e)
                {
                    Log.i("NewsMainActivity","Parsing Xml file Exception: " + e.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void args) {
                customAdapter = new CustomFeedAdapter(NewsMainActivity.this);
                customAdapter.updateRssFeed(mNewsArrayList);
                lvDNFeeds.setAdapter(customAdapter);
                pd.dismiss();
            }
        }.execute();
        } else {
            Toast.makeText(getApplicationContext(),
                    "No Internet Connection!!", Toast.LENGTH_LONG).show();
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}



