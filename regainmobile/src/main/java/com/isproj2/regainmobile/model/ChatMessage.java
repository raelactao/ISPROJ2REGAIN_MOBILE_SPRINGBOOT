package com.isproj2.regainmobile.model;

import java.time.Instant;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat_message")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_user_id", referencedColumnName = "user_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_user_id", referencedColumnName = "user_id", nullable = false)
    private User receiver;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    private Instant timestamp;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id", nullable = false)
    @JsonBackReference
    private ChatRoom roomId; 

    public ChatMessage(User sender, User receiver, String content, ChatRoom roomId, Instant timestamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = timestamp != null ? timestamp : Instant.now();
        this.roomId = roomId;
    }

    public ChatRoom getRoomId() {
        return roomId;
    }

}
