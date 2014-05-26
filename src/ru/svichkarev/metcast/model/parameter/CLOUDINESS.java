package ru.svichkarev.metcast.model.parameter;

public enum CLOUDINESS{
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
