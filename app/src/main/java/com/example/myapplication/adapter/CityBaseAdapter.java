package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.baen.City;

import java.util.List;

public class CityBaseAdapter extends BaseAdapter {

    private Context context;
    private List<City> list;
    private int templateCity;

    public CityBaseAdapter(Context context, List<City> list, int templateCity) {
        this.context = context;
        this.list = list;
        this.templateCity = templateCity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {

//            convertView = templateCity.inflate(R.layout.template_list_city, parent,false);

            // 使用 LayoutInflater 加载布局
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(templateCity, parent, false);

            holder = new ViewHolder();
//            convertView = View.inflate(Context, templateCity, null);
            holder.ivIcon = convertView.findViewById(R.id.iv_icon);
            holder.tvTitle = convertView.findViewById(R.id.tv_title);
            holder.tvText = convertView.findViewById(R.id.tv_text);
            convertView.setTag(holder);
        }
        City city = list.get(position);
        holder.ivIcon.setImageResource(city.img);
        holder.tvTitle.setText(city.title);
        holder.tvText.setText(city.text);
        return convertView;
    }

    public class ViewHolder {
        public ImageView ivIcon;
        public TextView tvTitle;
        public TextView tvText;

    }
}
