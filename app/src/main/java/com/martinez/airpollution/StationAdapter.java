package com.martinez.airpollution;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.martinez.airpollution.logic.Property;

import java.util.List;

public class StationAdapter extends BaseAdapter {
    List<Property> properties;
    private Context context;

    public StationAdapter(List<Property> properties, Context context){
        this.properties = properties;
        this.context = context;
    }

    @Override
    public int getCount() {
        return properties.size();
    }

    @Override
    public Object getItem(int position) {
        return properties.get(position);
    }

    @Override
    public long getItemId(int position) {
        //return properties.get(position).getId();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.element_property, null);

        Property property = (Property) getItem(position);

        ((TextView)convertView.findViewById(R.id.tv_id)).setText(property.getId());
        ((TextView)convertView.findViewById(R.id.tv_value)).setText(property.getValue());

        return convertView;
    }
}
