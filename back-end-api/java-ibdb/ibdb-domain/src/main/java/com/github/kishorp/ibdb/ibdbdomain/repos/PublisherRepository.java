package com.github.kishorp.ibdb.ibdbdomain.repos;

import com.github.kishorp.ibdb.ibdbdomain.entity.Publisher;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository extends MongoRepository<Publisher, String>, PublisherFilteringRepository {

    Optional<Publisher> findById(String id);

}
