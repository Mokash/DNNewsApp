package mo.dnnewsapp;

/**
 * Created by Mo on 06/11/2016.
 * This class is to Parse XML file using SAX Parser
 */

import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import android.util.Log;

public class XmlSAXParser {

    public static ArrayList<RssFeedItem> parse(InputStream is) {
        ArrayList<RssFeedItem> NewsArticles = null;
        try {
            // create a XMLReader from SAXParser
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser()
                    .getXMLReader();
            // create a SAX Handler
            SAXParserHandler SAXhandler = new SAXParserHandler();
            // store handler in XMLReader
            xmlReader.setContentHandler(SAXhandler);
            // parsing xml file starts
            xmlReader.parse(new InputSource(is));
            // get a list of the article from SAX Handler
            NewsArticles = SAXhandler.getFeedsList();

        } catch (Exception ex) {
            Log.d("XMLFile", "SAX Parser: parse() failed!");
            ex.printStackTrace();
        }
        // return articles list
        return NewsArticles;
    }
}
