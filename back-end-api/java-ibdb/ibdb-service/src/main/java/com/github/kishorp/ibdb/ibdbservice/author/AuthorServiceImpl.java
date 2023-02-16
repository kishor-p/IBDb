package com.github.kishorp.ibdb.ibdbservice.author;

import com.github.kishorp.ibdb.ibdbdomain.dto.AuthorDto;
import com.github.kishorp.ibdb.ibdbdomain.entity.Author;
import com.github.kishorp.ibdb.ibdbdomain.repos.authors.AuthorRepository;
import com.github.kishorp.ibdb.ibdbservice.error.ErrorCodes;
import com.github.kishorp.ibdb.ibdbservice.error.IbdbServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorServiceImpl implements AuthorService{

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuthorRepository authorRepo;

    @Override
    public List<AuthorDto> fetchAllAuthors(String name, String email) {
        List<Author> allAuthors = authorRepo.filterByNameEmail(name, email);
        List<AuthorDto> allAuthorDtos = new ArrayList<>();
        for (Author pub: allAuthors) {
            allAuthorDtos.add(convertAuthorToDto(pub));
        }
        return allAuthorDtos;
    }


    @Override
    public AuthorDto fetchAuthorById(String id)  throws IbdbServiceException {
        Optional<Author> pub = authorRepo.findById(id);
        AuthorDto AuthorDto = null;
        if(pub.isPresent()) {
            AuthorDto = convertAuthorToDto(pub.get());
        } else {
            throw new IbdbServiceException(ErrorCodes.ERR_02_404_03, new String[]{id});
        }
        return AuthorDto;
    }

    @Override
    public AuthorDto addNewAuthor(AuthorDto newAuthorDto) throws IbdbServiceException {
        AuthorDto createdAuthorDto ;
        if(this.fetchAllAuthors(newAuthorDto.getName(), null) != null){
            throw new IbdbServiceException(ErrorCodes.ERR_02_409_01);
        } else if(this.fetchAllAuthors(null, newAuthorDto.getEmail()) != null){
            throw new IbdbServiceException(ErrorCodes.ERR_02_409_02);
        } else {
            Author newAuthor = authorRepo.save(convertDtoToAuthor(newAuthorDto));
            createdAuthorDto = convertAuthorToDto(newAuthor);
        }
        return createdAuthorDto;
    }

    @Override
    public AuthorDto updateAuthor(AuthorDto AuthorDto) throws IbdbServiceException {
        AuthorDto updatedAuthorDto = null;

        AuthorDto dtoByName = this.fetchAllAuthors(AuthorDto.getName(), null).get(0);
        AuthorDto dtoByEmail = this.fetchAllAuthors(null, AuthorDto.getEmail()).get(0);
        if( dtoByName != null && !dtoByName.getId().equals(AuthorDto.getId())){
            throw new IbdbServiceException(ErrorCodes.ERR_02_409_01);
        } else if( dtoByEmail != null && !dtoByEmail.getId().equals(AuthorDto.getId())){
            throw new IbdbServiceException(ErrorCodes.ERR_02_409_02);
        } else {
            Author updatedAuthor = authorRepo.save(convertDtoToAuthor(AuthorDto));
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
     * @param Author Entity that needs to be converted
     * @return Converted DTO
     */
    public AuthorDto convertAuthorToDto(Author Author){
        AuthorDto dto = modelMapper.map(Author, AuthorDto.class);
        return dto;
    }

    /**
     * Converts DTO: {@link AuthorDto} to ENTITY: {@link Author}
     *
     * @param AuthorDto DTO that needs to be converted
     * @return Converted Entity
     */
    public Author convertDtoToAuthor(AuthorDto AuthorDto){
        Author Author = modelMapper.map(AuthorDto, Author.class);
        return Author;
    }
}
