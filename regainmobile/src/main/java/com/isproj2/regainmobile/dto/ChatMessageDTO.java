package com.isproj2.regainmobile.dto;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ChatMessageDTO {
    private Integer senderId;
    private Integer receiverId;
    private String roomId;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
    private String senderUsername; 
    private String receiverUsername;

    public ChatMessageDTO(String content, Integer senderId, Integer receiverId, String senderUsername, String receiverUsername, String roomId, LocalDateTime timestamp) {
        this.content = content;
        this.senderId = senderId;
        this.senderUsername = senderUsername; // Initialize the username here
        this.roomId = roomId;
        this.timestamp = timestamp != null ? timestamp : LocalDateTime.now();
        this.receiverId = receiverId;
        this.receiverUsername = receiverUsername;
    }
}


