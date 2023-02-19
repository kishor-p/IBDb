package com.github.kishorp.ibdb.ibdbdomain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("authors")
@Data
public class Author {

    @Id
    private String id;
    private String name;
    private String email;
    private String bio;
}
