package com.github.kishorp.ibdb.ibdbdomain.repos.authors;

import com.github.kishorp.ibdb.ibdbdomain.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <li> Implementation of custom filtering Interface.</li>
 * <li>Uses MongoTemplate and Query</li>
 *
 */
@Repository
public class AuthorFilteringRepoImpl implements AuthorFilteringRepository{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Author> filterByNameEmail(String name, String email) {
        Query q = new Query();
        final List<Criteria> criteriaList = new ArrayList<>();
        if(StringUtils.hasText(name)){
            criteriaList.add(Criteria.where("name").regex(name));
        }

        if(StringUtils.hasText(email)){
            criteriaList.add(Criteria.where("email").regex(email));
        }
        if (!criteriaList.isEmpty())
            q.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()])));
        return mongoTemplate.find(q, Author.class);
    }
}
