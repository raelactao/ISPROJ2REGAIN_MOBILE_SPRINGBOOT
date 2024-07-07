package com.isproj2.regainmobile.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAllByUser_Id(Integer userId);
}
