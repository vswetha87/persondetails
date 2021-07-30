package com.firstmeridian.persondetails.controller;

import com.firstmeridian.persondetails.dto.AddressRequestDto;
import com.firstmeridian.persondetails.dto.AddressResponseDTO;
import com.firstmeridian.persondetails.response.ApiResponse;
import com.firstmeridian.persondetails.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.firstmeridian.persondetails.constants.AppConstants.ADDRESS_DELETED;
import static com.firstmeridian.persondetails.constants.AppConstants.INVALID_ADDRESS_ID;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<ApiResponse<AddressResponseDTO>> add(@RequestBody AddressRequestDto addressRequestDto) {
        AddressResponseDTO addressResponseDTO = addressService.add(addressRequestDto);
        ResponseEntity<ApiResponse<AddressResponseDTO>> responseEntity;
        if (addressResponseDTO != null) {
            ApiResponse<AddressResponseDTO> apiResponse = new ApiResponse<>(addressResponseDTO, "Success", true);
            responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } else {
            ApiResponse<AddressResponseDTO> apiResponse = new ApiResponse<>(addressResponseDTO, "Failed due to wrong data",
                    false);
            responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PutMapping
    public ResponseEntity<ApiResponse<AddressResponseDTO>> update(@RequestParam("id") long id, @RequestBody AddressRequestDto addressRequestDto) {
        AddressResponseDTO addressResponseDTO = addressService.update(id, addressRequestDto);
        ResponseEntity<ApiResponse<AddressResponseDTO>> responseEntity;
        if (addressResponseDTO != null) {
            ApiResponse<AddressResponseDTO> apiResponse = new ApiResponse<>(addressResponseDTO, "Success", true);
            responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            ApiResponse<AddressResponseDTO> apiResponse = new ApiResponse<>(addressResponseDTO, "Failed due to wrong data",
                    false);
            responseEntity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<String>> delete(@RequestParam("id") long id) {
        boolean isDeleted = addressService.delete(id);
        ResponseEntity<ApiResponse<String>> responseEntity;
        if (isDeleted) {
            ApiResponse<String> apiResponse = new ApiResponse<>(ADDRESS_DELETED, "Success",
                    true);
            responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            ApiResponse<String> apiResponse = new ApiResponse<>(INVALID_ADDRESS_ID, "Failed due to wrong data",
                    false);
            responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }


}
