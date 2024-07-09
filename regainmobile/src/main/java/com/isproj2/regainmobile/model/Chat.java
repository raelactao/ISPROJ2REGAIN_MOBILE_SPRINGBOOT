package com.isproj2.regainmobile.model;

import java.time.LocalDateTime;

import com.isproj2.regainmobile.dto.ChatDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "chat")
@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Integer chatId;

    @OneToMany
    @JoinColumn(name = "listing_id", referencedColumnName = "prdouct_id", nullable = false)
    private Product listingId;

    @OneToMany
    @JoinColumn(name = "user_sender_id", referencedColumnName = "user_id", nullable = false)
    private User userSenderId;

    @OneToMany
    @JoinColumn(name = "user_receiver_id", referencedColumnName = "user_id", nullable = false)
    private User userReceiverId;

    @lombok.NonNull
    @Column(name = "message", length = 2048)
    private String message;

    @lombok.NonNull
    @Column(name = "sent_timestamp")
    private LocalDateTime sentTimestamp;

    @lombok.NonNull
    @Column(name = "chat_status", length = 20)
    private String chatStatus;

    @Column(name = "status_timestamp")
    private LocalDateTime statusTimestamp;

    @Column(name = "eqlisting_id") //add joincolumn after creating Equipment Class
    private Integer eqlistingId;

    public Chat(ChatDTO chatDTO){
        this.chatId = chatDTO.getChatId();
        this.userSenderId = chatDTO.getUserSenderId();
        this.userReceiverId = chatDTO.getUserReceiverId();
        this.message = chatDTO.getMessage();
        this.sentTimestamp = chatDTO.getSentTimestamp();
        this.chatStatus = chatDTO.getChatStatus();
        this.statusTimestamp = chatDTO.getStatusTimestamp();
        this.eqlistingId = chatDTO.getEqlistingId();
    }
}

