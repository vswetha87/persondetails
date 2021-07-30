package com.firstmeridian.persondetails.service;

import com.firstmeridian.persondetails.dto.AddressRequestDto;
import com.firstmeridian.persondetails.dto.AddressResponseDTO;
import com.firstmeridian.persondetails.globalexception.AddressException;

public interface AddressService {
    AddressResponseDTO add(AddressRequestDto addressRequestDto);

    boolean delete(long id) throws AddressException;

    AddressResponseDTO update(long id, AddressRequestDto addressRequestDto) throws AddressException;

}
