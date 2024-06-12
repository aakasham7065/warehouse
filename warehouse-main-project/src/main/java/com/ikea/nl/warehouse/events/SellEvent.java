package com.ikea.nl.warehouse.events;

import com.ikea.nl.warehouse.dto.Product;

import org.springframework.context.ApplicationEvent;

import lombok.Data;

@Data
public class SellEvent extends ApplicationEvent {

    /* product to sell */
    private Product product;

    /**
     * Event to trigger when a sell occurs
     * 
     * @param source  Event source object
     * @param product the product that raises the event
     */
    public SellEvent(Object source, Product product) {
        super(source);
        this.product = product;
    }

    private static final long serialVersionUID = -5034102332335146896L;

}