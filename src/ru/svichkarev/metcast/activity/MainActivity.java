package ru.svichkarev.metcast.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import ru.svichkarev.metcast.R;
import ru.svichkarev.metcast.gismeteoapi.WeatherGetterTask;
import ru.svichkarev.metcast.model.Forecast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // выбранный прогноз
                Forecast choosenForecast = (Forecast)adapterView.getItemAtPosition(i);

                Toast.makeText( getApplicationContext(),
                        "You choose " + choosenForecast.getTOD(),
                        Toast.LENGTH_LONG ).show();

                Intent intent = new Intent( MainActivity.this, DetailedWeatherActivity.class );

                // передача выбранного прогноза
                // TODO: забить в константы
                intent.putExtra( "DATE", choosenForecast.getDate() );
                intent.putExtra( "TOD", choosenForecast.getTOD() );
                intent.putExtra( "SHORT_INF", choosenForecast.getShortInfFullTemp() );

                intent.putExtra( "PRESSURE", choosenForecast.getPressure() );
                intent.putExtra( "WIND", choosenForecast.getWind() );
                intent.putExtra( "RELWET", choosenForecast.getRelwet() );
                intent.putExtra( "HEAT_TEMP", choosenForecast.getHeatTemp() );

                startActivity( intent );
            }
        });

        Button updateBtn = (Button) findViewById(R.id.updateBtn);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( isOnline() ) {
                    // TODO: нужно как-то проверять доступность сервера
                    WeatherGetterTask weatherGetterTask = new WeatherGetterTask( MainActivity.this );
                    weatherGetterTask.execute();
                } else{
                    popDialog();
                }
            }
        });
    }

    // проверка доступности сети
    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    // показать окошко с необходимостью соединения
    private void popDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.no_connection_title)
                .setMessage(R.string.no_connection_message)
                .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }
}
