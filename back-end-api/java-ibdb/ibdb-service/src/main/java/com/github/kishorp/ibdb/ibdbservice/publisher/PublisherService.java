package com.github.kishorp.ibdb.ibdbservice.publisher;

import com.github.kishorp.ibdb.ibdbdomain.dto.PublisherDto;

import java.util.List;

public interface PublisherService {

    List<PublisherDto> fetchAllPublishers();

    List<PublisherDto> fetchAllPublishersWithSimilarName(String name);

    PublisherDto fetchPublisherByExactName(String name);

    PublisherDto fetchPublisherByEmail(String email);

    PublisherDto fetchPublisherById(String id);

    PublisherDto addNewPublisher(PublisherDto newPublisherDto);

    PublisherDto updatePublisher(PublisherDto publisherDto);

    void deletePublisherById(String id);


}
