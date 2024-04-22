package com.cristianbyte.rickandmortycalulator.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristianbyte.rickandmortycalulator.entities.Operations;

//Rick and Morty
@RestController
@RequestMapping("api/v1/operations")
public class OperationsController {

    @PostMapping(path= "/portal-add") //portal add
    public String portalAdd(@RequestBody Operations objOperation){
        String result = String.valueOf(objOperation.getNum1() + objOperation.getNum2());

        return objOperation.getNum1() + " + " +  objOperation.getNum2() + " = " + result;
    }

    @PostMapping(path= "/portal-multiply") //portal add
    public String portalMultiply(@RequestBody Operations objOperation){
        String result = String.valueOf(objOperation.getNum1() * objOperation.getNum2());

        return objOperation.getNum1() + " x " +  objOperation.getNum2() + " = " + result;
    }
    
    @PostMapping(path= "/portal-division") //portal add
    public String portalDivision(@RequestBody Operations objOperation){
        String result = String.valueOf(objOperation.getNum1() / objOperation.getNum2());

        return objOperation.getNum1() + " / " +  objOperation.getNum2() + " = " + result;
    }

    @PostMapping(path= "/portal-subtract") //portal add
    public String portalSubtract(@RequestBody Operations objOperation){
        String result = String.valueOf(objOperation.getNum1() - objOperation.getNum2());

        return objOperation.getNum1() + " - " +  objOperation.getNum2() + " = " + result;
    }
}
