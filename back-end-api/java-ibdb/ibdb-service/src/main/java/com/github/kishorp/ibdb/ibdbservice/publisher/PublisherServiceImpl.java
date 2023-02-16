package com.github.kishorp.ibdb.ibdbservice.publisher;

import com.github.kishorp.ibdb.ibdbdomain.dto.PublisherDto;
import com.github.kishorp.ibdb.ibdbdomain.entity.Publisher;
import com.github.kishorp.ibdb.ibdbdomain.repos.publishers.PublisherRepository;
import com.github.kishorp.ibdb.ibdbservice.error.ErrorCodes;
import com.github.kishorp.ibdb.ibdbservice.error.IbdbServiceException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <li> Implementation of {@link  PublisherService}. </li>
 * <li> Please refer the {@link  PublisherService} for detailed docs. </li>
 *
 */
@Service
@Slf4j
public class PublisherServiceImpl implements PublisherService{

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PublisherRepository publisherRepo;

    @Override
    public List<PublisherDto> fetchAllPublishers(String name, String email) {
        List<Publisher> allPublishers = publisherRepo.filterByNameEmail(name, email);
        List<PublisherDto> allPublisherDtos = new ArrayList<>();
        for (Publisher pub: allPublishers) {
            allPublisherDtos.add(convertPublisherToDto(pub));
        }
        return allPublisherDtos;
    }


    @Override
    public PublisherDto fetchPublisherById(String id)  throws IbdbServiceException{
        Optional<Publisher> pub = publisherRepo.findById(id);
        PublisherDto publisherDto = null;
        if(pub.isPresent()) {
            publisherDto = convertPublisherToDto(pub.get());
        } else {
            throw new IbdbServiceException(ErrorCodes.ERR_01_404_03, new String[]{id});
        }
        return publisherDto;
    }

    @Override
    public PublisherDto addNewPublisher(PublisherDto newPublisherDto) throws IbdbServiceException {
        PublisherDto createdPublisherDto ;
        if(this.fetchAllPublishers(newPublisherDto.getName(), null) != null){
            throw new IbdbServiceException(ErrorCodes.ERR_01_409_01);
        } else if(this.fetchAllPublishers(null, newPublisherDto.getEmail()) != null){
            throw new IbdbServiceException(ErrorCodes.ERR_01_409_02);
        } else {
            Publisher newPublisher = publisherRepo.save(convertDtoToPublisher(newPublisherDto));
            createdPublisherDto = convertPublisherToDto(newPublisher);
        }
        return createdPublisherDto;
    }

    @Override
    public PublisherDto updatePublisher(PublisherDto publisherDto) throws IbdbServiceException {
        PublisherDto updatedPublisherDto = null;

        PublisherDto dtoByName = this.fetchAllPublishers(publisherDto.getName(), null).get(0);
        PublisherDto dtoByEmail = this.fetchAllPublishers(null, publisherDto.getEmail()).get(0);
        if( dtoByName != null && !dtoByName.getId().equals(publisherDto.getId())){
            throw new IbdbServiceException(ErrorCodes.ERR_01_409_01);
        } else if( dtoByEmail != null && !dtoByEmail.getId().equals(publisherDto.getId())){
            throw new IbdbServiceException(ErrorCodes.ERR_01_409_02);
        } else {
            Publisher updatedPublisher = publisherRepo.save(convertDtoToPublisher(publisherDto));
            updatedPublisherDto = convertPublisherToDto(updatedPublisher);
        }
        return updatedPublisherDto;
    }

    @Override
    public void deletePublisherById(String id) {
        publisherRepo.deleteById(id);
    }

    /**
     * Converts Entity: {@link Publisher}  to DTO: {@link PublisherDto}
     *
     * @param publisher Entity that needs to be converted
     * @return Converted DTO
     */
    public PublisherDto convertPublisherToDto(Publisher publisher){
        PublisherDto dto = modelMapper.map(publisher, PublisherDto.class);
        return dto;
    }

    /**
     * Converts DTO: {@link PublisherDto} to ENTITY: {@link Publisher}
     *
     * @param publisherDto DTO that needs to be converted
     * @return Converted Entity
     */
    public  Publisher convertDtoToPublisher(PublisherDto publisherDto){
        Publisher publisher = modelMapper.map(publisherDto, Publisher.class);
        return publisher;
    }
}
