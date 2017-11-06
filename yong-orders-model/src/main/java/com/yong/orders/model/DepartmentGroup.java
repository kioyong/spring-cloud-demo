package com.yong.orders.model;

import com.yong.orders.annotation.NotNull;
import com.yong.orders.annotation.Unique;
import com.yong.orders.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * Created by yong.a.liang on 7/14/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentGroup extends BaseEntity {

    @Id
    private String id;
    @NotNull(message = "departmentGroup name can't be null!")
    @Unique(message = "department Group Name already exists ! Please use other name!")
    private String name;
    private String type;

}
