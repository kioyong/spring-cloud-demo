package com.yong.orders.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by LiangYong on 2017/8/31.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    public enum Sex{
        MALE,FEMALE
    }
    private String namue,emailAddress;
    private LocalDate birthday;
    private int Age;
    private Sex gender;
}
