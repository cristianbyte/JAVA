package com.riwi.littleweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.riwi.littleweb.entity.Coder;
import com.riwi.littleweb.services.CoderService;

@Controller
@RequestMapping("/")
public class CoderController {
    
    @Autowired
    private CoderService objCoderService;

    @GetMapping
    public String showGetAll(Model objModel, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size){
        
        Page<Coder> list = this.objCoderService.findPaginated(page -1 ,size);
        
        objModel.addAttribute("coderList", list);
        objModel.addAttribute("currentPage", page);
        objModel.addAttribute("totalPages", list.getTotalPages());

        
        //name HTML view
        return "viewCoder";
    }

    @GetMapping("/form")
    public String showForm(Model objModel){

        objModel.addAttribute("coder", new Coder());
        objModel.addAttribute("action", "/coder/create");
        //name HTML view
        return "viewForm";
    }

    //Update form
    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable Long id, Model objModel){
        Coder obCoderFind = this.objCoderService.findById(id);
        objModel.addAttribute("coder", obCoderFind);
        objModel.addAttribute("action", "/edit/"+id);
        return "viewForm";
    }

    /*Method for insert a coder by POST */
    /*@ModelAttribute get view's information */
    @PostMapping("/coder/create")
    public String createCoder(@ModelAttribute Coder objCoder){
        //calling service
        this.objCoderService.insert(objCoder);

        return "redirect:/";
    }


    @PostMapping("/edit/{id}")
    public String updateCoder(@PathVariable Long id,@ModelAttribute Coder objCoder){
        this.objCoderService.update(id, objCoder);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        this.objCoderService.delete(id);
        return "redirect:/";
    }
}
