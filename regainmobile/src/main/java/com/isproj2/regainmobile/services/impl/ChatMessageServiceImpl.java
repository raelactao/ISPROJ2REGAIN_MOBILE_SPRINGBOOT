package com.isproj2.regainmobile.services.impl;

import java.time.Instant;
import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.ChatMessageDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.ChatMessage;
import com.isproj2.regainmobile.model.ChatRoom;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.ChatMessageRepository;
import com.isproj2.regainmobile.repo.ChatRoomRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.ChatMessageService;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ChatMessage sendMessage(ChatMessageDTO messageDTO) {
        // Fetch the chat room by room ID
        ChatRoom chatRoom = chatRoomRepository.findByRoomId(messageDTO.getRoomId())
            .orElseThrow(() -> new ResourceNotFoundException("Chat room not found"));

        // Fetch the sender and receiver by their IDs
        User sender = userRepository.findById(messageDTO.getSenderId())
            .orElseThrow(() -> new ResourceNotFoundException("Sender not found"));
        User receiver = userRepository.findById(messageDTO.getReceiverId())
            .orElseThrow(() -> new ResourceNotFoundException("Receiver not found"));

        // Validate if the sender is part of the chat room
        if (!chatRoom.getUser1().getUserID().equals(sender.getUserID()) &&
            !chatRoom.getUser2().getUserID().equals(sender.getUserID())) {
            throw new IllegalArgumentException("Sender is not part of this chat room");
        }

        // Optional: Validate if the receiver is part of the chat room
        if (!chatRoom.getUser1().getUserID().equals(receiver.getUserID()) &&
            !chatRoom.getUser2().getUserID().equals(receiver.getUserID())) {
            throw new IllegalArgumentException("Receiver is not part of this chat room");
        }

        // Create a new ChatMessage
        ChatMessage chatMessage = new ChatMessage(
            sender,
            receiver,
            messageDTO.getContent(),
            chatRoom,
            Instant.now() // Use Instant for consistent UTC timestamps
        );

        // Save the message to the repository
        return chatMessageRepository.save(chatMessage);
    }


    @Override
    public Page<ChatMessageDTO> getMessages(String roomId, Pageable pageable) {
        // Fetch the chat room by roomId
        ChatRoom chatRoom = chatRoomRepository.findByRoomId(roomId)
            .orElseThrow(() -> new ResourceNotFoundException("Chat room not found"));

        // Fetch paginated messages ordered by timestamp in ascending order
        Page<ChatMessage> chatMessages = chatMessageRepository.findByRoomIdOrderByTimestampAsc(chatRoom, pageable);

        // Convert the Page<ChatMessage> to Page<ChatMessageDTO> to return only the necessary fields
        return chatMessages.map(chatMessage -> new ChatMessageDTO(
            chatMessage.getContent(),
            chatMessage.getSender().getUserID(),
            chatMessage.getReceiver().getUserID(),// Assuming you have a getUsername method
            chatMessage.getSender().getUsername(),
            chatMessage.getReceiver().getUsername(),
            chatMessage.getRoomId().getRoomId(),
            chatMessage.getTimestamp()
        ));
    }

    
}
