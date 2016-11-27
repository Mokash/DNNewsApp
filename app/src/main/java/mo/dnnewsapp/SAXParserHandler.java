package mo.dnnewsapp;

/**
 * Created by Mo on 06/11/2016.
 */

import android.text.Html;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import java.util.ArrayList;
import java.util.Date;
import android.util.Log;


public class SAXParserHandler extends DefaultHandler{

    private RssFeedItem feedItem;
    private ArrayList<RssFeedItem> feedsList;
    boolean currentElement = false;
    String currentValue ="";

    public ArrayList<RssFeedItem> getFeedsList()
    {
        return feedsList;
    }

    public void startElement(String uri, String tagName, String qName,
                             Attributes attributes) throws SAXException
    {
        currentElement = true;
        currentValue ="";
        if(tagName.equalsIgnoreCase("channel")) {
            feedsList = new ArrayList<RssFeedItem>();
        }else if(tagName.equalsIgnoreCase("title")) {
            feedItem = new RssFeedItem();
        }
        Log.i("StartElement", "TAG: " + tagName);
    }
    public void endElement(String uri, String tagName, String qName) throws SAXException
    {
        currentElement = false;
        Log.i("EndElement", "TAG: " + tagName);
        if(tagName.equalsIgnoreCase("title")) {
            feedItem.setTitle(Html.fromHtml(currentValue).toString());
        }else if(tagName.equalsIgnoreCase("link")) {
            feedItem.setUrlLink(currentValue.trim());
        }else if(tagName.equalsIgnoreCase("description")) {
            feedItem.setDetails(Html.fromHtml(currentValue.toString()).toString());
        }else if(tagName.equalsIgnoreCase("pubDate")) {
            feedItem.setPublishDate(currentValue.trim());
        }else if(tagName.equalsIgnoreCase("guid")) {
            feedItem.setGuid(currentValue.trim());
        }else if (tagName.equalsIgnoreCase("item")){
            feedsList.add(feedItem);
            currentValue= "";
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException{
        if(currentElement)
        {
          currentValue= currentValue + new String(ch, start, length);
            currentElement = false;
        }
    }

}