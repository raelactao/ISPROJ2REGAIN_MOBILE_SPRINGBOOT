package com.isproj2.regainmobile.services;

import java.util.List;

import com.isproj2.regainmobile.dto.AddressDTO;
import com.isproj2.regainmobile.model.Address;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO);

    void deleteAddress(Integer addressId);

    AddressDTO getAddressById(Integer addressId);

    List<AddressDTO> getAddressByUser(Integer userId);
}
