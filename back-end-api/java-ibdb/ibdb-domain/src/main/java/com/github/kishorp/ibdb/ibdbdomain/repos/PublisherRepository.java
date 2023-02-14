package com.github.kishorp.ibdb.ibdbdomain.repos;
import com.github.kishorp.ibdb.ibdbdomain.entity.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

/**
 * <li>Mongo Repository for <b>Publisher</b></li>
 * <li>Implements standard MongoRepository and also custom PublisherFilteringRepository</li>
 */
public interface PublisherRepository extends MongoRepository<Publisher, String>, PublisherFilteringRepository {

    Optional<Publisher> findById(String id);

}
