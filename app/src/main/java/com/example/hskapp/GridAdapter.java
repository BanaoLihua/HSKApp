package com.example.hskapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    class ViewHolder {
        TextView textView;
    }

    private ArrayList<String> unitList;
    private LayoutInflater inflater;
    private int layoutId;

    GridAdapter(Context context,
                int layoutId,
                ArrayList<String> unit_list) {
        super();
        this.inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutId = layoutId;
        unitList = unit_list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(layoutId, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.text_view);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(unitList.get(position));
        return convertView;
    }

    @Override
    public int getCount() {
        return unitList.size();
    }

    @Override
    public Object getItem(int position) {
        return unitList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
