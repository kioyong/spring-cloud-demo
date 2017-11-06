package com.yong.orders.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by LiangYong on 2017/8/14.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Name {
    private String firstName,lastName,type;
    }
