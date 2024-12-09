package com.isproj2.regainmobile.repo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.model.Commissions;

@Repository
public interface CommissionsRepository extends JpaRepository<Commissions, Integer> {

    // Method to sum commissions for a particular user (seller)
    @Query("SELECT SUM(c.commissionBalance) FROM Commissions c WHERE c.user.id = :userId AND (c.status IS NULL OR c.status <> 'paid')")
    BigDecimal sumCommissionsForUser(@Param("userId") int userId);

    // Method to sum commissions for a particular seller (Product's seller)
    @Query("SELECT SUM(c.commissionBalance) FROM Commissions c WHERE c.user.id = :userId")
    BigDecimal sumCommissionsForSeller(@Param("userId") int userId);

    List<Commissions> findByUserUserID(int userId);

    List<Commissions> findByPaymentId(int paymentId);

    List<Commissions> findByStatus(String status);

}
