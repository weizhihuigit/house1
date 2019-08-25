package com.potal.controller;

import com.pojo.Type;
import com.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/page")
public class TypeController1 {
    @Autowired
    private TypeService typeService;
    @RequestMapping("/getAllType")
    @ResponseBody
    public List<Type>getAllType(){
       return typeService.getAllType();
    }
}
