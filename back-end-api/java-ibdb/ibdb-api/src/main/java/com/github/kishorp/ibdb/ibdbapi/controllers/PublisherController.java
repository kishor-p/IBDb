package com.github.kishorp.ibdb.ibdbapi.controllers;

import com.github.kishorp.ibdb.ibdbdomain.dto.PublisherDto;
import com.github.kishorp.ibdb.ibdbservice.error.IbdbServiceException;
import com.github.kishorp.ibdb.ibdbservice.publisher.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
@CrossOrigin("http://localhost:4200")
@Slf4j
public class PublisherController {

    @Autowired
    PublisherService publisherService;

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<PublisherDto>> getAllPublishers() {
        return new ResponseEntity(publisherService.fetchAllPublishers(), HttpStatus.OK);
    }

    @GetMapping(value = "", params = "name", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<PublisherDto>>  getAllPublishersByName(@RequestParam(value = "name") String name) {
        return new ResponseEntity(publisherService.fetchAllPublishersWithSimilarName(name), HttpStatus.OK);
    }

    @GetMapping(value = "", params = "email", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<PublisherDto>  getAllPublishersByEmail(@RequestParam(value = "email") String email) {
        return new ResponseEntity(publisherService.fetchPublisherByEmail(email), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<PublisherDto> getPublisherById(@PathVariable("id") String id) {
        return new ResponseEntity(publisherService.fetchPublisherById(id), HttpStatus.OK);
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<PublisherDto> postNewPublisher(@RequestBody PublisherDto publisherDto) throws IbdbServiceException {
        return new ResponseEntity(publisherService.addNewPublisher(publisherDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<PublisherDto> putUpdatedPublisher(@RequestBody PublisherDto publisherDto) throws IbdbServiceException {
        return new ResponseEntity(publisherService.updatePublisher(publisherDto), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<String> deletePublisherById(@PathVariable("id") String id) {
        publisherService.deletePublisherById(id);
        return new ResponseEntity("Deleted", HttpStatus.OK);
    }

}
