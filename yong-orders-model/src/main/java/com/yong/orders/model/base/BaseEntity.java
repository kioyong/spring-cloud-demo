package com.yong.orders.model.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;

import java.util.Date;


/**
 * Created by yong.a.liang on 6/22/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    @CreatedDate
    protected Date createdDate;
    @LastModifiedDate
    protected Date lastModifiedDate;
    @CreatedBy
    protected String createdBy;
    @LastModifiedBy
    protected String lastModifiedBy;
    protected Boolean isActive;


}
