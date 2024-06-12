package com.ikea.nl.warehouse.util;

import com.ikea.nl.warehouse.dto.Article;
import com.ikea.nl.warehouse.exceptions.InventoryLoaderException;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InventoryLoaderTest {
    private InventoryLoader inventoryLoader = new InventoryLoader();

    @Test
    void shouldLoadInventory() {
        // Given
        ReflectionTestUtils.setField(inventoryLoader, "inventoriesFile", "inventory.json");

        // When
        Map<Integer, Article> actual = inventoryLoader.loadInventory();

        // Then
        assertThat(actual, not(nullValue()));
        assertThat(actual.containsKey(1), equalTo(true));
    }

    @Test
    void shouldLoadInventoryGivenInputItem() {
        // Given
        ReflectionTestUtils.setField(inventoryLoader, "inventoriesFile", "inventory.json");

        // When
        Map<Integer, Article> actual = inventoryLoader.loadInventory();

        // Then
        assertThat(actual, not(nullValue()));
        assertThat(actual.get(1).getName(), equalTo("leg"));
    }


    @Test
    void shouldNotLoadInventory() {
          // Given
        ReflectionTestUtils.setField(inventoryLoader, "inventoriesFile", null);

          // When
        try {
            Map<Integer, Article> actual = inventoryLoader.loadInventory();
        } catch (InventoryLoaderException ex) {
            // Then
            assertEquals("Error loading inventory", ex.getMessage());
        }
    }

    @Test
    void shouThrowException() {
           // Given
        ReflectionTestUtils.setField(inventoryLoader, "inventoriesFile", null);

           // When
        try {

            inventoryLoader.loadInventory();
        } catch (InventoryLoaderException ex) {

            // Then
            assertEquals("Error loading inventory", ex.getMessage());
        }
    }

}