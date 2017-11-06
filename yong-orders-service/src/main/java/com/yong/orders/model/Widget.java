package com.yong.orders.model;

/**
 * Created by LiangYong on 2017/9/2.
 */
public class Widget {
    private final Color color;
    private final int weight;

    public Widget(Color color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public Color getColor() {return color;}
    public int getWeight() {return weight;}

}
