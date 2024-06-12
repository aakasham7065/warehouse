package com.ikea.nl.warehouse.services;

import com.ikea.nl.warehouse.dto.Article;
import com.ikea.nl.warehouse.dto.ProductPart;
import com.ikea.nl.warehouse.events.SellEvent;
import com.ikea.nl.warehouse.exceptions.InventoryLoaderException;
import com.ikea.nl.warehouse.util.InventoryLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Predicate;

@Service
public class InventoryService extends AbstractService {

    @Value("${warehouse.inventory.file}")
    private String inventoriesFile;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private Map<Integer, Article> inventories = new HashMap<>();

    @Autowired
    private InventoryLoader inventoryLoader;

    @PostConstruct
    public void init() {
        inventories = inventoryLoader.loadInventory();
    }


    /**
     * Handle sell event by updating the stock
     *
     * @param event
     */
    @EventListener
    public void handleContextStart(SellEvent event) {

        log.info("Event received for selling product with name {}", event.getProduct().getName());
        List<ProductPart> parts = event.getProduct().getProductParts();
        for (ProductPart productPart : parts) {
            if (isArticleAvailableById(productPart.getArtId(), productPart.getAmpuntOf())) {
                removeArticleFromStock(productPart.getArtId(), productPart.getAmpuntOf());
            } else {
                // handle not in stack procedure. like inform user or /and a back-order event.
            }
        }

    }

    /**
     * remove the article amounts form stock
     *
     * @param artId  the article id
     * @param amount the amount to remove from stock
     */
    private void removeArticleFromStock(int artId, int amount) {
        log.info("Removing {} articles from stock with id {}", amount, artId);
        Article article = getArticle(artId);
        article.setStock(article.getStock() - amount);
    }

    private Article getArticle(int artId) {
        if (inventories.containsKey(artId)) {
            return this.inventories.get(artId);
        } else {
            throw new InventoryLoaderException("No such Article");
        }

    }

    public Map<Integer, Article> listInventory() {
        return Collections.unmodifiableMap(this.inventories);
    }

    public boolean isArticleAvailableById(int articleId, int numberOfArticlesRequired) {
        if (this.inventories.containsKey(articleId)) {
            Article article = this.inventories.get(articleId);
            boolean enoughStockIsAvailable = numberOfArticlesRequired <= article.getStock();
            return enoughStockIsAvailable;
        } else {
            return false;
        }

    }
}