package com.isproj2.regainmobile.services;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.isproj2.regainmobile.dto.ChatMessageDTO;
import com.isproj2.regainmobile.model.ChatMessage;

public interface ChatMessageService {
    ChatMessage sendMessage(ChatMessageDTO messageDTO);
    Page<ChatMessageDTO> getMessages(String roomId, Pageable pageable);
}
