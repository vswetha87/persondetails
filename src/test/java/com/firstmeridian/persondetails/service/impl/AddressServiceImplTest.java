package com.firstmeridian.persondetails.service.impl;

import com.firstmeridian.persondetails.domain.Address;
import com.firstmeridian.persondetails.domain.Person;
import com.firstmeridian.persondetails.dto.AddressRequestDto;
import com.firstmeridian.persondetails.dto.AddressResponseDTO;
import com.firstmeridian.persondetails.globalexception.AddressException;
import com.firstmeridian.persondetails.repository.AddressRepository;
import com.firstmeridian.persondetails.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private PersonRepository personRepository;

    private static final String CITY = "GWK";
    private static final String STREET = "X-ROAD";
    private static final String STATE = "AP";
    private static final String POSTALCODE = "530000";

    @Test
    public void testCreateAddress(){
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(getPerson()));
        when(addressRepository.save(any(Address.class))).thenReturn(getAddress(12));
        AddressResponseDTO addressResponseDTO = addressService.add(getAddressRequestDto());
        assertNotNull(addressResponseDTO);
        validateAssertion(addressResponseDTO);
    }

    @Test
    public void testDeleteAddressSuccess() throws AddressException {
        when(addressRepository.findById(anyLong())).thenReturn(Optional.of(getAddress(12)));
        boolean isDeleted = addressService.delete(12);
        assertTrue(isDeleted);
    }

    @Test
    public void testDeleteAddressFailure() throws AddressException {
        boolean isDeleted = addressService.delete(12);
        assertFalse(isDeleted);
    }


    @Test
    public void testUpdateAddress() throws AddressException {
        when(addressRepository.findById(anyLong())).thenReturn(Optional.of(getAddress(13)));
        when(addressRepository.save(any(Address.class))).thenReturn(getAddress(13));
        AddressResponseDTO addressResponseDTO = addressService.update(13, getAddressRequestDto());
        assertNotNull(addressResponseDTO);
        validateAssertion(addressResponseDTO);
    }

    private void validateAssertion(AddressResponseDTO addressResponseDTO){
        assertEquals(CITY, addressResponseDTO.getCity());
        assertEquals(POSTALCODE, addressResponseDTO.getPostalCode());
        assertEquals(STATE, addressResponseDTO.getState());
        assertEquals(STREET, addressResponseDTO.getStreet());
    }


    private Address getAddress(long id) {
        Address address = new Address();
        BeanUtils.copyProperties(getAddressRequestDto(), address);
        address.setId(id);
        address.setPerson(getPerson());
        return address;
    }

    private Person getPerson() {
        Person person = new Person();person.setFirstName("John");person.setLastName("Doe");
        person.setId(12);
        return person;
    }

    private AddressRequestDto getAddressRequestDto() {
        AddressRequestDto addressRequestDto = new AddressRequestDto();
        addressRequestDto.setCity(CITY);
        addressRequestDto.setState(STATE);
        addressRequestDto.setStreet(STREET);
        addressRequestDto.setPostalCode(POSTALCODE);
        return addressRequestDto;
    }
}
