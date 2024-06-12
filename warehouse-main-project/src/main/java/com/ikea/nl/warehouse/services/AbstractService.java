package com.ikea.nl.warehouse.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.ikea.nl.warehouse.dto.Warehouse;
import com.ikea.nl.warehouse.util.JsonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

public abstract class AbstractService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * load the file specified in the configuration
     * 
     * @param filename the filename of the file to load
     */
    protected Warehouse loadFile(String filename) throws IOException {
        File file = null;
        file = ResourceUtils.getFile(filename);
        if (!file.exists()) {
            // check classpath
            file = ResourceUtils.getFile("classpath:" + filename);
        }
        String json = new String(Files.readAllBytes(file.toPath()));
        return JsonUtil.fromJsonToObject(json, Warehouse.class);

    }
}
