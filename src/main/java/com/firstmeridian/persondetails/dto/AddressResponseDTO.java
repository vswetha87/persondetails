package com.firstmeridian.persondetails.dto;

import com.firstmeridian.persondetails.domain.Person;
import lombok.Data;

@Data
public class AddressResponseDTO extends AddressRequestDto {
    private long id;
    private Person person;
}
