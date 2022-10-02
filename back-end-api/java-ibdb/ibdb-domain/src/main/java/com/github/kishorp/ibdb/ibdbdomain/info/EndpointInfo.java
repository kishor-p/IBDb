package com.github.kishorp.ibdb.ibdbdomain.info;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EndpointInfo {
    private String description;
    private String method;
    private String uri;
}
