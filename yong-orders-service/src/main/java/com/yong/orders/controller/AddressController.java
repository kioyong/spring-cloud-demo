package com.yong.orders.controller;

import com.yong.orders.model.Address;
import com.yong.orders.service.AddressService;
import com.yong.orders.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by LiangYong on 2017/9/14.
 */
@RestController
@RequestMapping("/address")
public class AddressController extends BaseController<Address> {

    private final AddressService service;

    public AddressController(AddressService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/location/{locationId}")
    public List<Address> findByLocation(@PathVariable String locationId){
        return service.findByLocation(locationId);
    }
}
