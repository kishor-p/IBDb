package com.github.kishorp.ibdb.ibdbdomain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoDB Entity for <b>publishers</b> collection.
 */
@Document("publishers")
@Data
public class Publisher {

    @Id
    private String id;
    private String name;
    private String description;
    private String email;
    private String url;
}
