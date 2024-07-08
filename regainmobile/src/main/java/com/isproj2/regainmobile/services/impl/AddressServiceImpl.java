package com.isproj2.regainmobile.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.AddressDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.Address;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.AddressRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.AddressService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO) {
        User user = userRepository.findById(addressDTO.getUserID())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + addressDTO.getUserID()));

        Address address = new Address(addressDTO, user);
        addressRepository.save(address);
        return addressDTO;
    }

    @Override
    public void deleteAddress(Integer addressId) {
        addressRepository.deleteById(addressId);
    }

    @Override
    public AddressDTO getAddressById(Integer addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + addressId));
        return new AddressDTO(address.getAddressID(), address.getUnitNumber(), address.getStreet(),
                address.getBarangay(), address.getCity(), address.getProvince(), address.getZipCode(),
                address.getUser().getId());
    }
}