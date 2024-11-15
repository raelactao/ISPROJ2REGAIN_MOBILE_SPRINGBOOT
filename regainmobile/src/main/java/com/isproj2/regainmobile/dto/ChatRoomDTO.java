package com.isproj2.regainmobile.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ChatRoomDTO {
     private String roomId;
    private String roomName;  // The other user's username
    private String lastMessage;  // The latest message
    private String timestamp;  // Timestamp (optional, for sorting or display)
}
