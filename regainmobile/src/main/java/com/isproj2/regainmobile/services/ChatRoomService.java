package com.isproj2.regainmobile.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.isproj2.regainmobile.dto.ChatRoomDTO;
import com.isproj2.regainmobile.model.ChatRoom;

public interface ChatRoomService {
    ChatRoom createChatRoom(Integer userId1, Integer userId2);
    ChatRoom findByRoomId(String roomId); 
    boolean isUserInRoom(String roomId, Integer userId);
    List<ChatRoom> getChatRoomsForUser(Integer userId);
    Page<ChatRoomDTO> getChatRoomsByUserId(Integer userId, Pageable pageable);
} 
