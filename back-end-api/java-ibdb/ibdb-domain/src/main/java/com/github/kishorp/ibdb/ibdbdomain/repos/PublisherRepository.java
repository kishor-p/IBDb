package com.github.kishorp.ibdb.ibdbdomain.repos;

import com.github.kishorp.ibdb.ibdbdomain.entity.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository extends MongoRepository<Publisher, String> {

    List<Publisher> findByNameRegex(String name);

    Optional<Publisher> findByEmail(String email);

    Optional<Publisher> findById(String id);

    Optional<Publisher> findByName(String name);
}
