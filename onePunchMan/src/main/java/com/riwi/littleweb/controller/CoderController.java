package com.riwi.littleweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.riwi.littleweb.entity.Coder;
import com.riwi.littleweb.services.CoderService;

@Controller
@RequestMapping("/")
public class CoderController {
    
    @Autowired
    private CoderService objCoderService;

    @GetMapping
    public String showGetAll(Model objModel){

        List<Coder> list = this.objCoderService.findAll();
        objModel.addAttribute("coderList", list);
        
        //name HTML view
        return "viewCoder";
    }

    @GetMapping("/form")
    public String showForm(){
        
        //name HTML view
        return "viewForm";
    }
}
