package ru.svichkarev.metcast.model;

import java.util.ArrayList;
import java.util.List;

// вся информация о погоде
public class WeatherInfo{
    // TODO: по-умолчанию вроде 4 прогноза
    private List<Forecast> forecasts;

    public WeatherInfo() {
        forecasts = new ArrayList<Forecast>( 4 );
    }

    public WeatherInfo(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    public void add( Forecast forecast ){
        forecasts.add( forecast );
    }

    public Forecast get( int indexForecast ) {
        return forecasts.get(indexForecast);
    }

    public List<Forecast> getList() {
        return forecasts;
    }
}
