package com.firstmeridian.persondetails.service.impl;

import com.firstmeridian.persondetails.domain.Address;
import com.firstmeridian.persondetails.domain.Person;
import com.firstmeridian.persondetails.dto.AddressRequestDto;
import com.firstmeridian.persondetails.dto.AddressResponseDTO;
import com.firstmeridian.persondetails.globalexception.AddressException;
import com.firstmeridian.persondetails.repository.AddressRepository;
import com.firstmeridian.persondetails.repository.PersonRepository;
import com.firstmeridian.persondetails.service.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public AddressResponseDTO add(AddressRequestDto addressRequestDto) {
        try {
            Optional<Person> personOptional = personRepository.findById(addressRequestDto.getPersonId());
            Address address = new Address();
            BeanUtils.copyProperties(addressRequestDto, address);
            personOptional.ifPresent(address::setPerson);
            Address createdAddress = addressRepository.save(address);
            AddressResponseDTO addressResponseDTO = convertToResponse(createdAddress);
            addressResponseDTO.setPersonId(createdAddress.getPerson().getId());
            return addressResponseDTO;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(long id) throws AddressException {
        try{
            Optional<Address> addressOptional = addressRepository.findById(id);
            if (addressOptional.isPresent()) {
                addressRepository.delete(addressOptional.get());
                return true;
            }
        }catch (Exception e){
            throw new AddressException("Invalid Id");
        }
        return false;
    }

    @Override
    public AddressResponseDTO update(long id, AddressRequestDto addressRequestDto) throws AddressException {
        try {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            address.setCity(addressRequestDto.getCity());
            address.setState(addressRequestDto.getState());
            address.setStreet(addressRequestDto.getStreet());
            address.setPostalCode(addressRequestDto.getPostalCode());
            Address udpatedAddress = addressRepository.save(address);
            return this.convertToResponse(udpatedAddress);
        }
        }catch (Exception e){
            throw new AddressException("Invalid Request");
        }
        return null;
    }

    private AddressResponseDTO convertToResponse(Address address) {
        AddressResponseDTO addressResponseDTO = new AddressResponseDTO();
        BeanUtils.copyProperties(address, addressResponseDTO);
        return addressResponseDTO;
    }
}
