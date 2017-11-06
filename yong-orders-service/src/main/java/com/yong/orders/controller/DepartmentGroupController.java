package com.yong.orders.controller;

import com.yong.orders.model.DepartmentGroup;
import com.yong.orders.service.DepartmentGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yong.a.liang on 7/14/2017.
 */
@RestController
@RequestMapping("/departmentGroup")
public class DepartmentGroupController extends BaseController<DepartmentGroup>{
    private final static Logger LOG = LoggerFactory.getLogger(DepartmentGroupController.class);

    private DepartmentGroupService service;

    public DepartmentGroupController(DepartmentGroupService service){
        super(service);
        this.service = service;
    }
}
