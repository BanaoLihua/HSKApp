package com.example.hskapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    class ViewHolder {
        RelativeLayout relativeLayout;
        TextView textView;
    }

    private ArrayList<String> unitList;
    private String selectedLevel;
    private LayoutInflater inflater;
    private int layoutId;

    GridAdapter(Context context,
                int layoutId,
                ArrayList<String> unit_list,
                String selected_level) {
        super();
        this.inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutId = layoutId;
        unitList = unit_list;
        selectedLevel = selected_level;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // grid_item_buttonの中身のテキスト
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

        // grid_item_buttonの背景含む形
        holder.relativeLayout = convertView.findViewById(R.id.grid_item_button);
        if(selectedLevel == "1") {
            holder.relativeLayout.setBackgroundResource(R.drawable.grid_item_button1);
        }
        if(selectedLevel == "2") {
            holder.relativeLayout.setBackgroundResource(R.drawable.grid_item_button2);
        }
        if(selectedLevel == "3") {
            holder.relativeLayout.setBackgroundResource(R.drawable.grid_item_button3);
        }
        if(selectedLevel == "4") {
            holder.relativeLayout.setBackgroundResource(R.drawable.grid_item_button4);
        }
        if(selectedLevel == "5") {
            holder.relativeLayout.setBackgroundResource(R.drawable.grid_item_button5);
        }
        if(selectedLevel == "6") {
            holder.relativeLayout.setBackgroundResource(R.drawable.grid_item_button6);
        }

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
