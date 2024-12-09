package com.isproj2.regainmobile.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.model.Payment;
import com.isproj2.regainmobile.model.User;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}