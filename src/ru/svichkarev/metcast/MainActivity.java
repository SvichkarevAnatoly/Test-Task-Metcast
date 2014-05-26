package ru.svichkarev.metcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import ru.svichkarev.metcast.gismeteoapi.WeatherGetterTask;
import ru.svichkarev.metcast.model.WeatherInfo;

import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {

    // TODO: для тестирования
    String[] countries = { "Россия", "США", "Великобритания", "Франция", "Китай",
            "Россия", "США", "Великобритания", "Франция", "Китай"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        WeatherGetterTask weatherGetterTask = new WeatherGetterTask();
        weatherGetterTask.execute();
        // TODO:
        try {
            WeatherInfo weatherInfo = weatherGetterTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ListView listView = (ListView) findViewById(R.id.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        listView.setAdapter(adapter);

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
