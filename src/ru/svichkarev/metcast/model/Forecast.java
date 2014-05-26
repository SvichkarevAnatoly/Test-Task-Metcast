package ru.svichkarev.metcast.model;

// данные одного прогноза
public class Forecast {
    private Date date;
    private Temperature temp;
    private CLOUDINESS cloudiness;
    private PRECIPITATION precipitation;
    private Wind wind;
    private Presure presure;
    private Relwet relwet;
    // комфортная температура
    private Temperature tempHeat;

    // конструктор
    public Forecast(Date date, Temperature temp,
                    CLOUDINESS cloudiness, PRECIPITATION precipitation,
                    Wind wind, Presure presure, Relwet relwet, Temperature tempHeat) {
        this.date = date;
        this.temp = temp;
        this.cloudiness = cloudiness;
        this.precipitation = precipitation;
        this.wind = wind;
        this.presure = presure;
        this.relwet = relwet;
        this.tempHeat = tempHeat;
    }

    private static class Date{
        private int day, month, year;
        private TOD tod;

        private enum TOD{
            NIGHT,
            MORNING,
            DAY,
            EVENING;

            @Override
            public String toString() {
                switch (this){
                    case NIGHT:
                        return "ночь";
                    case MORNING:
                        return "утро";
                    case DAY:
                        return "день";
                    case EVENING:
                        return "утро";
                }
                return null;
            }
        }

        private Date(int day, int month, int year, TOD tod) {
            this.day = day;
            this.month = month;
            this.year = year;
            this.tod = tod;
        }

        @Override
        public String toString(){
            return "" + day + "." + month + "." + year;
        }

        // получить время суток
        public String todToString(){
            return tod.toString();
        }
    }

    private class Temperature{
        private int min, max;

        public Temperature(int min, int max) {
            this.min = min;
            this.max = max;
        }

        // получить среднюю температуру для краткой информации
        public String getAverageString(){
            int average = (min + max) / 2;
            return sign( average ) + average;
        }

        // получить диапазон температуры
        public String getRangeString(){
            return sign( min ) + min + "..." + sign( max ) + max;
        }

        // возвращает знак температуры
        private String sign( int temp ){
            if( temp > 0 ){
                return "+";
            } else{
                if( temp == 0 ){
                    return "";
                } else{
                    return "-";
                }
            }
        }
    }

    private enum CLOUDINESS{
        CLEAR,
        PARTLY,
        RAIN,
        CLOUDY;

        @Override
        public String toString() {
            switch (this){
                case CLEAR:
                    return "ясно";
                case PARTLY:
                    return "малооблачно";
                case RAIN:
                    return "облачно";
                case CLOUDY:
                    return "пасмурно";
            }
            return null;
        }
    }

    // TODO: тут нумерация сбита
    private enum PRECIPITATION{
        RAIN,
        RAINFALL,
        SNOW,
        STORM,
        UNKNOWN,
        WITHOUT;

        @Override
        public String toString() {
            switch (this){
                case RAIN:
                    return "дождь";
                case RAINFALL:
                    return "ливень";
                case SNOW:
                    return "снег";
                case STORM:
                    return "гроза";
                case UNKNOWN:
                    return "нет данных";
                case WITHOUT:
                    return "без осадков";
            }
            return null;
        }
    }

    private class Presure{
        private int min, max;

        private Presure(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return "" + (max + min) / 2;
        }
    }

    private class Relwet {
        private int min, max;

        private Relwet(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return "" + (max + min) / 2;
        }
    }

    private class Wind{
        private int min, max;

        // TODO:
        /*private enum DIRECTION{

        }*/

        public String  getRangeString(){
            return "" + min + "-" + max;
        }
    }
}
