package com.isproj2.regainmobile.dto;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class ChatMessageDTO {
    private Integer senderId;
    private Integer receiverId;
    private String roomId;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Instant timestamp;
    private String senderUsername; 
    private String receiverUsername;

    public ChatMessageDTO(String content, Integer senderId, Integer receiverId, String senderUsername, String receiverUsername, String roomId, Instant timestamp) {
        this.content = content;
        this.senderId = senderId;
        this.senderUsername = senderUsername; // Initialize the username here
        this.roomId = roomId;
        this.timestamp = timestamp != null ? timestamp : Instant.now();
        this.receiverId = receiverId;
        this.receiverUsername = receiverUsername;
    }

    // Optional: Convert to LocalDateTime for display
    public LocalDateTime getLocalTimestamp(String zoneId) {
        return LocalDateTime.ofInstant(this.timestamp, ZoneId.of(zoneId));
    }
}


