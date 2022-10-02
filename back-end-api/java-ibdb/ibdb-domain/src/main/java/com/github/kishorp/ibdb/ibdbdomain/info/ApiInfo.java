package com.github.kishorp.ibdb.ibdbdomain.info;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ApiInfo {
    private String title;
    private String description;
    private Map<String, List<EndpointInfo>> endpoints;

}
