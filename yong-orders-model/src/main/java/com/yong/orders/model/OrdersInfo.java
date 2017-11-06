package com.yong.orders.model;

import com.yong.orders.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by LiangYong on 2017/8/14.
 */
@Data
@Builder
@Document(collection = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class OrdersInfo extends BaseEntity {
    @Id
    private String id;
    private String cust_id;
    private String ord_date;
    private String status;
    private long amount;
    private List<Name> name;

}
