package com.isproj2.regainmobile.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.dto.ChatRoomDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.ChatMessage;
import com.isproj2.regainmobile.model.ChatRoom;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.ChatMessageRepository;
import com.isproj2.regainmobile.repo.ChatRoomRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.ChatRoomService;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public ChatRoom createChatRoom(Integer userId1, Integer userId2) {
        User user1 = userRepository.findById(userId1)
            .orElseThrow(() -> new ResourceNotFoundException("User 1 not found"));
        User user2 = userRepository.findById(userId2)
            .orElseThrow(() -> new ResourceNotFoundException("User 2 not found"));
    
        // Check for existing ChatRoom in both directions
        return chatRoomRepository
            .findByUser1AndUser2OrUser2AndUser1(user1, user2)
            .orElseGet(() -> chatRoomRepository.save(new ChatRoom(user1, user2)));
    }
    

    @Override
    public ChatRoom findByRoomId(String roomId) {
        return chatRoomRepository.findByRoomId(roomId)
            .orElseThrow(() -> new ResourceNotFoundException("Chat room not found"));
    }

    @Override
    public boolean isUserInRoom(String roomId, Integer userId) {
        ChatRoom chatRoom = findByRoomId(roomId);
        return chatRoom.getUser1().getUserID().equals(userId) || chatRoom.getUser2().getUserID().equals(userId);
    }

    @Override
    public List<ChatRoom> getChatRoomsForUser(Integer userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return chatRoomRepository.findByUser1OrUser2(user, user);
    }

    @Override
    public Page<ChatRoomDTO> getChatRoomsByUserId(Integer userId, Pageable pageable) {
        System.out.println("Fetching chat rooms for user ID: " + userId);
        // Fetch paginated chat rooms where the user is either user1 or user2
        Page<ChatRoom> chatRoomsPage = chatRoomRepository.findByUser1_UserIDOrUser2_UserID(userId, userId, pageable);
        System.out.println("Found " + chatRoomsPage.getTotalElements() + " chat rooms for user ID: " + userId);
        
        return chatRoomsPage.map(chatRoom -> {
            System.out.println("Processing chat room ID: " + chatRoom.getRoomId());

            User user1 = chatRoom.getUser1();
            User user2 = chatRoom.getUser2();
            System.out.println("User 1: " + (user1 != null ? user1.getUsername() : "null"));
            System.out.println("User 2: " + (user2 != null ? user2.getUsername() : "null"));
        
            // Ensure user1 and user2 are not null
            if (user1 == null || user2 == null) {
                throw new ResourceNotFoundException("Invalid user references in ChatRoom");
            }
        
    
            // Determine which user is the current logged-in user
            String otherUsername = (user1.getUserID().equals(userId)) ? user2.getUsername() : user1.getUsername();
    
            // Fetch the last message
            ChatMessage lastMessage = chatMessageRepository.findTopMessageByRoomId(chatRoom.getRoomId());
            String lastMessageContent = (lastMessage != null && lastMessage.getContent() != null) 
                ? lastMessage.getContent() 
                : "No messages yet";
            String timestamp = (lastMessage != null && lastMessage.getTimestamp() != null)
                ? lastMessage.getTimestamp().toString()  // Assuming timestamp is of type `LocalDateTime`
                : null;  // Handle null timestamp case
    
            // Create a DTO to return (containing room info and last message)
            ChatRoomDTO chatRoomDTO = new ChatRoomDTO();
            chatRoomDTO.setRoomId(chatRoom.getRoomId());
            chatRoomDTO.setRoomName(otherUsername); // Set the other user's username
            chatRoomDTO.setLastMessage(lastMessageContent); // Set the last message
            chatRoomDTO.setTimestamp(timestamp);
    
            return chatRoomDTO;
        });
    }
    
}

