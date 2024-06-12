package com.ikea.nl.warehouse.events.listeners;

import com.ikea.nl.warehouse.events.SellEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

/**
 * Listener for the Sell event
 */
public class SellEventListener implements ApplicationListener<SellEvent> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Method called when event istriggered
     */
    @Override
    public void onApplicationEvent(SellEvent event) {
        log.info("Receiving sell product event {}", event.getProduct().getName());
    }
}
