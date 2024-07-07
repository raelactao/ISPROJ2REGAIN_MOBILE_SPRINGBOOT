package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.AddressDTO;
import com.isproj2.regainmobile.model.Address;

public interface AddressService {
    Address addAddress(AddressDTO addressDTO);
    Address updateAddress(Integer addressId, AddressDTO addressDTO);
    void deleteAddress(Integer addressId);
    List<Address> getAllAddressesByUserId(Integer userId);
    Address getAddressById(Integer addressId);
}
