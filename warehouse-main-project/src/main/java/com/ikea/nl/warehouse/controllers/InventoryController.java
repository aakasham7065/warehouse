package com.ikea.nl.warehouse.controllers;

import java.util.Map;

import com.ikea.nl.warehouse.dto.Article;
import com.ikea.nl.warehouse.services.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    /**
     * list all inventory
     * 
     * @return
     */
    @GetMapping(value = "/rest/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<Integer, Article>> listProducts() {
        Map<Integer, Article> article = inventoryService.listInventory();
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

}
