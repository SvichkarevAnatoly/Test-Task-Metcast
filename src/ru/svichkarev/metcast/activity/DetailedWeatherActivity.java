package ru.svichkarev.metcast.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import ru.svichkarev.metcast.R;


public class DetailedWeatherActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.detailed_weather );

        Intent gettingIntent = getIntent();

        // TODO: сделать красиво
        TextView dateTV = (TextView) findViewById( R.id.dateTV );
        // TODO: в константы
        String dateStr = gettingIntent.getStringExtra( "DATE" );
        dateTV.setText( dateStr );

        TextView todTV = (TextView) findViewById( R.id.todTV );
        String todStr = gettingIntent.getStringExtra( "TOD" );
        todTV.setText( todStr );

        TextView shortInfTV = (TextView) findViewById( R.id.shortInfTV );
        String shortInfStr = gettingIntent.getStringExtra( "SHORT_INF" );
        shortInfTV.setText(shortInfStr);

        TextView pressureTV = (TextView) findViewById( R.id.pressureTV );
        String pressureStr = gettingIntent.getStringExtra( "PRESSURE" );
        pressureTV.setText( pressureTV.getText() + pressureStr);

        TextView windTV = (TextView) findViewById( R.id.windTV );
        String windStr = gettingIntent.getStringExtra( "WIND" );
        windTV.setText( windTV.getText() + windStr);

        TextView relwetTV = (TextView) findViewById( R.id.relwetTV );
        String relwetStr = gettingIntent.getStringExtra( "RELWET" );
        relwetTV.setText( relwetTV.getText() + relwetStr);

        TextView heatTempTV = (TextView) findViewById( R.id.heatTempTV);
        String heatTempStr = gettingIntent.getStringExtra( "HEAT_TEMP" );
        heatTempTV.setText( heatTempTV.getText() + heatTempStr);
    }
}