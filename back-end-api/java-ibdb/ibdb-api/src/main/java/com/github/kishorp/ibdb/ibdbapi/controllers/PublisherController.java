package com.github.kishorp.ibdb.ibdbapi.controllers;

import com.github.kishorp.ibdb.ibdbdomain.dto.PublisherDto;
import com.github.kishorp.ibdb.ibdbservice.error.IbdbServiceException;
import com.github.kishorp.ibdb.ibdbservice.publisher.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <li> Controller to implement the {@link PublisherApi} </li>
 */
@Slf4j
@RestController
public class PublisherController implements PublisherApi{

    @Autowired
    PublisherService publisherService;

    @Override
    public ResponseEntity<List<PublisherDto>> getAllPublishers(String name, String email) {
        return new ResponseEntity(publisherService.fetchAllPublishers(name, email), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PublisherDto> getPublisherById(@PathVariable("id") String id) throws IbdbServiceException {
        return new ResponseEntity(publisherService.fetchPublisherById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PublisherDto> postNewPublisher(@RequestBody PublisherDto publisherDto) throws IbdbServiceException {
        return new ResponseEntity(publisherService.addNewPublisher(publisherDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PublisherDto> putUpdatedPublisher(@RequestBody PublisherDto publisherDto) throws IbdbServiceException {
        return new ResponseEntity(publisherService.updatePublisher(publisherDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> deletePublisherById(@PathVariable("id") String id) {
        publisherService.deletePublisherById(id);
        return new ResponseEntity("Deleted", HttpStatus.OK);
    }

}
