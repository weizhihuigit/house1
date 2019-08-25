package com.potal.controller;

import com.pojo.Street;
import com.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/page")
@Controller
public class StreetController1 {
    @Autowired
    private StreetService streetService;
    @RequestMapping("/getStreetByDid")
    @ResponseBody
    public List<Street>getStreetByDid(Integer did){
        return streetService.getStreetByDistrict(did);
    }
}
