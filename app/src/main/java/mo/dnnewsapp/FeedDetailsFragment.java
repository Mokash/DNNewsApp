package mo.dnnewsapp;

/**
 * Created by Mo on 27/11/2016.
 *
 */
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FeedDetailsFragment extends Fragment {
    private RssFeedItem mSelectedFeed;

    public FeedDetailsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mSelectedFeed.getGuid() == null) {
            getActivity().getFragmentManager().popBackStack();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feed_details, container, false);
        if (mSelectedFeed.getGuid() != null) {
            ((TextView) rootView.findViewById(R.id.title)).setText(mSelectedFeed.getTitle());
            ((TextView) rootView.findViewById(R.id.date)).setText(mSelectedFeed.getPublishDate());
            ((TextView) rootView.findViewById(R.id.description)).setText(mSelectedFeed.getDetails());
            rootView.findViewById(R.id.read_more).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(mSelectedFeed.getUrlLink());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
        }
        return rootView;
    }

    public void setRssFeedObj(RssFeedItem rssFeedItem) {
        this.mSelectedFeed = rssFeedItem;
    }
}
