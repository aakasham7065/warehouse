package com.ikea.nl.warehouse.util;

import com.ikea.nl.warehouse.dto.Article;
import com.ikea.nl.warehouse.dto.Warehouse;
import com.ikea.nl.warehouse.exceptions.InventoryLoaderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InventoryLoader {

    @Value("${warehouse.inventory.file}")
    private String inventoriesFile;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private Map<Integer, Article> inventories = new HashMap<>();

    public Map<Integer, Article> loadInventory() {
        File file;
        try {
            log.info("initializing inventory service");
                Warehouse warehouse = loadFile(inventoriesFile);
            if (warehouse != null && warehouse.getArticles() != null) {
                log.info("loaded {} inventory items", warehouse.getArticles().size());
                List<Article> articles = warehouse.getArticles();
                inventories = articles.stream().collect(Collectors.toMap(Article::getArtId, atricle -> atricle));
            } else {
                throw new InventoryLoaderException("No article");
            }
        } catch (IOException ioException) {
            throw new InventoryLoaderException("Error loading inventory", ioException);
        }
        catch (Exception exceptons) {
            throw new InventoryLoaderException("Error loading inventory", exceptons);
        }
        return inventories;

    }


    private Warehouse loadFile(String filename) throws IOException {
        File file = ResourceUtils.getFile(filename);
        if (!file.exists()) {
            // check classpath
            file = ResourceUtils.getFile("classpath:" + filename);
        }
        String json = new String(Files.readAllBytes(file.toPath()));
        return JsonUtil.fromJsonToObject(json, Warehouse.class);

    }
}
