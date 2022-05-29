package com.jac.assignment2.service;

import com.fasterxml.jackson.databind.*;
import com.jac.assignment2.model.*;
import com.jac.assignment2.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class AddressService {
    @Autowired
    private  StudentRepository repository;
    @Autowired
    private  AddressRepository addressRepository;

    private ObjectMapper mapper;

    public void saveAddress(Address address){
        addressRepository.save(address);
    }
}
