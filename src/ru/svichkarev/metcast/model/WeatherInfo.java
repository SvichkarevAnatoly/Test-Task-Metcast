package ru.svichkarev.metcast.model;

import java.util.Vector;

// вся информация о погоде
public class WeatherInfo{
    // TODO: по-умолчанию вроде 4 прогноза
    private Vector<Forecast> forecasts = new Vector<Forecast>(4);

    public void add( Forecast forecast ){
        forecasts.add( forecast );
    }

    public Forecast get( int indexForecast ) {
        return forecasts.get(indexForecast);
    }
}
