package com.yong.orders.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yong.a.liang on 9/26/2017.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="errorLog")
@Data
public class ErrorLog {

    @Id
    private String id;

    private String message;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date LastModifiedDate;

    private int count;

    private List<Map<String, String>> detail;

}
