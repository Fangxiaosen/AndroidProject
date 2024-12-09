package com.example.myapplication.baen;


import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class House {

    private static int houseImgArray[] = {
            R.drawable.list1,
            R.drawable.list2,
            R.drawable.list3,

    };
    private static String houseTitleArray[] = {
            "交通便利市中心闹中取静 一居室",
            "公园迷你海洋房",
            "购物中心附近单间",
    };
    private static float housePriceArray[] = {
            368.00f,
            498.00f,
            199.88f,
    };
    public int houseImg;
    public String houseTitle;
    public float housePrice;
    public House(int houseImg, String houseTitle, float housePrice) {
        this.houseImg = houseImg;
        this.houseTitle = houseTitle;
        this.housePrice = housePrice;
    }

    public static List<House> getHouseDate() {
        List<House> houseList = new ArrayList<>();
        for (int i = 0; i < housePriceArray.length; i++) {
            House house = new House(houseImgArray[i], houseTitleArray[i], housePriceArray[i]);
            houseList.add(house);
        }
        return houseList;
    }
}
