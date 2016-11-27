package mo.dnnewsapp;

/**
 * Created by Mo on 06/11/2016.
 * This class define every DN news Rss tags
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.InputStream;
import java.net.URL;
import java.util.List;


public class NewsMainActivity extends AppCompatActivity {
    private List<RssFeedItem> newsArrayList;
    private ListView lvDNFeeds;
    private static CustomFeedAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_main);
        lvDNFeeds = (ListView) findViewById(R.id.lvDNFeeds);
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
                    newsArrayList = XmlSAXParser.parse(inStream);
                }catch(Exception e)
                {
                    Log.i("NewsMainActivity","Parsing Xml file Exception: " + e.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void args) {
                customAdapter = new CustomFeedAdapter(NewsMainActivity.this);
                customAdapter.updateRssFeed(newsArrayList);
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



