package com.isproj2.regainmobile.controller;

import com.isproj2.regainmobile.model.Notifications;
import com.isproj2.regainmobile.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // /**
    //  * Fetch unread messages for a user in a specific chat room.
    //  */
    // @GetMapping("/{userId}/unread/{roomId}")
    // public ResponseEntity<?> getUnreadMessages(@PathVariable Integer userId, @PathVariable String roomId) {
    //     try {
    //         List<ChatMessage> unreadMessages = notificationService.getUnreadMessages(userId, roomId);
    //         return ResponseEntity.ok(unreadMessages);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                              .body("Error fetching unread messages: " + e.getMessage());
    //     }
    // }

    // /**
    //  * Mark all messages in a chat room as read for a user.
    //  */
    // @PostMapping("/{userId}/markAsRead/{roomId}")
    // public ResponseEntity<?> markChatAsRead(@PathVariable Integer userId, @PathVariable String roomId) {
    //     try {
    //         notificationService.markChatAsRead(userId, roomId);
    //         return ResponseEntity.ok("Chat marked as read.");
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                              .body("Error marking chat as read: " + e.getMessage());
    //     }
    // }

    /**
     * Fetch all unread notifications for a user.
     */
    @GetMapping("/{userId}/unread")
    public ResponseEntity<?> getUnreadNotifications(@PathVariable Integer userId) {
        try {
            List<Notifications> unreadNotifications = notificationService.getUnreadNotifications(userId);
            return ResponseEntity.ok(unreadNotifications);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error fetching unread notifications: " + e.getMessage());
        }
    }

    /**
     * Mark all notifications as read for a user.
     */
    @PostMapping("/{userId}/markAllAsRead")
    public ResponseEntity<?> markAllNotificationsAsRead(@PathVariable Integer userId) {
        try {
            notificationService.markAllNotificationsAsRead(userId);
            return ResponseEntity.ok("All notifications marked as read.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error marking notifications as read: " + e.getMessage());
        }
    }

    @PostMapping("/markAsRead/{notificationId}")
    public ResponseEntity<?> markNotificationAsRead(@PathVariable Integer notificationId) {
        try {
            boolean updated = notificationService.markNotificationAsRead(notificationId);
            if (updated) {
                return ResponseEntity.ok("Notification marked as read.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error marking notification as read: " + e.getMessage());
        }
    }

}
