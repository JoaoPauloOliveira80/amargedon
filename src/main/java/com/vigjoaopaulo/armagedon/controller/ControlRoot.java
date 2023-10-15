package com.vigjoaopaulo.armagedon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vigjoaopaulo.armagedon.model.Depositos;
import com.vigjoaopaulo.armagedon.model.Proventos;
import com.vigjoaopaulo.armagedon.service.DepositosService;

@RestController
public class ControlRoot {

    @Autowired
    DepositosService depositosService;
    

    @GetMapping("/depositos")
    public List<Depositos> getDepositos() {
        return depositosService.getDepositos();
    }
    
    @GetMapping("/proventos")
    @ResponseBody
    public List<Proventos> getProventos() {
        return depositosService.getProventos();
    }
} 
 