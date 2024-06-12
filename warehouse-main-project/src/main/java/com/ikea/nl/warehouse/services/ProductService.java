package com.ikea.nl.warehouse.services;

import com.ikea.nl.warehouse.dto.Product;
import com.ikea.nl.warehouse.dto.ProductPart;
import com.ikea.nl.warehouse.dto.Warehouse;
import com.ikea.nl.warehouse.events.publisher.SellProductPublisher;
import com.ikea.nl.warehouse.exceptions.ProductNotInStockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ProductService extends AbstractService {

    @Value("${warehouse.products.file}")
    private String productsFile;

    private Map<String, Product> products = new HashMap<>();

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private SellProductPublisher sellProductPublisher;

    /**
     * initalize the service and load contents from the provided file
     */
    @PostConstruct
    public void init() {
        try {
            log.info("initializing products service");
            Warehouse warehouse = loadFile(productsFile);
            if (warehouse != null && warehouse.getProducts() != null) {
                log.info("loaded {} products", warehouse.getProducts().size());
                addProducts(warehouse.getProducts());
            } else {
                log.warn("No products are loaded");
            }
        } catch (IOException e) {
            log.error("Error while loading products : {}", e.getMessage());
        }

    }

    /**
     * List all products
     */
    public Map<String, Product> listProducts() {
        return this.products;
    }


    public Product getProduct(String name) {
        return this.products.get(name);

    }

    /**
     * List only available products
     * 
     */
    public Map<String, Product> listAvailableProducts() {
        Map<String, Product> availProducts = new HashMap<>();

        this.products.forEach((k, product) -> {
            if (isProductAvailable(product)) {
                availProducts.put(product.getName(), product);
            }
        });

        return availProducts;

    }


    public boolean isProductAvailable(Product product) {
        for (ProductPart part : product.getProductParts()) {
            if (!inventoryService.isArticleAvailableById(part.getArtId(), part.getAmpuntOf())) {
                return false;
            }
        }

        return true;

    }


    public void addProducts(List<Product> products) {
        this.products = products.stream().collect(Collectors.toMap(Product::getName, product -> product));
    }

    public void sellProduct(String name) {
        Product product = getProduct(name);
        if (isProductAvailable(product)) {
            // sell product
            sellProductPublisher.publishCustomEvent(product);
        } else {
            throw new ProductNotInStockException(
                    "Product with name " + name + " is no longer in stock. Check back soon!");

        }
    }

}

