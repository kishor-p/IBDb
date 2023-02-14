package com.github.kishorp.ibdb.ibdbdomain.dto;

import lombok.Data;

/**
 * DTO to carry with basic Publisher entity
 */
@Data
public class PublisherDto {
    private String id;
    private String name;
    private String description;
    private String email;
    private String url;
}
