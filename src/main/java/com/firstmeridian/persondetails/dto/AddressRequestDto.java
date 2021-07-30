package com.firstmeridian.persondetails.dto;

import lombok.Data;

@Data
public class AddressRequestDto {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private long personId;
}
