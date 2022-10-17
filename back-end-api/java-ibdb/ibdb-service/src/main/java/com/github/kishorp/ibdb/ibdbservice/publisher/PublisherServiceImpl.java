package com.github.kishorp.ibdb.ibdbservice.publisher;

import com.github.kishorp.ibdb.ibdbdomain.dto.PublisherDto;
import com.github.kishorp.ibdb.ibdbdomain.entity.Publisher;
import com.github.kishorp.ibdb.ibdbdomain.repos.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PublisherServiceImpl implements PublisherService{

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PublisherRepository publisherRepo;

    @Override
    public List<PublisherDto> fetchAllPublishers() {
        List<Publisher> allPublishers = publisherRepo.findAll();
        List<PublisherDto> allPublisherDtos = new ArrayList<>();
        for (Publisher pub: allPublishers) {
            allPublisherDtos.add(convertPublisherToDto(pub));
        }
        return allPublisherDtos;
    }

    @Override
    public List<PublisherDto> fetchAllPublishersWithSimilarName(String name) {
        List<Publisher> allPublishers = publisherRepo.findByNameRegex(name);
        List<PublisherDto> allPublisherDtos = new ArrayList<>();
        for (Publisher pub: allPublishers) {
            allPublisherDtos.add(convertPublisherToDto(pub));
        }
        return allPublisherDtos;
    }

    @Override
    public PublisherDto fetchPublisherByExactName(String name) {
        Optional<Publisher> pub =publisherRepo.findByName(name);
        PublisherDto publisherDto = null;
        if(pub.isPresent())
            publisherDto = convertPublisherToDto(pub.get());
        return publisherDto;
    }

    @Override
    public PublisherDto fetchPublisherByEmail(String email) {
        Optional<Publisher> pub =publisherRepo.findByEmail(email);
        PublisherDto publisherDto = null;
        if(pub.isPresent())
            publisherDto = convertPublisherToDto(pub.get());
        return publisherDto;
    }

    @Override
    public PublisherDto fetchPublisherById(String id) {
        Optional<Publisher> pub = publisherRepo.findById(id);
        PublisherDto publisherDto = null;
        if(pub.isPresent())
            publisherDto = convertPublisherToDto(pub.get());
        return publisherDto;
    }

    @Override
    public PublisherDto addNewPublisher(PublisherDto newPublisherDto) {
        PublisherDto createdPublisherDto = null;
        if(this.fetchPublisherByExactName(newPublisherDto.getName()) != null){
            log.error("409 Duplicate Name");
        } else if(this.fetchPublisherByEmail(newPublisherDto.getEmail()) != null){
            log.error("409 Duplicate Email");
        } else {
            Publisher newPublisher = publisherRepo.save(convertDtoToPublisher(newPublisherDto));
            createdPublisherDto = convertPublisherToDto(newPublisher);
        }
        return createdPublisherDto;
    }

    @Override
    public PublisherDto updatePublisher(PublisherDto publisherDto) {
        PublisherDto updatedPublisherDto = null;

        PublisherDto dtoByName = this.fetchPublisherByExactName(publisherDto.getName());
        PublisherDto dtoByEmail = this.fetchPublisherByEmail(publisherDto.getEmail());
        if( dtoByName != null && !dtoByName.getId().equals(publisherDto.getId())){
            log.error("409 Duplicate Name");
        } else if( dtoByEmail != null && !dtoByEmail.getId().equals(publisherDto.getId())){
            log.error("409 Duplicate Email");
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


    PublisherDto convertPublisherToDto(Publisher publisher){
        PublisherDto dto = modelMapper.map(publisher, PublisherDto.class);
        return dto;
    }

    Publisher convertDtoToPublisher(PublisherDto publisherDto){
        Publisher publisher = modelMapper.map(publisherDto, Publisher.class);
        return publisher;
    }
}
