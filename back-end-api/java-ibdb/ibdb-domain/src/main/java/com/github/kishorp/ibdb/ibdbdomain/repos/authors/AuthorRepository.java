package com.github.kishorp.ibdb.ibdbdomain.repos.authors;

import com.github.kishorp.ibdb.ibdbdomain.entity.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <li>Mongo Repository for <b>Author</b></li>
 * <li>Extends standard MongoRepository and also custom {@link AuthorFilteringRepository}</li>
 */
public interface AuthorRepository extends MongoRepository<Author, String>, AuthorFilteringRepository {

    Author findAuthorById(String id);

}
