package com.example.myapplication.baen;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Goods {
    //手机名称数组
    private static String[] nameArray = {
            "iphone11", "Mate30", "OPPO Reno3", "荣耀30S"
    };
    //手机描述信息数组
    private static String[] descArray = {"Apple iphone11 256GB 绿色 4G全网通手机",
            "华为 HUAWEI Mate30 8G+256GB 丹霞橙 5G全网通 全面屏手机",
            "OPPO Reno3 8G+128G 蓝色星夜 双模5G 拍照游戏智能手机",
            "荣耀30S 8G+128G 鲽羽红 5G芯片 自拍全面屏手机"
    };
    //手机价格数组
    private static float[] priceArray = {6299, 4999, 3999, 2999};
    //手机图片数组
    private static int[] imgArray = {R.drawable.iphone, R.drawable.huawei, R.drawable.oppo, R.drawable.rongyao};
    public String name; //名称
    public String desc; //描述
    public float price; //价格
    public int pic; //图片

    public Goods(String name, String desc, float price, int pic) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.pic = pic;
    }

    //获取手机列表信息
    public static List<Goods> getGoodsList() {
        List<Goods> list = new ArrayList<Goods>();
        for (int i = 0; i < nameArray.length; i++) {
            list.add(new Goods(nameArray[i], descArray[i], priceArray[i], imgArray[i]));
        }
        return list;
    }
}
