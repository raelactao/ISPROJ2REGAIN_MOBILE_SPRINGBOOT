package com.isproj2.regainmobile.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "notifications")
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @lombok.NonNull
    @Column(name = "user_id")
    private Integer userId;

    @lombok.NonNull
    @Column(name = "message", length = 255)
    private String message;

    @lombok.NonNull
    @Column(name = "sender_id")
    private Integer senderId;

    @lombok.NonNull
    @Column(name = "room_id")
    private String roomId;

    @CreationTimestamp
    @lombok.NonNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp timestamp;

    @Column(name = "is_read", nullable = false)
    private boolean isRead = false;

    public Notifications(Integer userId, String message, Integer senderId, String roomId, Timestamp timestamp, boolean isRead) {
        this.userId = userId;
        this.message = message;
        this.senderId = senderId;
        this.roomId = roomId;
        this.timestamp = timestamp;
        this.isRead = isRead;
    }
    
}
