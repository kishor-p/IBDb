package com.github.kishorp.ibdb.ibdbdomain.repos.authors;

import com.github.kishorp.ibdb.ibdbdomain.entity.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

/**
 * <li>Mongo Repository for <b>author</b></li>
 * <li>Implements standard {@link MongoRepository} and also custom {@link AuthorFilteringRepository}</li>
 */
public interface AuthorRepository extends MongoRepository<Author, String>, AuthorFilteringRepository {

    Optional<Author> findById(String id);
}
