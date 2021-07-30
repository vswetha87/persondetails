package com.firstmeridian.persondetails.controller;

import com.firstmeridian.persondetails.dto.PersonRequestDto;
import com.firstmeridian.persondetails.dto.PersonResponseDTO;
import com.firstmeridian.persondetails.response.ApiResponse;
import com.firstmeridian.persondetails.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.firstmeridian.persondetails.constants.AppConstants.INVALID_PERSON_ID;
import static com.firstmeridian.persondetails.constants.AppConstants.PERSON_DELETED;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<ApiResponse<PersonResponseDTO>> add(@RequestBody PersonRequestDto personRequestDto) {
        PersonResponseDTO personResponseDTO = personService.add(personRequestDto);
        return getResponseEntity(personResponseDTO);
    }

    private ResponseEntity<ApiResponse<PersonResponseDTO>> getResponseEntity(PersonResponseDTO personResponseDTO) {
        ResponseEntity<ApiResponse<PersonResponseDTO>> responseEntity;
        if (personResponseDTO != null) {
            ApiResponse<PersonResponseDTO> apiResponse = new ApiResponse<>(personResponseDTO, "Success",
                    true);
            responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } else {
            ApiResponse<PersonResponseDTO> apiResponse = new ApiResponse<>(personResponseDTO, "Failed",
                    false);
            responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PutMapping
    public ResponseEntity<ApiResponse<PersonResponseDTO>> update(@RequestParam("id") long id, @RequestBody PersonRequestDto personRequestDto) {
        PersonResponseDTO personResponseDTO = personService.update(id, personRequestDto);
        return getResponseEntity(personResponseDTO);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<String>> delete(@RequestParam("id") long id) {
        boolean isDeleted = personService.delete(id);
        ResponseEntity<ApiResponse<String>> responseEntity;
        if (isDeleted) {
            ApiResponse<String> apiResponse = new ApiResponse<>(PERSON_DELETED, "Success",
                    true);
            responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            ApiResponse<String> apiResponse = new ApiResponse<>(INVALID_PERSON_ID, "Failed",
                    false);
            responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PersonResponseDTO>>> get() {
        List<PersonResponseDTO> personResponseDTO = personService.get();
        ResponseEntity<ApiResponse<List<PersonResponseDTO>>> responseEntity;
        if (personResponseDTO != null) {
            ApiResponse<List<PersonResponseDTO>> apiResponse = new ApiResponse<>(personResponseDTO, "Success", true);
            responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            ApiResponse<List<PersonResponseDTO>> apiResponse = new ApiResponse<>(personResponseDTO, "Failed", false);
            responseEntity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCount() {
        List<PersonResponseDTO> personResponseDTO = personService.get();
        ResponseEntity<Integer> responseEntity;
        if (personResponseDTO != null) {
            responseEntity = new ResponseEntity<>(personResponseDTO.size(), HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

}
