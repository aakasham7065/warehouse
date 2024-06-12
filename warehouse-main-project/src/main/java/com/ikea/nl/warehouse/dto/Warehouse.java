package com.ikea.nl.warehouse.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * The warehouse object
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Warehouse {
    // products in the warehouse
    List<Product> products;
    // articles in the warehouse
    @JsonProperty("inventory")
    List<Article> articles;
}
