package com.training.spring.web.controllers;

import com.training.spring.domain.data.PucharseData;
import com.training.spring.domain.services.PucharseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private PucharseService pucharseService;

    @GetMapping("/all")
    public ResponseEntity<List<PucharseData>> getAll(){
        List<PucharseData> lstPucharses = pucharseService.getAll();
        return lstPucharses.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(lstPucharses, HttpStatus.OK);
    }

    @GetMapping("/customer/{idCustomer}")
    public ResponseEntity<List<PucharseData>> getPucharsesByCient(@PathVariable String idCustomer){
        return pucharseService.getPucharsesByClient(idCustomer)
                .map(pucharsesData -> new ResponseEntity<>(pucharsesData, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<PucharseData> save(@RequestBody PucharseData pucharseData){
        PucharseData savePucharseData = pucharseService.save(pucharseData);
        return Objects.nonNull(savePucharseData) && savePucharseData.getPucharseId() > 0 ?
                new ResponseEntity<>(savePucharseData, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

}
