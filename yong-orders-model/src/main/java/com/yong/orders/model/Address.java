package com.yong.orders.model;

import com.yong.orders.model.base.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by yong.a.liang on 6/21/2017.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="address")
public class Address extends BaseEntity {
    @Id
    private String add;
    private String location;
    private String country;
}
