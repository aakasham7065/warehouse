package com.ikea.nl.warehouse.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.common.base.Preconditions;
import lombok.Data;

@Data
public class Article implements Serializable {
    private static final long serialVersionUID = -1768105479248068676L;

    @JsonProperty("art_id")
    private int artId;
    private String name;
    private int stock;

    Article() {
    }

    public Article(int articleId, String name, int stock) {
        this.artId = articleId;
        this.name = Preconditions.checkNotNull(name);
        this.stock = stock;
    }
}
