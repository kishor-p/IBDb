package com.github.kishorp.ibdb.ibdbdomain.repos;

import com.github.kishorp.ibdb.ibdbdomain.entity.Publisher;

import java.util.List;

public interface PublisherFilteringRepository {

    List<Publisher> filterByNameEmail(String name, String email);
}
