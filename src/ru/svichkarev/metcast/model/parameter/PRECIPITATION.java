package ru.svichkarev.metcast.model.parameter;

public enum PRECIPITATION{
    RAIN,
    RAINFALL,
    SNOW,
    SNOW2,
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
            case SNOW2:
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
