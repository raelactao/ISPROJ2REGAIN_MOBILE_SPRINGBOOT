package com.isproj2.regainmobile.services;

import com.isproj2.regainmobile.dto.AddressDTO;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO);
    void deleteAddress(Integer addressId);
    AddressDTO getAddressById(Integer addressId);
}
