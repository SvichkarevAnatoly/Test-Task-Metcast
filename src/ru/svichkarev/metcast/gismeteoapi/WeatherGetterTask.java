package ru.svichkarev.metcast.gismeteoapi;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.svichkarev.metcast.R;
import ru.svichkarev.metcast.model.Forecast;
import ru.svichkarev.metcast.model.WeatherInfo;
import ru.svichkarev.metcast.model.parameter.*;
import ru.svichkarev.metcast.util.ForecastItemAdapter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

// TODO: разобраться с параметрами AsyncTask
public class WeatherGetterTask extends AsyncTask<Void, Void, WeatherInfo> {
    // TODO: сделать разделяемым с Activities
    private WeatherInfo weatherInfo;

    private ListView listView;

    // TODO: отладка
    private static final String LOG_TAG = "Metcast";

    private static final String GISMETEO_API_URL = "http://informer.gismeteo.ru/xml/29634_1.xml";

    public WeatherGetterTask( Activity main ){
        weatherInfo = new WeatherInfo();
        listView = (ListView) main.findViewById(R.id.list);
    }

    @Override
    protected WeatherInfo doInBackground(Void... strings) {
        // при каждом обновлении заного создаём
        // TODO: по идее когда обновляются новые данные,
        //должна быть возможность прокручивать и просматривать старые
        parseWeather( GISMETEO_API_URL );
        return weatherInfo;
    }

    @Override
    protected void onPostExecute(WeatherInfo weatherInfo) {
        super.onPostExecute(weatherInfo);
        /*String weatherStrs[] = new String[4];

        for( int i = 0; i < 4; i++ ){
            Forecast forecast = weatherInfo.get(i);
            weatherStrs[i] = forecast.getDate();
        }*/

        ForecastItemAdapter adapter = new ForecastItemAdapter( listView.getContext(), R.layout.list_item, weatherInfo.getList() );
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>( listView.getContext(), android.R.layout.simple_list_item_1, weatherStrs);
        listView.setAdapter(adapter);
    }

    // TODO: сделать класс с константами
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
            Log.i( LOG_TAG, "Connection or parse error" );
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
        final String PHENOMENA_KEY = "PHENOMENA";
        final String PRESSURE_KEY = "PRESSURE";
        final String TEMPERATURE_KEY = "TEMPERATURE";
        final String WIND_KEY = "WIND";
        final String RELWET_KEY = "RELWET";
        final String HEAT_KEY = "HEAT";

        // TODO:
        weatherInfo = new WeatherInfo();

        for (int i = 0; i < forecasts.getLength(); i++) {
            Node forecastNode = forecasts.item(i);

            Date date = fillDate((Element) forecastNode);

            NodeList forecastParameters = forecastNode.getChildNodes();

            Element parameter = null;

            Temperature temperature = null;
            CLOUDINESS cloudiness = null;
            PRECIPITATION precipitation = null;
            Wind wind = null;
            Pressure pressure = null;
            Relwet relwet = null;
            Temperature temperatureHeat = null;
            for (int j = 0; j < forecastParameters.getLength(); j++) {
                // TODO: сделать проверку на cast
                Node parameterNode = forecastParameters.item(j);
                if( parameterNode instanceof Element ) {
                    parameter = (Element) parameterNode;

                    if (parameter.getNodeName().equalsIgnoreCase(PHENOMENA_KEY)) {
                        cloudiness = fillCloudiness(parameter);
                        precipitation = fillPrecipitation(parameter);
                    } else if (parameter.getNodeName().equalsIgnoreCase(PRESSURE_KEY)) {
                        pressure = fillPresure(parameter);
                    } else if (parameter.getNodeName().equalsIgnoreCase(TEMPERATURE_KEY)) {
                        temperature = fillTemperature(parameter);
                    } else if (parameter.getNodeName().equalsIgnoreCase(WIND_KEY)) {
                        wind = fillWind(parameter);
                    } else if (parameter.getNodeName().equalsIgnoreCase(RELWET_KEY)) {
                        relwet = fillRelwet(parameter);
                    } else if (parameter.getNodeName().equalsIgnoreCase(HEAT_KEY)) {
                        temperatureHeat = fillTemperatureHeat(parameter);
                    }
                }
            }

            Forecast forecast = new Forecast(date, temperature, cloudiness,
                    precipitation, wind, pressure, relwet, temperatureHeat);

            weatherInfo.add(forecast);
        }
    }

    private Temperature fillTemperatureHeat(Element element) {
        // TODO: сделать константы статическими
        final String MIN_KEY = "min";
        final String MAX_KEY = "max";

        int min = Integer.parseInt(element.getAttribute(MIN_KEY));
        int max = Integer.parseInt(element.getAttribute(MAX_KEY));

        Temperature temperature = new Temperature( min, max );

        return temperature;
    }

    private Wind fillWind(Element element) {
        // TODO: сделать константы статическими
        final String MIN_KEY = "min";
        final String MAX_KEY = "max";
        final String DIRECTION_KEY = "direction";

        int min = Integer.parseInt(element.getAttribute(MIN_KEY));
        int max = Integer.parseInt(element.getAttribute(MAX_KEY));
        int dir = Integer.parseInt(element.getAttribute(DIRECTION_KEY));

        Wind wind = new Wind( min, max, dir );

        return wind;
    }

    private Relwet fillRelwet(Element element) {
        // TODO: сделать константы статическими
        final String MIN_KEY = "min";
        final String MAX_KEY = "max";

        int min = Integer.parseInt(element.getAttribute(MIN_KEY));
        int max = Integer.parseInt(element.getAttribute(MAX_KEY));

        Relwet relwet = new Relwet( min, max );

        return relwet;
    }

    private Pressure fillPresure(Element element) {
        // TODO: сделать константы статическими
        final String MIN_KEY = "min";
        final String MAX_KEY = "max";

        int min = Integer.parseInt(element.getAttribute(MIN_KEY));
        int max = Integer.parseInt(element.getAttribute(MAX_KEY));

        Pressure pressure = new Pressure( min, max );

        return pressure;
    }

    private PRECIPITATION fillPrecipitation(Element element) {
        // TODO: сделать константы статическими
        final String PRECIPITATION_KEY = "precipitation";

        int index = Integer.parseInt(element.getAttribute(PRECIPITATION_KEY));
        PRECIPITATION precipitation = PRECIPITATION.values()[ index - 4 ];

        return precipitation;
    }

    private CLOUDINESS fillCloudiness(Element element) {
        // TODO: сделать константы статическими
        final String CLOUDINESS_KEY = "cloudiness";

        int index = Integer.parseInt(element.getAttribute(CLOUDINESS_KEY));
        CLOUDINESS cloudiness = CLOUDINESS.values()[ index ];

        return cloudiness;
    }

    private Temperature fillTemperature(Element element) {
        // TODO: сделать константы статическими
        final String MIN_KEY = "min";
        final String MAX_KEY = "max";

        int min = Integer.parseInt(element.getAttribute(MIN_KEY));
        int max = Integer.parseInt(element.getAttribute(MAX_KEY));

        Temperature temperature = new Temperature( min, max );

        return temperature;
    }

    private Date fillDate(Element element) {
        // TODO: сделать константы статическими
        final String DAY_KEY = "day";
        final String MONTH_KEY = "month";
        final String YEAR_KEY = "year";
        final String TOD_KEY = "tod";

        int day = Integer.parseInt(element.getAttribute(DAY_KEY));
        int month = Integer.parseInt(element.getAttribute(MONTH_KEY));
        int year = Integer.parseInt(element.getAttribute(YEAR_KEY));
        int tod = Integer.parseInt(element.getAttribute(TOD_KEY));

        Date date = new Date( day, month, year, tod );

        return date;
    }

}
