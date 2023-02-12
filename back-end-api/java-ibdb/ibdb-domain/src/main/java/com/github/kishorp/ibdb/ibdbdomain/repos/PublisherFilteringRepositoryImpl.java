package com.github.kishorp.ibdb.ibdbdomain.repos;

import com.github.kishorp.ibdb.ibdbdomain.entity.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PublisherFilteringRepositoryImpl implements PublisherFilteringRepository{

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public List<Publisher> filterByNameEmail(String name, String email) {


        Query q = new Query();
        final List<Criteria> criterias = new ArrayList<>();
        if(StringUtils.hasText(name)){
            criterias.add(Criteria.where("name").regex(name));
        }

        if(StringUtils.hasText(email)){
            criterias.add(Criteria.where("email").regex(email));
        }
        if (!criterias.isEmpty())
            q.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()])));
        return mongoTemplate.find(q, Publisher.class);
    }
}
