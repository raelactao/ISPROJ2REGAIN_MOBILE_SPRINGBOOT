package com.isproj2.regainmobile.services.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isproj2.regainmobile.dto.CommissionsDTO;
import com.isproj2.regainmobile.dto.CommissionsTotalDTO;
import com.isproj2.regainmobile.dto.OrderDTO;
import com.isproj2.regainmobile.dto.PaymentDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.Commissions;
import com.isproj2.regainmobile.model.Order;
import com.isproj2.regainmobile.model.Payment;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.CommissionsRepository;
import com.isproj2.regainmobile.repo.FavoriteRepository;
import com.isproj2.regainmobile.repo.OrderRepository;
import com.isproj2.regainmobile.repo.PaymentRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.CommissionService;

@Service
public class CommissionServiceImpl implements CommissionService {

    @Autowired
    CommissionsRepository commissionsRepository;

    @Autowired
    FavoriteRepository favoriteRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<CommissionsDTO> getCommissionsByUserId(Integer userId) {

        List<Commissions> userCommissions = commissionsRepository.findByUserUserID(userId);
        return userCommissions.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    private CommissionsDTO convertToDTO(Commissions comm) {
        CommissionsDTO dto = new CommissionsDTO(comm);
        return dto;
    }

    @Override
    public CommissionsTotalDTO getCommissionsTotal(Integer userId) {

        List<Commissions> userCommissions = commissionsRepository.findByUserUserID(userId);
        List<Commissions> pendingCommissions = commissionsRepository.findByStatus("Paid");
        userCommissions.removeAll(pendingCommissions);
        List<CommissionsDTO> dtoList = userCommissions.stream().map(this::convertToDTO).collect(Collectors.toList());

        BigDecimal totalBal = commissionsRepository.sumCommissionsForUser(userId);

        CommissionsTotalDTO commTotal = new CommissionsTotalDTO(new DecimalFormat("0.00").format(totalBal), dtoList);

        return commTotal;

    }

    @Transactional
    @Override
    public CommissionsTotalDTO addPaymentForCommissions(List<CommissionsDTO> list, Integer userId) {

        // declare new dto list
        List<CommissionsDTO> updatedList = new ArrayList<CommissionsDTO>();

        CommissionsDTO firstDTO = list.get(0);
        // get paymentDTO from first commissionDTO
        PaymentDTO paymentDTO = firstDTO.getPayment();
        // convert paymentDTO to payment and add/save payment
        Payment savedPayment = paymentRepository.save(new Payment(paymentDTO));

        // get each commissionDTO
        for (CommissionsDTO comm : list) {

            // get comm based on id
            Commissions commission = commissionsRepository.findById(comm.getCommissionID())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Commission not found with id " + comm.getCommissionID()));
            // set payment with saved payment
            commission.setPayment(savedPayment);
            // save comm and add updated comm to new list
            Commissions savedCommission = commissionsRepository.save(commission);
            updatedList.add(new CommissionsDTO(savedCommission));
        }

        // return list
        BigDecimal totalBal = commissionsRepository.sumCommissionsForUser(userId);
        CommissionsTotalDTO commTotal = new CommissionsTotalDTO(new DecimalFormat("0.00").format(totalBal),
                updatedList);

        return commTotal;
    }

    // private Commissions convertDtoToCommissions(CommissionsDTO dto, Payment
    // payment) {

    // User user = userRepository.findById(dto.getUserID())
    // .orElseThrow(() -> new ResourceNotFoundException("User not found with id " +
    // dto.getUserID()));

    // Order order = orderRepository.findById(dto.getOrder().getOrderID())
    // .orElseThrow(
    // () -> new ResourceNotFoundException("Order not found with id " +
    // dto.getOrder().getOrderID()));

    // Commissions commissions = new Commissions(dto, user, order, payment);

    // return commissions;
    // }

}
