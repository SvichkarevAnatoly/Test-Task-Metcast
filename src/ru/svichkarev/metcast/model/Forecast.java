package ru.svichkarev.metcast.model;

import ru.svichkarev.metcast.model.parameter.*;

// данные одного прогноза
public class Forecast {
    private Date date;
    private Temperature temp;
    private CLOUDINESS cloudiness;
    private PRECIPITATION precipitation;
    private Wind wind;
    private Pressure pressure;
    private Relwet relwet;
    // комфортная температура
    private Temperature tempHeat;

    // конструктор
    public Forecast(Date date, Temperature temp,
                    CLOUDINESS cloudiness, PRECIPITATION precipitation,
                    Wind wind, Pressure pressure, Relwet relwet, Temperature tempHeat) {
        this.date = date;
        this.temp = temp;
        this.cloudiness = cloudiness;
        this.precipitation = precipitation;
        this.wind = wind;
        this.pressure = pressure;
        this.relwet = relwet;
        this.tempHeat = tempHeat;
    }

    public String getDate(){
        return date.toString();
    }

    public String getTOD(){
        return date.todToString();
    }

    public String getShortInf(){
        return temp.getAverageString() + ", " + cloudiness.toString() + ", " + precipitation.toString();
    }
}
