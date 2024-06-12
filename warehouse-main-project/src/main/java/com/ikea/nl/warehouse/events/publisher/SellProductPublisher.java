package com.ikea.nl.warehouse.events.publisher;

import com.ikea.nl.warehouse.dto.Product;
import com.ikea.nl.warehouse.events.SellEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Publisher for the sell product event
 */
@Component
public class SellProductPublisher {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent(final Product product) {

        log.info("Publishing sell product event ");
        SellEvent customSpringEvent = new SellEvent(this, product);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
