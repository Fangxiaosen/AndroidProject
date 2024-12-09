package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.baen.House;

import java.util.List;

public class HouseBaseAdapter extends BaseAdapter {
    public Context mContext;
    public List<House> list;
    public int template;

    public HouseBaseAdapter(Context mContext, List<House> list, int template) {
        this.mContext = mContext;
        this.list = list;
        this.template = template;
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
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(template, null);

            holder.ivHouseImg = convertView.findViewById(R.id.iv_img);
            holder.tvHouseTitle = convertView.findViewById(R.id.tv_title);
            holder.tvHousePrice = convertView.findViewById(R.id.tv_price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        House house = list.get(position);
        holder.ivHouseImg.setImageResource(house.houseImg);
        holder.tvHouseTitle.setText(house.houseTitle);
        holder.tvHousePrice.setText(Float.toString(house.housePrice));
        return convertView;
    }

    public class ViewHolder {
        // 控件
        public ImageView ivHouseImg;
        public TextView tvHouseTitle;
        public TextView tvHousePrice;
    }
}
