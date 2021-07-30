package com.firstmeridian.persondetails.service;

import com.firstmeridian.persondetails.dto.PersonRequestDto;
import com.firstmeridian.persondetails.dto.PersonResponseDTO;
import com.firstmeridian.persondetails.globalexception.PersonException;

import java.util.List;

public interface PersonService {
    PersonResponseDTO add(PersonRequestDto personRequestDto) throws PersonException;

    boolean delete(long id) throws PersonException;

    PersonResponseDTO update(long id, PersonRequestDto personRequestDto) throws PersonException;

    List<PersonResponseDTO> get();
}
