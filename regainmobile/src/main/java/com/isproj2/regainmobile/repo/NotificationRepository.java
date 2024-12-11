package com.isproj2.regainmobile.repo;

import com.isproj2.regainmobile.model.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications, Integer> {

    // Fetch all unread notifications for a user
    List<Notifications> findByUserIdAndIsRead(Integer userId, boolean isRead);

    // Fetch all notifications for a user
    List<Notifications> findByUserId(Integer userId);
}
