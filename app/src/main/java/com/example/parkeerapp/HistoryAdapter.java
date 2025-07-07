package com.example.parkeerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class HistoryAdapter extends BaseAdapter {
    private Context context;
    private List<String> dummyList; // hanya buat isi data dummy

    public HistoryAdapter(Context context, List<String> dummyList) {
        this.context = context;
        this.dummyList = dummyList;
    }

    @Override
    public int getCount() {
        return dummyList.size();
    }

    @Override
    public Object getItem(int position) {
        return dummyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.layout_item_history, parent, false);
    }
}
