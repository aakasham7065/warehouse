package com.ikea.nl.warehouse.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * A Part (article) of the product
 */
@Data
public class ProductPart implements Serializable {
    private static final long serialVersionUID = 4570964199436245098L;
    @JsonProperty("art_id")
    private int artId;
    @JsonProperty("amount_of")
    private int ampuntOf;
}
