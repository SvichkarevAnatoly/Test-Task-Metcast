package ru.svichkarev.metcast;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MyActivity extends Activity {

    // TODO: для тестирования
    String[] countries = { "Россия", "США", "Великобритания", "Франция", "Китай",
            "Россия", "США", "Великобритания", "Франция", "Китай"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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
            }
        });
    }
}
