package com.isproj2.regainmobile.services;

import com.isproj2.regainmobile.model.ChatMessage;
import com.isproj2.regainmobile.model.ChatUserMetadata;
import com.isproj2.regainmobile.model.Notifications;
import com.isproj2.regainmobile.repo.ChatMessageRepository;
import com.isproj2.regainmobile.repo.ChatUserMetadataRepository;
import com.isproj2.regainmobile.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatUserMetadataRepository chatUserMetadataRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    /**
     * Get unread chat messages for a user in a room.
     */
    public List<ChatMessage> getUnreadMessages(Integer userId, String roomId) {
        Optional<ChatUserMetadata> metadataOpt = chatUserMetadataRepository.findByUserIdAndRoomId(userId, roomId);
        Timestamp lastReadAt = metadataOpt.map(ChatUserMetadata::getLastReadAt).orElse(new Timestamp(0));

        return chatMessageRepository.findUnreadMessages(roomId, lastReadAt);
    }

    /**
     * Mark a chat as read by updating the lastReadAt timestamp.
     */
    public void markChatAsRead(Integer userId, String roomId) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Optional<ChatUserMetadata> metadataOpt = chatUserMetadataRepository.findByUserIdAndRoomId(userId, roomId);
        
        if (metadataOpt.isPresent()) {
            metadataOpt.get().setLastReadAt(now);
            chatUserMetadataRepository.save(metadataOpt.get());
        } else {
            ChatUserMetadata newMetadata = new ChatUserMetadata();
            newMetadata.setUserId(userId);
            newMetadata.setRoomId(roomId);
            newMetadata.setLastReadAt(now);
            chatUserMetadataRepository.save(newMetadata);
        }
    }

    /**
     * Get unread notifications for a user.
     */
    public List<Notifications> getUnreadNotifications(Integer userId) {
        return notificationRepository.findByUserIdAndIsRead(userId, false);
    }

    /**
     * Send a notification for a new message.
     */
    public void sendNotification(ChatMessage message) {
        String notificationMessage = "New message from " + message.getSender().getUsername() + ": " + message.getContent();
        
        Notifications notification = new Notifications(
                message.getReceiver().getUserID(), // User ID of the receiver
                notificationMessage, // Notification message including sender and content
                message.getSender().getUserID(), // Sender's user ID
                message.getRoomId().getRoomId(), // Chat room ID
                new Timestamp(System.currentTimeMillis()), // Current timestamp
                false // Mark as unread
        );
        
        notificationRepository.save(notification);
    }


    public void markAllNotificationsAsRead(Integer userId) {
        List<Notifications> unreadNotifications = notificationRepository.findByUserIdAndIsRead(userId, false);
        unreadNotifications.forEach(notification -> notification.setRead(true));
        notificationRepository.saveAll(unreadNotifications);
    }

    public boolean markNotificationAsRead(Integer notificationId) {
        Optional<Notifications> notificationOpt = notificationRepository.findById(notificationId);
        if (notificationOpt.isPresent()) {
            Notifications notification = notificationOpt.get();
            notification.setRead(true);
            notificationRepository.save(notification);
            return true;
        }
        return false;
    }
    
}
