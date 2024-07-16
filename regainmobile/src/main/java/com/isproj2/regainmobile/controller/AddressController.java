package com.isproj2.regainmobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.AddressDTO;
import com.isproj2.regainmobile.model.ResponseModel;
import com.isproj2.regainmobile.services.AddressService;

import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) {
        AddressDTO createdAddress = addressService.createAddress(addressDTO);
        return ResponseEntity.ok(createdAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseModel<Void> deleteAddress(@PathVariable("id") Integer addressId) {
        try {
            addressService.deleteAddress(addressId);
        } catch (ValidationException e) {
            return new ResponseModel<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }

        return new ResponseModel<>(HttpStatus.OK.value(), "Address deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable("id") Integer addressId) {
        AddressDTO addressDTO = addressService.getAddressById(addressId);
        return ResponseEntity.ok(addressDTO);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<AddressDTO>> getAddressesByUser(@PathVariable("id") Integer addressId) {
        List<AddressDTO> addressList = addressService.getAddressByUser(addressId);
        return ResponseEntity.ok(addressList);
    }
}