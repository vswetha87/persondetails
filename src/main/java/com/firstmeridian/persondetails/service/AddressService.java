package com.firstmeridian.persondetails.service;

import com.firstmeridian.persondetails.dto.AddressRequestDto;
import com.firstmeridian.persondetails.dto.AddressResponseDTO;

public interface AddressService {
    AddressResponseDTO add(AddressRequestDto addressRequestDto);

    boolean delete(long id);

    AddressResponseDTO update(long id, AddressRequestDto addressRequestDto);

}
