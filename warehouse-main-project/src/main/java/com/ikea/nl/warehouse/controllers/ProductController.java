package com.ikea.nl.warehouse.controllers;

import java.util.Map;

import com.ikea.nl.warehouse.dto.Product;
import com.ikea.nl.warehouse.dto.SuccessOrder;
import com.ikea.nl.warehouse.exceptions.ProductNotFoundException;
import com.ikea.nl.warehouse.services.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * list all availble products
     * 
     * @return
     */
    @GetMapping(value = "/rest/product", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, Product>> listProducts() {
        Map<String, Product> products = productService.listAvailableProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * View product details
     * 
     * @param name the name of the product to vuew
     * @return the product
     */
    @GetMapping(value = "/rest/product/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Product> getProduct(@PathVariable String name) {
        log.info("Getting product with name {}", name);
        Product product = productService.getProduct(name);
        if (product == null) {
            throw new ProductNotFoundException("Product with name '" + name + "' not found");
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Sell a product *
     * 
     * @param name the name of the product to buy
     */
    @PutMapping(value = "/rest/product/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SuccessOrder> sellProduct(@PathVariable String name) {
        log.info("Sell product with name {}", name);
        SuccessOrder successOrderObj = new SuccessOrder();
        productService.sellProduct(name);
        successOrderObj.setOrderMessage("order placed successfully  for "+name);

        return new ResponseEntity<SuccessOrder>(successOrderObj,HttpStatus.CREATED);
    }

}
