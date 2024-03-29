package com.training.spring.web.controllers;

import com.training.spring.domain.data.PucharseData;
import com.training.spring.domain.services.PucharseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(
            summary = "Get all purchases",
            method = "GET",
            operationId = "getAllPurchases"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all purchases"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Empty List", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<List<PucharseData>> getAll(){
        List<PucharseData> lstPucharses = pucharseService.getAll();
        return lstPucharses.isEmpty() ? new ResponseEntity<>(Collections.emptyList(),HttpStatus.NOT_FOUND) : new ResponseEntity<>(lstPucharses, HttpStatus.OK);
    }

    @GetMapping("/customer/{idCustomer}")
    @Operation(
            summary = "Get purchases by client ID",
            method = "GET",
            operationId = "getPurchasesByClientId"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of purchases for the specified client"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Error code", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<List<PucharseData>> getPucharsesByCient(@Parameter(description = "ID of the client", example = "7", required = true) @PathVariable String idCustomer){
        return pucharseService.getPucharsesByClient(idCustomer)
                .map(pucharsesData -> new ResponseEntity<>(pucharsesData, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(
            summary = "Save a purchase",
            method = "POST",
            operationId = "savePurchase"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Purchase saved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "406", description = "Purchase could not be saved due to invalid data", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<PucharseData> save(@Parameter(description = "Purchase data to be saved", required = true) @RequestBody PucharseData pucharseData){
        PucharseData savePucharseData = pucharseService.save(pucharseData);
        return Objects.nonNull(savePucharseData) && savePucharseData.getPucharseId() > 0 ?
                new ResponseEntity<>(savePucharseData, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

}
