package com.yong.orders.model;

/**
 * Created by LiangYong on 2017/9/2.
 */
public class Transaction {
    private final int id;
    private final Integer value;
    private final Type type;

    public Transaction(int id, Integer value, Type type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }

    public enum Type {
        A, B, C, D, GEOCERY
    }

    public int getId() {return id;}
    public Integer getValue() {return value;}
    public Type getType() {return type;}

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", value=" + value +
                ", type=" + type +
                '}';
    }

}

