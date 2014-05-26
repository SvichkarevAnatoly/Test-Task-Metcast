package ru.svichkarev.metcast.gismeteoapi;

import android.os.AsyncTask;
import android.util.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.svichkarev.metcast.model.WeatherInfo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

// TODO: разобраться с параметрами AsyncTask
public class WeatherGetterTask extends AsyncTask<Void, Void, WeatherInfo> {
    WeatherInfo weatherInfo = new WeatherInfo();

    // TODO: отладка
    private static final String LOG_TAG = "Metcast";

    private static final String GISMETEO_API_URL = "http://informer.gismeteo.ru/xml/29634_1.xml";

    @Override
    protected WeatherInfo doInBackground(Void... strings) {
        parseWeather( GISMETEO_API_URL );
        return weatherInfo;
    }

    private void parseWeather(String queryURL) {
        InputStream inputStream = null;
        try {
            // TODO:
            URL url = new URL(queryURL);
            // TODO:
            URLConnection connection = url.openConnection();
            // TODO:
            inputStream = new BufferedInputStream( connection.getInputStream() );
            // TODO:
            Document doc = parseXML( inputStream );
            // TODO:
            NodeList forecasts = doc.getElementsByTagName( "FORECAST" );

            fillWeather(forecasts);
        } catch (Exception e){
            // TODO: отладка
            Log.i( LOG_TAG, "Connection error" );
        } finally {
            if( inputStream != null ){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.i(LOG_TAG, "Closing stream error");
                }
            }
        }
    }

    // парсим поток в xml документ
    private Document parseXML (InputStream input) throws Exception{
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
            doc = objDocumentBuilder.parse(input);
        }
        catch (Exception e) {
            throw e;
        }
        return doc;
    }

    // заполнение модели данных
    private void fillWeather( NodeList forecasts ) {

        List<String> result = new ArrayList<String>();
        for (int i = 0; i < forecasts.getLength(); i++) {
            Element element = (Element) forecasts.item(i);
            // TODO:
        }
    }

}
