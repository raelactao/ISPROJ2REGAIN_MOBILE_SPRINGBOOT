package com.isproj2.regainmobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.isproj2.regainmobile.dto.ChatMessageDTO;
import com.isproj2.regainmobile.model.ChatMessage;
import com.isproj2.regainmobile.services.ChatMessageService;
import com.isproj2.regainmobile.services.ChatRoomService;
import com.isproj2.regainmobile.services.UserService;

@Controller
public class ChatWebSocketController {

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private ChatRoomService chatRoomService; // Correct service name

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserService userService; // Inject the userService to fetch usernames

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessageDTO messageDTO) {
        // Validate if the user is part of the chat room
        boolean isAuthorized = chatRoomService.isUserInRoom(messageDTO.getRoomId(), messageDTO.getSenderId());
        if (!isAuthorized) {
            throw new IllegalArgumentException("User not authorized to send messages in this room");
        }

        // Save the message
        ChatMessage savedMessage = chatMessageService.sendMessage(messageDTO);

        String senderUsername = userService.getUsernameById(savedMessage.getSender().getUserID());
        String receiverUsername = userService.getUsernameById(savedMessage.getReceiver().getUserID());

        ChatMessageDTO responseDTO = new ChatMessageDTO(
            savedMessage.getContent(),
            savedMessage.getSender().getUserID(),
            savedMessage.getReceiver().getUserID(),
            senderUsername,
            receiverUsername, 
            savedMessage.getRoomId().getRoomId(),
            savedMessage.getTimestamp()
        );

        // Broadcast the message to all clients subscribed to the room
        String destination = "/topic/chatroom/" + messageDTO.getRoomId();
        messagingTemplate.convertAndSend(destination, responseDTO);
        
    }
}

