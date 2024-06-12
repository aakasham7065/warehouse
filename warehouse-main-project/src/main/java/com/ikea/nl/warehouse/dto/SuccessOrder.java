package com.ikea.nl.warehouse.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SuccessOrder {

    private static final long serialVersionUID = 4570964199436245098L;
    private String orderMessage;
}
