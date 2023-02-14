package com.github.kishorp.ibdb.ibdbapi.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <li> Root Controller that displays basic info about SWAGGER at base URL </li>
 */
@RestController("/")
@CrossOrigin("http://localhost:4200")
@Slf4j
public class RootController {

    @GetMapping("/")
    ResponseEntity welcome() {
        String html = "<h1>IBDb API</h1> <br/> " +
                "<a href=\"javascript:;\" onclick=\"location.href=window.location.origin+'/ibdb/api/swagger-ui.html'\">Swagger</a>";
        return new ResponseEntity(html, HttpStatus.OK);
    }
}
