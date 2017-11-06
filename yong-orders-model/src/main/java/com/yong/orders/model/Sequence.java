package com.yong.orders.model;

import com.yong.orders.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by LiangYong on 2017/7/26.
 */
@Data
@Builder
@Document(collection = "sequenceConfig")
@AllArgsConstructor
@NoArgsConstructor
public class Sequence extends BaseEntity{

    @Id
    private String id;

    private Long sequenceNo;

}
