package com.isproj2.regainmobile.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.AddressDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.Address;
import com.isproj2.regainmobile.model.Product;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.AddressRepository;
import com.isproj2.regainmobile.repo.ProductRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.AddressService;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

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
        List<Product> prodListWithAddress = productRepository.findByLocationAddressID(addressId);
        if (!prodListWithAddress.isEmpty()) {
            throw new ValidationException("Unable to delete this address");
        }
        addressRepository.deleteById(addressId);
    }

    @Override
    public AddressDTO getAddressById(Integer addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + addressId));
        return new AddressDTO(address.getAddressID(), address.getUnitNumber(), address.getStreet(),
                address.getBarangay(), address.getCity(), address.getProvince(), address.getZipCode(),
                address.getUser().getUserID());
    }

    @Override
    public List<AddressDTO> getAddressByUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        List<Address> addresses = addressRepository.findByUser(user);
        List<AddressDTO> dtoList = new ArrayList<AddressDTO>();

        for (Address address : addresses) {
            dtoList.add(new AddressDTO(address));
        }

        return dtoList;

    }
}