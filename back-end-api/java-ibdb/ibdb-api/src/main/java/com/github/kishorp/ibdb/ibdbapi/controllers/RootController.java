package com.github.kishorp.ibdb.ibdbapi.controllers;

import com.github.kishorp.ibdb.ibdbservice.info.ApiInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@CrossOrigin("http://localhost:4200")
@Slf4j
public class RootController {
    @Autowired
    ApiInfoService apiInfoService;

    @GetMapping(value = "info", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity info() {
        return new ResponseEntity(apiInfoService.getFullApiInfo(), HttpStatus.OK);
    }

    @GetMapping("/")
    ResponseEntity welcome() {
        return new ResponseEntity("apiInfoService.getFullApiInfo()", HttpStatus.OK);
    }
}
