package com.isproj2.regainmobile.services.util;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.model.Order;
import com.isproj2.regainmobile.model.OrderLog;
import com.isproj2.regainmobile.repo.OrderLogRepository;
import com.isproj2.regainmobile.repo.OrderRepository;

import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class OrderScheduler {

    private static final Logger logger = LoggerFactory.getLogger(OrderScheduler.class);
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLogRepository orderLogRepository;

    @Transactional
    @Scheduled(cron = "0 0 0 * * *") // Runs every day at midnight
    // @Scheduled(cron = "0 * * * * *")
    public void autoReceiveDeliveredOrders() {
        logger.info("Scheduler started at {}", LocalDateTime.now());

        // Get all orders with current status 'Delivered'
        List<Order> deliveredOrders = orderRepository.findByCurrentStatus("Delivered");

        for (Order order : deliveredOrders) {
            // Get the latest log entry for this order
            Optional<OrderLog> latestLog = orderLogRepository.findByOrderOrderID(order.getOrderID())
                .stream()
                .max(Comparator.comparing(OrderLog::getTimeStamp));

            if (latestLog.isPresent() && latestLog.get().getPrevStatus().equals("In Transit")) {
                LocalDateTime deliveredTime = latestLog.get().getTimeStamp();

                // // Check if 3 days have passed since the 'Delivered' timestamp
                // if (deliveredTime.isBefore(LocalDateTime.now().minusMinutes(3)))
                if (deliveredTime.isBefore(LocalDateTime.now().minusDays(3)))
                 {
                    //  ORDER STATUS 'Received'
                    order.setCurrentStatus("Received");
                    orderRepository.save(order);

                    logger.info("Order ID {} status changed to 'Received'", order.getOrderID());

                    // NEW LOG ENTRY FOR STATUS CHANGE
                    OrderLog orderLog = new OrderLog();
                    orderLog.setOrder(order);
                    orderLog.setPrevStatus("Delivered"); // Previous status before 'Received'
                    orderLog.setDeliveryDate(order.getDeliveryDate());
                    orderLog.setUpdatedByUser(null); // System update
                    orderLog.setTimeStamp(LocalDateTime.now());

                    orderLogRepository.save(orderLog);
                }

            }
        }
    }
}