package com.example.myapplication.baen;

import com.example.myapplication.R;

public class City {
    private static int[] imgArray = new int[]{
            R.drawable.beijing, R.drawable.guangzhou, R.drawable.hangzhou
    };
    private static String[] titleArray = new String[]{
            "北京", "广州", "杭州"
    };
    private static String[] textArray = new String[]{
            "bjbj", "gzgz", "hzhz"
    };
    public int img; //城市图片
    public String title; //城市名称
    public String text; //城市介绍

    public City(int img, String title, String text) {
        this.img = img;
        this.title = title;
        this.text = text;
    }

    public static City[] getCityList() {
        City[] cities = new City[imgArray.length];
        for (int i = 0; i < imgArray.length; i++) {
            cities[i] = new City(imgArray[i], titleArray[i], textArray[i]);
        }
        return cities;
    }
}
