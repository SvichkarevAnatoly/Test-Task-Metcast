package ru.svichkarev.metcast.model.parameter;

public class Date{
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
                    return "вечер";
            }
            return null;
        }
    }

    public Date(int day, int month, int year, int tod) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.tod = TOD.values()[ tod ];
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
