package com.isproj2.regainmobile.dto;

import java.time.LocalDateTime;

import com.isproj2.regainmobile.model.Chat;
import com.isproj2.regainmobile.model.Product;
import com.isproj2.regainmobile.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class ChatDTO {
    private Integer chatId;

    private Product listingId;

    @lombok.NonNull
    private User userSenderId;

    @lombok.NonNull
    private User userReceiverId;

    @lombok.NonNull
    private String message;

    @lombok.NonNull
    private LocalDateTime sentTimestamp;

    @lombok.NonNull
    private String chatStatus;

    private LocalDateTime statusTimestamp;

    private Integer eqlistingId;

    public ChatDTO(Chat chat){
        this.chatId = chat.getChatId();
        this.listingId = chat.getListingId();
        this.userSenderId = chat.getUserSenderId();
        this.userReceiverId = chat.getUserReceiverId();
        this.message = chat.getMessage();
        this.sentTimestamp = chat.getSentTimestamp();
        this.chatStatus = chat.getChatStatus();
        this.statusTimestamp = chat.getStatusTimestamp();
        this.eqlistingId = chat.getEqlistingId();
    }
}
