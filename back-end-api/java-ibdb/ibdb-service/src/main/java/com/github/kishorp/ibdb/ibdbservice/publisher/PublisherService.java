package com.github.kishorp.ibdb.ibdbservice.publisher;

import com.github.kishorp.ibdb.ibdbdomain.dto.PublisherDto;
import com.github.kishorp.ibdb.ibdbservice.error.IbdbServiceException;

import java.util.List;

public interface PublisherService {

    List<PublisherDto> fetchAllPublishers();

    List<PublisherDto> fetchAllPublishersWithSimilarName(String name);

    PublisherDto fetchPublisherByExactName(String name);

    PublisherDto fetchPublisherByEmail(String email);

    PublisherDto fetchPublisherById(String id);

    PublisherDto addNewPublisher(PublisherDto newPublisherDto) throws IbdbServiceException;

    PublisherDto updatePublisher(PublisherDto publisherDto) throws IbdbServiceException;

    void deletePublisherById(String id);


}
