package ru.svichkarev.metcast.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ru.svichkarev.metcast.R;
import ru.svichkarev.metcast.model.Forecast;
import ru.svichkarev.metcast.model.WeatherInfo;

import java.util.List;

public class ForecastItemAdapter extends ArrayAdapter<Forecast> {
    private WeatherInfo weatherInfo;
    private final Context context;

    public ForecastItemAdapter(Context context, int resourceId, List<Forecast> forecasts) {
        super(context, resourceId, forecasts);

        this.context = context;
        weatherInfo = new WeatherInfo( forecasts );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R.layout.list_item, null);
        }

        Forecast forecast = weatherInfo.get(position);
        if (forecast != null) {
            TextView dateTV = (TextView) view.findViewById(R.id.item_date);
            TextView todTV = (TextView) view.findViewById(R.id.item_tod);
            TextView shortInfTV = (TextView) view.findViewById(R.id.item_short_inf);

            dateTV.setText( forecast.getDate() );
            todTV.setText( forecast.getTOD() );
            shortInfTV.setText( forecast.getShortInf() );
        }
        return view;
    }
}
