package com.ikea.nl.warehouse.services;

import com.ikea.nl.warehouse.dto.Article;
import com.ikea.nl.warehouse.util.InventoryLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {
    @InjectMocks
    private InventoryService inventoryService;

    @Mock
    private InventoryLoader inventoryLoader;

    @Test
    void shouldInit() {
        // Given

        // When
        inventoryService.init();

        // Then
        verify(inventoryLoader).loadInventory();
    }

    @Test
    void shouldBeAvailableByIdGivenSufficientStock() {
        // Given
        int articleId = 1;
        int numberOfArticlesRequired = 3;
        Map<Integer, Article> inventory = Map.of(1, new Article(articleId, "leg", 3));
        given(inventoryLoader.loadInventory()).willReturn(inventory);
        inventoryService.init();

        // When
        boolean actual = inventoryService.isArticleAvailableById(articleId, numberOfArticlesRequired);

        // Then
        assertThat(actual, equalTo(true));
    }

    @Test
    void shouldNotBeAvailableByIdGivenInSufficientStock() {
        // Given
        int articleId = 1;
        int numberOfArticlesRequired = 3;
        Map<Integer, Article> inventory = Map.of(1, new Article(articleId, "leg", 2));
        given(inventoryLoader.loadInventory()).willReturn(inventory);
        inventoryService.init();

        // When
        boolean actual = inventoryService.isArticleAvailableById(articleId, numberOfArticlesRequired);

        // Then
        assertThat(actual, equalTo(false));
    }

    @Test
    void shouldNotBeAvailableByIdGivenNoArticles() {
        // Given
        int articleId = 1;
        int numberOfArticlesRequired = 3;
        Map<Integer, Article> inventory = Map.of();
        given(inventoryLoader.loadInventory()).willReturn(inventory);
        inventoryService.init();

        // When
        boolean actual = inventoryService.isArticleAvailableById(articleId, numberOfArticlesRequired);

        // Then
        assertThat(actual, equalTo(false));
    }
}