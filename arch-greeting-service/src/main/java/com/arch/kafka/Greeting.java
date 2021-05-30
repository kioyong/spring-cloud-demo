package com.arch.kafka;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Greeting {

    private String id;
    private Date date;


    private Date createdDate;
}
