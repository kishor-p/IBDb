package com.github.kishorp.ibdb.ibdbdomain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoDB entity for <b>authors</b> collection.
 * Represents Book Authors
 */
@Document("authors")
@Data
public class Author {
    @Id
    private String id;
    private String name;
    private String email;
}
