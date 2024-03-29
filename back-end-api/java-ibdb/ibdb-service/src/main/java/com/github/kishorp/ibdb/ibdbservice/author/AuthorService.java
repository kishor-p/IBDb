package com.github.kishorp.ibdb.ibdbservice.author;

import com.github.kishorp.ibdb.ibdbdomain.dto.AuthorDto;
import com.github.kishorp.ibdb.ibdbservice.error.IbdbServiceException;

import java.util.List;

public interface AuthorService {


    /**
     * <li> Returns all Authors matching the provided params Name & Email</li>
     * <li> If no Params provided it simply returns all the available list of Authors</li>
     *
     * @param name Name based on which the Authors need to be filtered.
     * @param email Email based on which the Authors need to eb filtered.
     * @return List of Authors matching the filters provided as params.
     */
    List<AuthorDto> fetchAllAuthors(String name, String email);

    /**
     * <li> Fetches an individual Author based on ID </li>
     *
     * @param id ID of Author
     * @return Found Author
     * @throws IbdbServiceException Error: ERR_02_404_03 is thrown if no Authors is found by the specified ID
     */
    AuthorDto fetchAuthorById(String id) throws IbdbServiceException;

    /**
     * <li> Adds a new Author to the "Authors" collection </li>
     *
     * @param newAuthorDto New Author that need to be created
     * @return Created AuthorDto with proper ID generated by DB
     * @throws IbdbServiceException
     *  <li> ERR_02_409_01 is thrown if another Author with same Name is available </li>
     *  <li> ERR_02_409_02 is thrown if another Author with same Email is available </li>
     */
    AuthorDto addNewAuthor(AuthorDto newAuthorDto) throws IbdbServiceException;

    /**
     * <li> Updates an existing Author with provided one. </li>
     * <li> If no Author with the provided ID is found, then it creates a NEW one</li>
     *
     * @param authorDto Author that need to be updated
     * @return Updated AuthorDto with proper ID generated by DB
     * @throws IbdbServiceException
     *  <li> ERR_02_409_01 is thrown if another Author with same Name is available </li>
     *  <li> ERR_02_409_02 is thrown if another Author with same Email is available </li>
     */
    AuthorDto updateAuthor(AuthorDto authorDto) throws IbdbServiceException;

    /**
     * <li> Deletes an Existing Author based on ID  </li>
     * @param id ID of the Author that need to be deleted
     */
    void deleteAuthorById(String id);
}
