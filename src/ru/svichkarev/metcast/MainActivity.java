package ru.svichkarev.metcast;

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
import ru.svichkarev.metcast.gismeteoapi.WeatherGetterTask;

public class MainActivity extends Activity {

    // TODO: для тестирования
    String[] countries = { "Россия", "США", "Великобритания", "Франция", "Китай",
            "Россия", "США", "Великобритания", "Франция", "Китай"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO: для тестирования
                Toast.makeText( getApplicationContext(),
                        "You choose " + adapterView.getItemAtPosition(i).toString(),
                        Toast.LENGTH_LONG ).show();

                Intent intent = new Intent( MainActivity.this, DetailedWeatherActivity.class );
                // TODO: передача параметров
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
