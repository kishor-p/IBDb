package com.github.kishorp.ibdb.ibdbapi.controllers;

import com.github.kishorp.ibdb.ibdbdomain.dto.AuthorDto;
import com.github.kishorp.ibdb.ibdbservice.error.IbdbServiceException;
import com.github.kishorp.ibdb.ibdbservice.author.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <li> Controller to implement the {@link AuthorApi} </li>
 */
@Slf4j
@RestController
public class AuthorController implements AuthorApi{

    @Autowired
    AuthorService authorService;

    @Override
    public ResponseEntity<List<AuthorDto>> getAllAuthors(String name, String email) {
        return new ResponseEntity(authorService.fetchAllAuthors(name, email), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable("id") String id) throws IbdbServiceException {
        return new ResponseEntity(authorService.fetchAuthorById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthorDto> postNewAuthor(@RequestBody AuthorDto authorDto) throws IbdbServiceException {
        return new ResponseEntity(authorService.addNewAuthor(authorDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AuthorDto> putUpdatedAuthor(@RequestBody AuthorDto authorDto) throws IbdbServiceException {
        return new ResponseEntity(authorService.updateAuthor(authorDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> deleteAuthorById(@PathVariable("id") String id) {
        authorService.deleteAuthorById(id);
        return new ResponseEntity("Deleted", HttpStatus.OK);
    }

}
