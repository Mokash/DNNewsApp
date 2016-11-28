package mo.dnnewsapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mo on 06/11/2016.
 * This class define DN Rss tags
 */

public class RssFeedItem implements Parcelable {
    private String Title;
    private String Details;
    private String UrlLink;
    private String PublishDate;
    private String Guid;



    public String getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(String publishDate) {
        PublishDate = publishDate;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getUrlLink() {
        return UrlLink;
    }

    public void setUrlLink(String urlLink) {
        UrlLink = urlLink;
    }

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String guid) {
        Guid = guid;
    }

    public RssFeedItem(){;};

    public RssFeedItem(Parcel in) {
        readFromParcel(in);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(Details);
        dest.writeString(UrlLink);
        dest.writeString(PublishDate);
        dest.writeString(Guid);
    }
    private void readFromParcel(Parcel in) {
        Title = in.readString();
        Details = in.readString();
        UrlLink = in.readString();
        PublishDate = in.readString();
        Guid = in.readString();
    }
    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public RssFeedItem createFromParcel(Parcel in) {
                    return new RssFeedItem(in);
                }

                public RssFeedItem[] newArray(int size) {
                    return new RssFeedItem[size];
                }
            };


}
