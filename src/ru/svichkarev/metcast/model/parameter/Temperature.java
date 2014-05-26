package ru.svichkarev.metcast.model.parameter;

public class Temperature{
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
