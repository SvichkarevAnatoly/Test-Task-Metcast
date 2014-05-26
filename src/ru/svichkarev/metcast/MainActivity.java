package ru.svichkarev.metcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        WeatherGetterTask weatherGetterTask = new WeatherGetterTask( this );
        weatherGetterTask.execute();

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
    }
}
