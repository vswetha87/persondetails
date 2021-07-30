package com.firstmeridian.persondetails.service.impl;

import com.firstmeridian.persondetails.domain.Person;
import com.firstmeridian.persondetails.dto.PersonRequestDto;
import com.firstmeridian.persondetails.dto.PersonResponseDTO;
import com.firstmeridian.persondetails.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {

    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private PersonRepository personRepository;

    private static final String FIRSTNAME = "John";
    private static final String LASTNAME = "Doe";

    @Test
    public void testCreatePerson(){
        when(personRepository.save(any(Person.class))).thenReturn(getPerson(12));
        PersonResponseDTO personResponseDTO = personService.add(getPersonRequestDto());
        assertNotNull(personResponseDTO);
        assertEquals(FIRSTNAME, personResponseDTO.getFirstName());
        assertEquals(LASTNAME, personResponseDTO.getLastName());
    }

    @Test
    public void testDeletePersonSuccess(){
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(getPerson(12)));
        boolean isDeleted = personService.delete(12);
        assertTrue(isDeleted);
    }

    @Test
    public void testDeletePersonFailure(){
        boolean isDeleted = personService.delete(12);
        assertFalse(isDeleted);
    }


    @Test
    public void testUpdatePerson(){
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(getPerson(13)));
        when(personRepository.save(any(Person.class))).thenReturn(getPerson(13));
        PersonResponseDTO personResponseDTO = personService.update(13, getPersonRequestDto());
        assertNotNull(personResponseDTO);
        assertEquals(FIRSTNAME, personResponseDTO.getFirstName());
        assertEquals(LASTNAME, personResponseDTO.getLastName());
    }

    @Test
    public void testGetAllPersons(){
        List<Person> persons = new ArrayList<>();
        persons.add(getPerson(12));
        persons.add(getPerson(14));
        when(personRepository.findAll()).thenReturn(persons);
        List<PersonResponseDTO> personResponseDTOS = personService.get();
        assertNotNull(personResponseDTOS);
        assertTrue(personResponseDTOS.size() > 1);
        assertEquals(FIRSTNAME, personResponseDTOS.get(0).getFirstName());
        assertEquals(LASTNAME, personResponseDTOS.get(0).getLastName());
    }

    private Person getPerson(long id) {
        Person person = new Person();
        BeanUtils.copyProperties(getPersonRequestDto(), person);
        person.setId(id);
        return person;
    }

    private PersonRequestDto getPersonRequestDto() {
        PersonRequestDto personRequestDto = new PersonRequestDto();
        personRequestDto.setFirstName(FIRSTNAME);
        personRequestDto.setLastName(LASTNAME);
        return personRequestDto;
    }
}
