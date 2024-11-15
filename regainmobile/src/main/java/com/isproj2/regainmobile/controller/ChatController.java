package com.isproj2.regainmobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.ChatMessageDTO;
import com.isproj2.regainmobile.dto.ChatRoomDTO;
import com.isproj2.regainmobile.exceptions.ResourceNotFoundException;
import com.isproj2.regainmobile.model.ChatRoom;
import com.isproj2.regainmobile.services.ChatMessageService;
import com.isproj2.regainmobile.services.ChatRoomService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ChatMessageService chatMessageService; 

    @PostMapping("/createRoom")
    public ResponseEntity<ChatRoom> createChatRoom(@RequestParam Integer userId1, @RequestParam Integer userId2) {
        try {
            ChatRoom chatRoom = chatRoomService.createChatRoom(userId1, userId2);
            return ResponseEntity.ok(chatRoom); // Return HTTP 200 OK with the created room
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Return HTTP 400 if there's an error
        }
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<ChatRoom> getChatRoom(@PathVariable String roomId) {
        try {
            ChatRoom chatRoom = chatRoomService.findByRoomId(roomId);
            return ResponseEntity.ok(chatRoom); // Return the chat room if found
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return HTTP 404 if not found
        }
    }

    @GetMapping("/room/{roomId}/messages")
    public ResponseEntity<Page<ChatMessageDTO>> getMessages(
            @PathVariable String roomId, 
            Pageable pageable) {

        try {
            // Fetch paginated messages for the room
            Page<ChatMessageDTO> messages = chatMessageService.getMessages(roomId, pageable);
            return ResponseEntity.ok(messages); // Return the paginated messages with HTTP 200 OK
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return HTTP 404 if room is not found
        }
    }

@GetMapping("/user/{userId}/rooms")
public ResponseEntity<Page<ChatRoomDTO>> getUserChatRooms(@PathVariable Integer userId, Pageable pageable) {
    try {
        System.out.println("Fetching chat rooms for user ID: " + userId);
        
        // Fetch paginated chat rooms for the user
        Page<ChatRoomDTO> chatRooms = chatRoomService.getChatRoomsByUserId(userId, pageable);
        
        if (chatRooms.isEmpty()) {
            System.out.println("No chat rooms found for user ID: " + userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(chatRooms);
        }
        
        System.out.println("Chat rooms fetched successfully for user ID: " + userId);
        return ResponseEntity.ok(chatRooms);
    } catch (ResourceNotFoundException ex) {
        System.out.println("Resource not found: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (Exception ex) {
        System.out.println("Unexpected error: " + ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

}
