package com.github.kishorp.ibdb.ibdbservice.publisher;

import com.github.kishorp.ibdb.ibdbdomain.dto.PublisherDto;
import com.github.kishorp.ibdb.ibdbservice.error.IbdbServiceException;

import java.util.List;

public interface PublisherService {

    List<PublisherDto> fetchAllPublishers(String name, String email);

    PublisherDto fetchPublisherById(String id) throws IbdbServiceException;

    PublisherDto addNewPublisher(PublisherDto newPublisherDto) throws IbdbServiceException;

    PublisherDto updatePublisher(PublisherDto publisherDto) throws IbdbServiceException;

    void deletePublisherById(String id);


}
