package ru.svichkarev.metcast.model.parameter;

public class Wind{
    private int min, max;
    private DIRECTION dir;

    // направление ветра в румбах, 0 - северный, 1 - северо-восточный,  и т.д.
    private enum DIRECTION{
        NORTH,
        NORTHEAST,
        EAST,
        SOUTHEAST,
        SOUTH,
        SOUTHWEST,
        WEST,
        NORTHWEST;

        @Override
        public String toString() {
            switch (this){
                case NORTH:
                    return "северный";
                case NORTHEAST:
                    return "северо-восточный";
                case EAST:
                    return "восточный";
                case SOUTHEAST:
                    return "юго-восточный";
                case SOUTH:
                    return "южный";
                case SOUTHWEST:
                    return "юго-западный";
                case WEST:
                    return "западный";
                case NORTHWEST:
                    return "северо-западный";
            }
            return null;
        }
    }

    public Wind(int min, int max, int dir) {
        this.min = min;
        this.max = max;
        this.dir = DIRECTION.values()[dir];
    }

    public String  getRangeString(){
        return dir.toString() + ", " + min + "-" + max + "м/с";
    }
}
