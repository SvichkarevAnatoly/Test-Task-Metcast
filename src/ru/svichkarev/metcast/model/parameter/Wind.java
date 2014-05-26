package ru.svichkarev.metcast.model.parameter;

public class Wind{
    private int min, max;

    // TODO:
        /*private enum DIRECTION{

        }*/

    public String  getRangeString(){
        return "" + min + "-" + max;
    }
}
