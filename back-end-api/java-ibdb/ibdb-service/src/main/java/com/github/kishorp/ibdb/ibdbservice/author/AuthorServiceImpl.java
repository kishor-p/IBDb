package com.github.kishorp.ibdb.ibdbservice.author;

import com.github.kishorp.ibdb.ibdbdomain.dto.AuthorDto;
import com.github.kishorp.ibdb.ibdbdomain.entity.Author;
import com.github.kishorp.ibdb.ibdbdomain.repos.authors.AuthorRepository;
import com.github.kishorp.ibdb.ibdbservice.error.ErrorCodes;
import com.github.kishorp.ibdb.ibdbservice.error.IbdbServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    AuthorRepository authorRepo;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<AuthorDto> fetchAllAuthors(String name, String email) {
        List<Author> allAuthors = authorRepo.filterAuthorsByNameEmail(name, email);
        List<AuthorDto> allAuthorDtos = new ArrayList<>();
        for (Author auth: allAuthors) {
            allAuthorDtos.add(convertAuthorToDto(auth));
        }
        return allAuthorDtos;
    }

    @Override
    public AuthorDto fetchAuthorById(String id) throws IbdbServiceException {
        Optional<Author> aut = authorRepo.findById(id);
        AuthorDto authorDto = null;
        if(aut.isPresent()) {
            authorDto = convertAuthorToDto(aut.get());
        } else {
            throw new IbdbServiceException(ErrorCodes.ERR_02_404_03, new String[]{id});
        }
        return authorDto;
    }

    @Override
    public AuthorDto addNewAuthor(AuthorDto newAuthorDto) throws IbdbServiceException {
        AuthorDto createdAuthorDto;
        if(this.fetchAllAuthors(newAuthorDto.getName(), null) .size() > 0){
            throw new IbdbServiceException(ErrorCodes.ERR_02_409_01);
        } else if(this.fetchAllAuthors(null, newAuthorDto.getEmail()) .size() > 0){
            throw new IbdbServiceException(ErrorCodes.ERR_02_409_02);
        } else {
            Author newAuthor = authorRepo.save(convertDtoToAuthor(newAuthorDto));
            createdAuthorDto = convertAuthorToDto(newAuthor);
        }
        return createdAuthorDto;
    }

    @Override
    public AuthorDto updateAuthor(AuthorDto authorDto) throws IbdbServiceException {
        AuthorDto updatedAuthorDto = null;

        AuthorDto dtoByName = this.fetchAllAuthors(authorDto.getName(), null).get(0);
        AuthorDto dtoByEmail = this.fetchAllAuthors(null, authorDto.getEmail()).get(0);
        if( dtoByName != null && !dtoByName.getId().equals(authorDto.getId())){
            throw new IbdbServiceException(ErrorCodes.ERR_02_409_01);
        } else if( dtoByEmail != null && !dtoByEmail.getId().equals(authorDto.getId())){
            throw new IbdbServiceException(ErrorCodes.ERR_02_409_02);
        } else {
            Author updatedAuthor = authorRepo.save(convertDtoToAuthor(authorDto));
            updatedAuthorDto = convertAuthorToDto(updatedAuthor);
        }
        return updatedAuthorDto;
    }

    @Override
    public void deleteAuthorById(String id) {
        authorRepo.deleteById(id);
    }


    /**
     * Converts Entity: {@link Author}  to DTO: {@link AuthorDto}
     *
     * @param author Entity that needs to be converted
     * @return Converted DTO
     */
    public AuthorDto convertAuthorToDto(Author author){
        AuthorDto dto = modelMapper.map(author, AuthorDto.class);
        return dto;
    }

    /**
     * Converts DTO: {@link AuthorDto} to ENTITY: {@link Author}
     *
     * @param authorDto DTO that needs to be converted
     * @return Converted Entity
     */
    public  Author convertDtoToAuthor(AuthorDto authorDto){
        Author author = modelMapper.map(authorDto, Author.class);
        return author;
    }
}
