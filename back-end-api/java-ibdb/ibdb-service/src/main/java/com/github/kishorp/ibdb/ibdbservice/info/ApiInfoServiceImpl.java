package com.github.kishorp.ibdb.ibdbservice.info;

import com.github.kishorp.ibdb.ibdbdomain.info.ApiInfo;
import com.github.kishorp.ibdb.ibdbdomain.info.EndpointInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ApiInfoServiceImpl implements ApiInfoService{
    @Override
    public ApiInfo getFullApiInfo() {
        ApiInfo apiInfo = new ApiInfo();
        apiInfo.setTitle("IBDb: Internet Book Database");
        apiInfo.setDescription("IBDb is an online database of information related to Books, Publishers and Authors");
        apiInfo.setEndpoints(new HashMap<>());

        List<EndpointInfo> bookEndpoints = new ArrayList<>();
        bookEndpoints.add(new EndpointInfo("Lists all books", "GET", "/books/"));
        bookEndpoints.add(new EndpointInfo("Search book ISBN", "GET", "/books&isbn=?"));
        bookEndpoints.add(new EndpointInfo("Search book Title", "GET", "/books&title=?"));
        bookEndpoints.add(new EndpointInfo("Search book Author", "GET", "/books&author=?"));
        bookEndpoints.add(new EndpointInfo("Add a new book", "POST", "/books"));
        bookEndpoints.add(new EndpointInfo("Update an existing book", "PUT", "/books"));
        bookEndpoints.add(new EndpointInfo("Delete an existing book", "DELETE", "/books/{isbn}"));

        apiInfo.getEndpoints().put("Books", bookEndpoints);


        return apiInfo;
    }
}
