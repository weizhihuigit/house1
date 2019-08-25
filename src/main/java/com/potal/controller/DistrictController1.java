package com.potal.controller;

import com.pojo.District;
import com.service.ServiceDistrict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/page")
public class DistrictController1 {
    @Autowired
    private ServiceDistrict serviceDistrict;
    @RequestMapping("/getAllDistrict")
    @ResponseBody
    private List<District>getAllDistrict(){
        return serviceDistrict.getDistrictByPage();
    }
}
