package mo.dnnewsapp;
/**
 * Created by Mo on 27/11/2016.
 *
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FeedDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_details);
        if (savedInstanceState == null) {
            Bundle b = getIntent().getExtras();
            RssFeedItem obj = b.getParcelable(Constants.KEY_RSSFEEDITEM_OBJECT);
            FeedDetailsFragment detailFragment = new FeedDetailsFragment();
            detailFragment.setRssFeedObj(obj);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.feed_detail_container,detailFragment)
                    .commit();
        }
    }
}
