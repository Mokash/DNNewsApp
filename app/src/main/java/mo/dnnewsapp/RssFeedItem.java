package mo.dnnewsapp;

/**
 * Created by Mo on 06/11/2016.
 * This class define DN Rss tags
 */

public class RssFeedItem {
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
}
