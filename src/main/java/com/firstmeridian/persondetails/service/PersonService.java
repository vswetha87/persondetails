package com.firstmeridian.persondetails.service;

import com.firstmeridian.persondetails.dto.PersonRequestDto;
import com.firstmeridian.persondetails.dto.PersonResponseDTO;

import java.util.List;

public interface PersonService {
    PersonResponseDTO add(PersonRequestDto personRequestDto);

    boolean delete(long id);

    PersonResponseDTO update(long id, PersonRequestDto personRequestDto);

    List<PersonResponseDTO> get();
}
