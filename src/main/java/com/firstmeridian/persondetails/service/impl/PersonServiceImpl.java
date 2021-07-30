package com.firstmeridian.persondetails.service.impl;

import com.firstmeridian.persondetails.domain.Person;
import com.firstmeridian.persondetails.dto.PersonRequestDto;
import com.firstmeridian.persondetails.dto.PersonResponseDTO;
import com.firstmeridian.persondetails.repository.PersonRepository;
import com.firstmeridian.persondetails.service.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public PersonResponseDTO add(PersonRequestDto personRequestDto) {
        Person person = new Person();
        BeanUtils.copyProperties(personRequestDto, person);
        Person createdPerson = personRepository.save(person);
        return convertToResponse(createdPerson);
    }

    @Override
    public boolean delete(long id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            personRepository.delete(personOptional.get());
            return true;
        }
        return false;
    }

    @Override
    public PersonResponseDTO update(long id, PersonRequestDto personRequestDto) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            person.setFirstName(personRequestDto.getFirstName());
            person.setLastName(personRequestDto.getLastName());
            Person updatedPerson = personRepository.save(person);
            return this.convertToResponse(updatedPerson);
        }
        return null;
    }

    @Override
    public List<PersonResponseDTO> get() {
        List<Person> persons = personRepository.findAll();
        return persons.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    private PersonResponseDTO convertToResponse(Person person) {
        PersonResponseDTO personResponseDTO = new PersonResponseDTO();
        BeanUtils.copyProperties(person, personResponseDTO);
        return personResponseDTO;
    }
}
