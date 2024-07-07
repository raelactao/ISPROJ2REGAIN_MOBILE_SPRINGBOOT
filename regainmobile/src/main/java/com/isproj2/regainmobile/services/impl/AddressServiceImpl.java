package com.isproj2.regainmobile.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.AddressDTO;
import com.isproj2.regainmobile.model.Address;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.AddressRepository;
import com.isproj2.regainmobile.services.AddressService;
import com.isproj2.regainmobile.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;

    public AddressServiceImpl(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Address addAddress(AddressDTO addressDTO) {
        User user = userService.getUserById(addressDTO.getUserID());
        Address address = new Address();
        address.setUser(user);
        address.setUnitNumber(addressDTO.getUnitNumber());
        address.setStreet(addressDTO.getStreet());
        address.setBarangay(addressDTO.getBarangay());
        address.setCity(addressDTO.getCity());
        address.setProvince(addressDTO.getProvince());
        address.setZipCode(addressDTO.getZipCode());
        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public Address updateAddress(Integer addressId, AddressDTO addressDTO) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found with ID: " + addressId));

        User user = userService.getUserById(addressDTO.getUserID());
        address.setUser(user);
        address.setUnitNumber(addressDTO.getUnitNumber());
        address.setStreet(addressDTO.getStreet());
        address.setBarangay(addressDTO.getBarangay());
        address.setCity(addressDTO.getCity());
        address.setProvince(addressDTO.getProvince());
        address.setZipCode(addressDTO.getZipCode());

        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public void deleteAddress(Integer addressId) {
        addressRepository.deleteById(addressId);
    }

    @Override
    public List<Address> getAllAddressesByUserId(Integer userId) {
        return addressRepository.findAllByUser_Id(userId);
    }

    @Override
    public Address getAddressById(Integer addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found with ID: " + addressId));
    }
}