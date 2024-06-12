package com.ikea.nl.warehouse.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * A Product
 */
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = 4156220146326471001L;
    private String name;

    @JsonProperty("contain_articles")
    private List<ProductPart> productParts;
}
